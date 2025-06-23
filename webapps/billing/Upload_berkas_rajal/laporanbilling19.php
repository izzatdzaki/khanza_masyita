<?php
include '../conf/conf.php';
include_once '../phpqrcode/qrlib.php'; // Pastikan path ini benar dan file ada

// Define getOne function if not already defined
if (!function_exists('getOne')) {
    function getOne($sql) {
        global $conn;
        $hasil = mysqli_query($conn, $sql);
        if ($hasil && $baris = mysqli_fetch_array($hasil)) {
            return $baris[0];
        }
        return null; // Return null if no result
    }
}

// Define getOne3 function if not already defined
if (!function_exists('getOne3')) {
    function getOne3($sql, $default = '') {
        global $conn;
        $hasil = mysqli_query($conn, $sql);
        if ($hasil && $baris = mysqli_fetch_array($hasil)) {
            return $baris[0];
        }
        return $default;
    }
}

ini_set('upload_max_filesize', '10M');
ini_set('post_max_size', '16M');
ini_set('memory_limit', '256M');

// Assume reportsqlinjection() is defined elsewhere and works
// reportsqlinjection(); 

$usere      = trim(isset($_GET['usere'])) ? trim($_GET['usere']) : NULL;
$passwordte = trim(isset($_GET['passwordte'])) ? trim($_GET['passwordte']) : NULL;

