<?php
include '../conf/conf.php';

header('Content-Type: application/json');

// Check if required parameters are present
if (!isset($_POST['no_rawat']) || !isset($_POST['kode']) || !isset($_FILES['file'])) {
    echo json_encode(['success' => false, 'message' => 'Parameter tidak lengkap']);
    exit;
}

$no_rawat = mysqli_real_escape_string($koneksi, $_POST['no_rawat']);
$kode = mysqli_real_escape_string($koneksi, $_POST['kode']);

// Validate file upload
if ($_FILES['file']['error'] !== UPLOAD_ERR_OK) {
    echo json_encode(['success' => false, 'message' => 'Error upload file: ' . $_FILES['file']['error']]);
    exit;
}

$file = $_FILES['file'];
$name = $file['name'];
$size = $file['size'];
$type = $file['type'];
$tmp_name = $file['tmp_name'];

// File size limit (100MB)
$maxsize = 100 * 1024 * 1024;

// Allowed file types
$allowed_types = [
    'application/pdf',
    'image/jpeg',
    'image/jpg', 
    'image/png',
    'application/msword',
    'application/vnd.openxmlformats-officedocument.wordprocessingml.document'
];

// Validate file size
if ($size > $maxsize) {
    echo json_encode(['success' => false, 'message' => 'Ukuran file terlalu besar (maksimal 100MB)']);
    exit;
}

// Validate file type
if (!in_array($type, $allowed_types)) {
    echo json_encode(['success' => false, 'message' => 'Tipe file tidak diizinkan']);
    exit;
}

// Create upload directory if not exists
$upload_dir = '../pages/upload/';
if (!file_exists($upload_dir)) {
    mkdir($upload_dir, 0755, true);
}

// Generate unique filename
$file_extension = pathinfo($name, PATHINFO_EXTENSION);
$unique_name = $kode . '_' . $no_rawat . '_' . date('YmdHis') . '.' . $file_extension;
$upload_path = $upload_dir . $unique_name;
$relative_path = 'pages/upload/' . $unique_name;

try {
    // Move uploaded file
    if (move_uploaded_file($tmp_name, $upload_path)) {
        // Insert into database
        $sql = "INSERT INTO berkas_digital_perawatan (no_rawat, kode, lokasi_file) VALUES (?, ?, ?)";
        $stmt = mysqli_prepare($koneksi, $sql);
        
        if ($stmt) {
            mysqli_stmt_bind_param($stmt, "sss", $no_rawat, $kode, $relative_path);
            
            if (mysqli_stmt_execute($stmt)) {
                echo json_encode(['success' => true, 'message' => 'File berhasil diupload']);
            } else {
                // Delete uploaded file if database insert fails
                unlink($upload_path);
                echo json_encode(['success' => false, 'message' => 'Gagal menyimpan ke database: ' . mysqli_error($koneksi)]);
            }
            
            mysqli_stmt_close($stmt);
        } else {
            // Delete uploaded file if prepare fails
            unlink($upload_path);
            echo json_encode(['success' => false, 'message' => 'Gagal prepare statement: ' . mysqli_error($koneksi)]);
        }
    } else {
        echo json_encode(['success' => false, 'message' => 'Gagal memindahkan file']);
    }
} catch (Exception $e) {
    // Clean up uploaded file on error
    if (file_exists($upload_path)) {
        unlink($upload_path);
    }
    echo json_encode(['success' => false, 'message' => 'Error: ' . $e->getMessage()]);
}

mysqli_close($koneksi);
?>