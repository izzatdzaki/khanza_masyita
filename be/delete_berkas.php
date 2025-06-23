<?php
include '../conf/conf.php';

header('Content-Type: application/json');

// Get JSON input
$input = json_decode(file_get_contents('php://input'), true);

// Check if required parameters are present
if (!isset($input['no_rawat']) || !isset($input['kode']) || !isset($input['lokasi_file'])) {
    echo json_encode(['success' => false, 'message' => 'Parameter tidak lengkap']);
    exit;
}

$no_rawat = mysqli_real_escape_string($koneksi, $input['no_rawat']);
$kode = mysqli_real_escape_string($koneksi, $input['kode']);
$lokasi_file = mysqli_real_escape_string($koneksi, $input['lokasi_file']);

try {
    // Delete from database first
    $sql = "DELETE FROM berkas_digital_perawatan WHERE no_rawat = ? AND kode = ? AND lokasi_file = ?";
    $stmt = mysqli_prepare($koneksi, $sql);
    
    if ($stmt) {
        mysqli_stmt_bind_param($stmt, "sss", $no_rawat, $kode, $lokasi_file);
        
        if (mysqli_stmt_execute($stmt)) {
            // Check if any rows were affected
            if (mysqli_stmt_affected_rows($stmt) > 0) {
                // Delete physical file
                $file_path = '../' . $lokasi_file;
                if (file_exists($file_path)) {
                    unlink($file_path);
                }
                echo json_encode(['success' => true, 'message' => 'File berhasil dihapus']);
            } else {
                echo json_encode(['success' => false, 'message' => 'File tidak ditemukan di database']);
            }
        } else {
            echo json_encode(['success' => false, 'message' => 'Gagal menghapus dari database: ' . mysqli_error($koneksi)]);
        }
        
        mysqli_stmt_close($stmt);
    } else {
        echo json_encode(['success' => false, 'message' => 'Gagal prepare statement: ' . mysqli_error($koneksi)]);
    }
} catch (Exception $e) {
    echo json_encode(['success' => false, 'message' => 'Error: ' . $e->getMessage()]);
}

mysqli_close($koneksi);
?>