if ((USERHYBRIDWEB == $usere) && (PASHYBRIDWEB == $passwordte)) {
    $petugas        = validTeks4(str_replace("_", " ", $_GET['petugas']), 20);
    $tanggal        = validTeks4(str_replace("_", " ", $_GET['tanggal']), 20);
    $nonota         = str_replace(": ", "", getOne("select temporary_bayar_ralan.temp2 from temporary_bayar_ralan where temporary_bayar_ralan.temp9='$petugas' and temporary_bayar_ralan.temp1='No.Nota'"));
    $norawat        = getOne("select nota_jalan.no_rawat from nota_jalan where nota_jalan.no_nota='$nonota'");
    $kodecarabayar  = getOne("select reg_periksa.kd_pj from reg_periksa where reg_periksa.no_rawat='$norawat'");
    $carabayar      = getOne("select penjab.png_jawab from penjab where penjab.kd_pj='$kodecarabayar'");
    
    $PNG_TEMP_DIR   = dirname(__FILE__) . DIRECTORY_SEPARATOR . 'temp' . DIRECTORY_SEPARATOR;
    $PNG_WEB_DIR    = 'temp/';
    if (!file_exists($PNG_TEMP_DIR)) mkdir($PNG_TEMP_DIR);

    $setting =  mysqli_fetch_array(bukaquery("select setting.nama_instansi,setting.alamat_instansi,setting.kabupaten,setting.propinsi,setting.kontak,setting.email,setting.logo from setting"));

    // --- Persiapan Data untuk JavaScript ---
    $billing_data = [
        'header' => [
            'nama_instansi' => $setting['nama_instansi'],
            'alamat_instansi' => $setting['alamat_instansi'],
            'kabupaten' => $setting['kabupaten'],
            'propinsi' => $setting['propinsi'],
            'kontak' => $setting['kontak'],
            'email' => $setting['email'],
            'logo' => base64_encode($setting['logo']),
            'carabayar' => $carabayar,
            'tanggal' => $tanggal,
        ],
        'info_pasien' => [],
        'dokter' => [],
        'administrasi' => [],
        'tindakan' => [],
        'operasi_vk' => [],
        'obat_bhp' => [],
        'potongan' => [],
        'tambahan' => [],
        'tagihan' => [],
        'ttd' => [
            'petugas_nama' => '',
            'petugas_id' => '',
            'qrcode_petugas' => '',
            'qr_data_petugas' => ''
        ],
        'no_rawat' => $norawat
    ];

    // Info Pasien (temp1 sampai temp6 dari temporary_bayar_ralan)
    $_sql_info_pasien = "select temporary_bayar_ralan.temp1,temporary_bayar_ralan.temp2 from temporary_bayar_ralan where temporary_bayar_ralan.temp9='$petugas' order by temporary_bayar_ralan.no asc";
    $hasil_info_pasien = bukaquery($_sql_info_pasien);
    $z = 1;
    while ($inapdrpasien = mysqli_fetch_array($hasil_info_pasien)) {
        if ($z <= 6) {
            $billing_data['info_pasien'][] = [
                'label' => str_replace("  ", "", $inapdrpasien[0]),
                'value' => $inapdrpasien[1]
            ];
        }
        $z++;
    }

    // Dokter
    $_sql_dokter = "select temporary_bayar_ralan.temp2 from temporary_bayar_ralan where temporary_bayar_ralan.temp9='$petugas' and temporary_bayar_ralan.temp8='Dokter' group by temporary_bayar_ralan.temp2 order by temporary_bayar_ralan.no asc";
    $hasil_dokter = bukaquery($_sql_dokter);
    while ($inapdrpasien = mysqli_fetch_array($hasil_dokter)) {
        $billing_data['dokter'][] = $inapdrpasien[0];
    }

    // Administrasi Rekam Medik
    $hasil_administrasi = bukaquery("select temporary_bayar_ralan.temp1,temporary_bayar_ralan.temp2,temporary_bayar_ralan.temp3,temporary_bayar_ralan.temp7 from temporary_bayar_ralan where temporary_bayar_ralan.temp9='$petugas' and temporary_bayar_ralan.temp8='Registrasi' order by temporary_bayar_ralan.no asc");
    while ($inapdrpasien = mysqli_fetch_array($hasil_administrasi)) {
        $billing_data['administrasi'][] = [
            'description' => $inapdrpasien[1],
            'amount' => $inapdrpasien[3]
        ];
    }

    // Tindakan
    $hasil_tindakan = bukaquery("select temporary_bayar_ralan.temp1,temporary_bayar_ralan.temp2,temporary_bayar_ralan.temp3,temporary_bayar_ralan.temp7,temporary_bayar_ralan.temp5 from temporary_bayar_ralan where temporary_bayar_ralan.temp9='$petugas' and (temporary_bayar_ralan.temp8='Ralan Dokter' or temporary_bayar_ralan.temp8='Ralan Dokter Paramedis' or temporary_bayar_ralan.temp8='Ralan Paramedis' or temporary_bayar_ralan.temp8='Laborat' or temporary_bayar_ralan.temp8='Radiologi') order by temporary_bayar_ralan.no asc");
    while ($inapdrpasien = mysqli_fetch_array($hasil_tindakan)) {
        if (!empty($inapdrpasien[3])) {
            $billing_data['tindakan'][] = [
                'description' => $inapdrpasien[1],
                'qty' => $inapdrpasien[4],
                'amount' => $inapdrpasien[3]
            ];
        }
    }
    
    // Operasi / VK
    $hasil_operasi = bukaquery("select temporary_bayar_ralan.temp1,temporary_bayar_ralan.temp2,temporary_bayar_ralan.temp3,temporary_bayar_ralan.temp7,temporary_bayar_ralan.temp5 from temporary_bayar_ralan where temporary_bayar_ralan.temp9='$petugas' and temporary_bayar_ralan.temp8='Operasi' order by temporary_bayar_ralan.no asc");
    while ($inapdrpasien = mysqli_fetch_array($hasil_operasi)) {
        if (!empty($inapdrpasien[3])) {
            $billing_data['operasi_vk'][] = [
                'description' => $inapdrpasien[1],
                'qty' => $inapdrpasien[4],
                'amount' => $inapdrpasien[3]
            ];
        }
    }

    // Obat & BHP
    $hasil_obat = bukaquery("select temporary_bayar_ralan.temp1,temporary_bayar_ralan.temp2,temporary_bayar_ralan.temp3,temporary_bayar_ralan.temp7,temporary_bayar_ralan.temp8,temporary_bayar_ralan.temp5 from temporary_bayar_ralan where temporary_bayar_ralan.temp9='$petugas' and (temporary_bayar_ralan.temp8='Obat' or temporary_bayar_ralan.temp8='TtlObat') group by temporary_bayar_ralan.temp2 order by temporary_bayar_ralan.no asc");
    while ($inapdrpasien = mysqli_fetch_array($hasil_obat)) {
        $billing_data['obat_bhp'][] = [
            'description' => $inapdrpasien[1],
            'qty' => $inapdrpasien[5],
            'amount' => $inapdrpasien[3],
            'type' => $inapdrpasien[4] // 'Obat' or 'TtlObat'
        ];
    }
    
    // Potongan
    $hasil_potongan = bukaquery("select temporary_bayar_ralan.temp1,temporary_bayar_ralan.temp2,temporary_bayar_ralan.temp3,temporary_bayar_ralan.temp7,temporary_bayar_ralan.temp5 from temporary_bayar_ralan where temporary_bayar_ralan.temp9='$petugas' and temporary_bayar_ralan.temp8='Potongan'  order by temporary_bayar_ralan.no asc");
    while ($inapdrpasien = mysqli_fetch_array($hasil_potongan)) {
        $billing_data['potongan'][] = [
            'label' => $inapdrpasien[0],
            'description' => $inapdrpasien[1],
            'qty' => $inapdrpasien[4],
            'amount' => $inapdrpasien[3]
        ];
    }

    // Tambahan
    $hasil_tambahan = bukaquery("select temporary_bayar_ralan.temp1,temporary_bayar_ralan.temp2,temporary_bayar_ralan.temp3,temporary_bayar_ralan.temp7,temporary_bayar_ralan.temp5 from temporary_bayar_ralan where temporary_bayar_ralan.temp9='$petugas' and temporary_bayar_ralan.temp8='Tambahan'  order by temporary_bayar_ralan.no asc");
    while ($inapdrpasien = mysqli_fetch_array($hasil_tambahan)) {
        $billing_data['tambahan'][] = [
            'label' => $inapdrpasien[0],
            'description' => $inapdrpasien[1],
            'qty' => $inapdrpasien[4],
            'amount' => $inapdrpasien[3]
        ];
    }

    // Tagihan (Total)
    $hasil_tagihan = bukaquery("select temporary_bayar_ralan.temp1,temporary_bayar_ralan.temp2,temporary_bayar_ralan.temp3,temporary_bayar_ralan.temp7 from temporary_bayar_ralan where temporary_bayar_ralan.temp9='$petugas' and temporary_bayar_ralan.temp8='Tagihan' and temporary_bayar_ralan.temp7<>'' order by temporary_bayar_ralan.no asc");
    while ($inapdrpasien = mysqli_fetch_array($hasil_tagihan)) {
        $billing_data['tagihan'][] = [
            'label' => $inapdrpasien[0],
            'amount' => $inapdrpasien[3]
        ];
    }

    // TTD dan QR Code
    $petugas_nama = '';
    $petugas_id = '';
    $qr_data_petugas = '';
    $filename_qr = '';

    if (getOne("select count(petugas.nama) from petugas where petugas.nip='$petugas'") >= 1) {
        $petugas_nama = getOne("select petugas.nama from petugas where petugas.nip='$petugas'");
        $petugas_id = getOne3("select ifnull(sha1(sidikjari.sidikjari),'" . $petugas . "') from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik='" . $petugas . "'", $petugas);
        $qr_data_petugas = "Dikeluarkan di " . $setting["nama_instansi"] . ", Kabupaten/Kota " . $setting["kabupaten"] . "\nDitandatangani secara elektronik oleh " . $petugas_nama . "\nID  " . $petugas_id . "\n" . $tanggal;
    } else {
        $petugas_nama = "Admin Utama";
        $petugas_id = "ADMIN";
        $qr_data_petugas = "Dikeluarkan di " . $setting["nama_instansi"] . ", Kabupaten/Kota " . $setting["kabupaten"] . "\nDitandatangani secara elektronik oleh Admin Utama\nID ADMIN\n" . $tanggal;
    }

    $filename_qr = $PNG_TEMP_DIR . $petugas . '.png';
    $errorCorrectionLevel = 'L';
    $matrixPointSize = 4;
    QRcode::png($qr_data_petugas, $filename_qr, $errorCorrectionLevel, $matrixPointSize, 2);

    $billing_data['ttd']['petugas_nama'] = $petugas_nama;
    $billing_data['ttd']['petugas_id'] = $petugas_id; // Store for debugging if needed
    $billing_data['ttd']['qr_data_petugas'] = $qr_data_petugas; // Store for debugging if needed
    $billing_data['ttd']['qrcode_petugas'] = base64_encode(file_get_contents($filename_qr)); // Read the generated QR code image

    // Convert PHP data to JSON for JavaScript
    $json_billing_data = json_encode($billing_data);

} else {
    exit(header("Location:../index.php"));
}
?>
<html>

