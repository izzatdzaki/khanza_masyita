<?php
include '../conf/conf.php';

header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $no_rawat = $_POST['no_rawat'];
    $kode = $_POST['kode'];

    // Direktori upload
    $upload_dir = '../berkas_rwat/pages/upload/';

    // Cek dan buat direktori jika belum ada
    if (!file_exists($upload_dir)) {
        mkdir($upload_dir, 0777, true);
    }

    $file = $_FILES['file'];
    $filename = $file['name'];
    $filepath = $upload_dir . $filename;

    try {
        // Upload file
        if (move_uploaded_file($file['tmp_name'], $filepath)) {
            // Simpan ke database
            $lokasi_file = 'berkas_rwat/pages/upload/' . $filename;
            $query = "INSERT INTO berkas_digital_perawatan (no_rawat, kode, lokasi_file) 
                     VALUES ('$no_rawat', '$kode', '$lokasi_file')";

            if (mysqli_query($koneksi, $query)) {
                echo json_encode(['success' => true]);
            } else {
                echo json_encode(['success' => false, 'message' => 'Gagal menyimpan ke database']);
            }
        } else {
            echo json_encode(['success' => false, 'message' => 'Gagal upload file']);
        }
    } catch (Exception $e) {
        echo json_encode(['success' => false, 'message' => $e->getMessage()]);
    }
} else {
    echo json_encode(['success' => false, 'message' => 'Method not allowed']);
}