<head>
    <title>Billing Rawat Jalan</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.8.2/jspdf.plugin.autotable.min.js"></script>
    <style>
        body {
            font-family: 'Tahoma', sans-serif;
            font-size: 10pt;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        .upload-section {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            border-radius: 5px;
            padding: 15px;
            margin: 10px 0;
            font-family: Tahoma, sans-serif;
        }

        .upload-form {
            display: flex;
            align-items: center;
            gap: 10px;
            margin-bottom: 10px;
        }

        .upload-btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 12px;
        }

        .upload-btn:hover {
            background-color: #0056b3;
        }

        .file-input {
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 12px;
        }

        @media print {
            .upload-section {
                display: none !important;
            }
        }
        /* Hide the HTML content that will be replaced by PDF generation */
        .tbl_form {
            display: none; 
        }
    </style>
    <script type="text/javascript">
        // Pass PHP data to JavaScript
        const billingData = <?php echo $json_billing_data; ?>;
        const norawat = billingData.no_rawat;

        window.onload = function() {
            // Automatically generate PDF and optionally upload/download
            generateBillingPDF(norawat, billingData);
        };

        async function generateBillingPDF(norawat, data) {
            const { jsPDF } = window.jspdf;
            const doc = new jsPDF('p', 'mm', 'a4'); // Portrait, millimeters, A4 size

            let yOffset = 10; // Initial Y offset from top margin
            const margin = 15; // Left/Right margin
            const pageWidth = doc.internal.pageSize.getWidth();
            const contentWidth = pageWidth - (2 * margin);

            // --- Header RS ---
            if (data.header.logo) {
                const img = new Image();
                img.src = 'data:image/jpeg;base64,' + data.header.logo;
                await new Promise(resolve => { // Ensure image is loaded before adding
                    img.onload = () => {
                        const imgWidth = 15; // Smaller size for logo
                        const imgHeight = (img.height * imgWidth) / img.width;
                        doc.addImage(img, 'JPEG', margin, yOffset, imgWidth, imgHeight);
                        resolve();
                    };
                    img.onerror = () => {
                        console.error("Failed to load logo image.");
                        resolve(); // Resolve even on error to not block PDF generation
                    };
                });
            }
            
            doc.setFontSize(14);
            doc.setFont('helvetica', 'bold');
            doc.text(data.header.nama_instansi, pageWidth / 2, yOffset + 3, { align: 'center' });
            
            doc.setFontSize(8);
            doc.setFont('helvetica', 'normal');
            doc.text(`${data.header.alamat_instansi}, ${data.header.kabupaten}, ${data.header.propinsi}`, pageWidth / 2, yOffset + 7, { align: 'center' });
            doc.text(`${data.header.kontak}, E-mail : ${data.header.email}`, pageWidth / 2, yOffset + 11, { align: 'center' });
            doc.text('BILLING', pageWidth / 2, yOffset + 15, { align: 'center' });

            doc.setFontSize(10);
            doc.text(data.header.carabayar, pageWidth - margin, yOffset + 3, { align: 'right' }); // Cara Bayar
            yOffset += 25; // Move Y down after header

            doc.line(margin, yOffset, pageWidth - margin, yOffset); // Separator line
            yOffset += 5; // Space after line

            // --- Info Pasien ---
            doc.setFontSize(10);
            doc.setFont('helvetica', 'normal');
            data.info_pasien.forEach(item => {
                doc.text(item.label, margin, yOffset);
                doc.text(': ' + item.value, margin + 30, yOffset); // Adjust 30 for label width
                yOffset += 5;
            });
            yOffset += 2; // Extra space

            // --- Dokter ---
            doc.text('Dokter', margin, yOffset);
            doc.text(': ', margin + 30, yOffset);
            const initialDocY = yOffset;
            let currentDocY = initialDocY;
            data.dokter.forEach((dokter, index) => {
                doc.text(dokter, margin + 32, currentDocY);
                currentDocY += 5;
            });
            yOffset = currentDocY;
            yOffset += 2; // Extra space

            // --- Administrasi Rekam Medik ---
            if (data.administrasi.length > 0) {
                doc.setFont('helvetica', 'bold');
                doc.text('Administrasi Rekam Medik', margin, yOffset);
                doc.setFont('helvetica', 'normal');
                yOffset += 5;
                data.administrasi.forEach(item => {
                    doc.text(item.description, margin + 5, yOffset);
                    doc.text(item.amount, pageWidth - margin, yOffset, { align: 'right' });
                    yOffset += 5;
                });
                yOffset += 2;
            }

            // --- Tindakan ---
            if (data.tindakan.length > 0) {
                doc.setFont('helvetica', 'bold');
                doc.text('Tindakan', margin, yOffset);
                doc.setFont('helvetica', 'normal');
                yOffset += 5;

                const tindakanRows = data.tindakan.map(t => [t.description, t.qty, t.amount]);
                doc.autoTable({
                    startY: yOffset,
                    head: [['Deskripsi', 'Qty', 'Jumlah']],
                    body: tindakanRows,
                    theme: 'plain', // No borders, plain style
                    styles: { fontSize: 9, cellPadding: 1, overflow: 'linebreak' },
                    headStyles: { fontStyle: 'bold', fillColor: [255, 255, 255], textColor: [0, 0, 0] },
                    columnStyles: {
                        0: { cellWidth: contentWidth * 0.75 }, // Description
                        1: { cellWidth: contentWidth * 0.05, halign: 'center' }, // Qty
                        2: { cellWidth: contentWidth * 0.20, halign: 'right' } // Amount
                    },
                    margin: { left: margin + 5, right: margin } // Indent actions slightly
                });
                yOffset = doc.autoTable.previous.finalY + 2;
            }

            // --- Operasi / VK ---
            if (data.operasi_vk.length > 0) {
                doc.setFont('helvetica', 'bold');
                doc.text('Operasi / VK', margin, yOffset);
                doc.setFont('helvetica', 'normal');
                yOffset += 5;

                const operasiRows = data.operasi_vk.map(t => [t.description, t.qty, t.amount]);
                doc.autoTable({
                    startY: yOffset,
                    head: [['Deskripsi', 'Qty', 'Jumlah']],
                    body: operasiRows,
                    theme: 'plain', // No borders, plain style
                    styles: { fontSize: 9, cellPadding: 1, overflow: 'linebreak' },
                    headStyles: { fontStyle: 'bold', fillColor: [255, 255, 255], textColor: [0, 0, 0] },
                    columnStyles: {
                        0: { cellWidth: contentWidth * 0.75 }, // Description
                        1: { cellWidth: contentWidth * 0.05, halign: 'center' }, // Qty
                        2: { cellWidth: contentWidth * 0.20, halign: 'right' } // Amount
                    },
                    margin: { left: margin + 5, right: margin } // Indent actions slightly
                });
                yOffset = doc.autoTable.previous.finalY + 2;
            }

            // --- Obat & BHP ---
            if (data.obat_bhp.length > 0) {
                doc.setFont('helvetica', 'bold');
                doc.text('Obat & BHP', margin, yOffset);
                doc.setFont('helvetica', 'normal');
                yOffset += 5;

                const obatBhpRows = [];
                data.obat_bhp.forEach(item => {
                    if (item.type === 'Obat') {
                        obatBhpRows.push([item.description, item.qty, item.amount]);
                    } else if (item.type === 'TtlObat') {
                        // For 'TtlObat', it looks like it's a subtotal, handled differently in original HTML
                        // Let's add it as a summary row or bolded row.
                        obatBhpRows.push(['', '', { content: `(${item.description}) ${item.amount}`, styles: { fontStyle: 'bold' } }]);
                    }
                });

                doc.autoTable({
                    startY: yOffset,
                    head: [['Deskripsi', 'Qty', 'Jumlah']],
                    body: obatBhpRows,
                    theme: 'plain',
                    styles: { fontSize: 9, cellPadding: 1, overflow: 'linebreak' },
                    headStyles: { fontStyle: 'bold', fillColor: [255, 255, 255], textColor: [0, 0, 0] },
                    columnStyles: {
                        0: { cellWidth: contentWidth * 0.75 },
                        1: { cellWidth: contentWidth * 0.05, halign: 'center' },
                        2: { cellWidth: contentWidth * 0.20, halign: 'right' }
                    },
                    margin: { left: margin + 5, right: margin },
                    didParseCell: function (data) {
                        if (data.row.raw[2] && data.row.raw[2].styles) { // Check if it's the custom total row
                            data.cell.styles = { ...data.cell.styles, ...data.row.raw[2].styles };
                            data.cell.text = [data.row.raw[2].content];
                        }
                    }
                });
                yOffset = doc.autoTable.previous.finalY + 2;
            }

            // --- Potongan ---
            if (data.potongan.length > 0) {
                doc.setFont('helvetica', 'bold');
                doc.text('Potongan', margin, yOffset);
                doc.setFont('helvetica', 'normal');
                yOffset += 5;
                data.potongan.forEach(item => {
                    doc.text(item.description, margin + 5, yOffset);
                    doc.text(item.amount, pageWidth - margin, yOffset, { align: 'right' });
                    yOffset += 5;
                });
                yOffset += 2;
            }

            // --- Tambahan ---
            if (data.tambahan.length > 0) {
                doc.setFont('helvetica', 'bold');
                doc.text('Tambahan', margin, yOffset);
                doc.setFont('helvetica', 'normal');
                yOffset += 5;
                data.tambahan.forEach(item => {
                    doc.text(item.description, margin + 5, yOffset);
                    doc.text(item.amount, pageWidth - margin, yOffset, { align: 'right' });
                    yOffset += 5;
                });
                yOffset += 2;
            }
            
            // --- Tagihan (Total) ---
            if (data.tagihan.length > 0) {
                yOffset += 5; // Space before total
                data.tagihan.forEach(item => {
                    doc.setFont(item.label === 'TOTAL BAYAR' ? 'helvetica' : 'helvetica', item.label === 'TOTAL BAYAR' ? 'bold' : 'normal');
                    doc.setFontSize(item.label === 'TOTAL BAYAR' ? 12 : 10);
                    doc.text(item.label, margin, yOffset);
                    doc.text(item.amount, pageWidth - margin, yOffset, { align: 'right' });
                    yOffset += 6; // More space for total
                });
                yOffset += 5;
            }

            // --- Footer / Tanda Tangan ---
            const footerYStart = doc.internal.pageSize.getHeight() - 50; // Position from bottom
            yOffset = Math.max(yOffset, footerYStart); // Ensure footer doesn't overlap content above

            doc.setFontSize(10);
            doc.setFont('helvetica', 'normal');
            doc.text(`${data.header.kabupaten}, ${data.header.tanggal}`, pageWidth - margin, yOffset, { align: 'right' });
            yOffset += 5;
            
            doc.text('Petugas', margin + 20, yOffset, { align: 'center' }); // Align Petugas slightly right
            doc.text('Penanggung Jawab Pasien', pageWidth - margin - 20, yOffset, { align: 'center' });
            yOffset += 5;

            // QR Code for Petugas
            if (data.ttd.qrcode_petugas) {
                const qrImg = new Image();
                qrImg.src = 'data:image/png;base64,' + data.ttd.qrcode_petugas;
                await new Promise(resolve => { // Ensure image is loaded
                    qrImg.onload = () => {
                        const qrSize = 25; // Size for QR code
                        doc.addImage(qrImg, 'PNG', margin + 10, yOffset, qrSize, qrSize); // Position QR code
                        resolve();
                    };
                    qrImg.onerror = () => {
                        console.error("Failed to load QR code image.");
                        resolve();
                    };
                });
            }
            
            doc.text(`( ${data.ttd.petugas_nama} )`, margin + 20, yOffset + 30, { align: 'center' }); // Petugas Name
            doc.text('( ............. )', pageWidth - margin - 20, yOffset + 30, { align: 'center' }); // Pasien Signature

            // --- Output PDF ---
            const clean_norawat = norawat ? norawat.replace(/[\/]/g, '') : '';
            const filename = 'Billing_' + clean_norawat + '.pdf';

            // Option to save or upload
            // doc.save(filename); // Downloads the PDF directly

            // For upload functionality:
            const pdfBlob = doc.output('blob');
            if (pdfBlob.size > 2 * 1024 * 1024) { // 2MB limit
                alert('Ukuran file billing melebihi 2MB (' + (pdfBlob.size / 1024 / 1024).toFixed(2) + ' MB). Silakan perkecil tampilan billing atau hubungi admin.');
                return;
            }

            const formData = new FormData();
            formData.append('file', pdfBlob, filename);
            formData.append('no_rawat', norawat);

            try {
                const response = await fetch('upload_billing.php', {
                    method: 'POST',
                    body: formData
                });
                const text = await response.text();
                let result;
                try {
                    result = JSON.parse(text);
                } catch (e) {
                    alert('Upload gagal. Server tidak mengembalikan data JSON yang valid.');
                    console.error('Server response:', text);
                    return;
                }

                if (result.success) {
                    alert('Billing berhasil diupload!');
                    // Optionally, you can offer to download after successful upload
                    doc.save(filename); // Auto-download after successful upload
                } else {
                    alert('Error: ' + (result.message || 'Gagal mengupload billing'));
                }
            } catch (error) {
                alert('Terjadi kesalahan saat upload billing');
                console.error('Error:', error);
            }
        }

        function triggerPdfDownload() {
            // This function can be called by a button if you want manual download
            generateBillingPDF(norawat, billingData); // Re-run generation just for download
        }

        // Modified uploadBilling to directly call generateBillingPDF which handles upload
        // We might not need this separate function anymore if generateBillingPDF is called on load
        // But keeping it for backward compatibility or explicit button calls.
        function uploadBilling(norawat) {
             generateBillingPDF(norawat, billingData); // Call the main function
        }

        // Disable original window.print() if you want only PDF button to trigger it
        // window.onload = function() { window.print(); } 
    </script>
</head>

<body bgcolor='#ffffff'>
    <?php if (!empty($norawat)) : ?>
        <div class='upload-section'>
            <h3 style='margin: 0 0 10px 0; font-size: 14px; color: #333;'>Upload Berkas Digital - No. Rawat: <?php echo $norawat; ?></h3>
            <div class='upload-form'>
                <button type='button' class='upload-btn' onclick='uploadBilling("<?php echo $norawat; ?>")'>Upload & Download PDF Billing</button>
                <button type='button' class='upload-btn' onclick='window.print()' style='background-color: #28a745;'>Print Current View (HTML)</button>
                <p style="font-size:10px; margin-left:10px; color:#666;">*PDF akan otomatis ter-download setelah berhasil diupload.</p>
            </div>
        </div>
    <?php endif; ?>

    <div class="tbl_form">
        <p>This content is hidden. The PDF is generated by JavaScript.</p>
        <?php
            // Example of how you would display data here if not hidden
            // echo "<h1>" . $billing_data['header']['nama_instansi'] . "</h1>";
            // ... and so on
        ?>
    </div>
    
    <?php if (empty($norawat)) : ?>
        <font color='000000' size='1' face='Times New Roman'><b>Data Billing masih kosong!</b></font>
    <?php endif; ?>
</body>
</html>