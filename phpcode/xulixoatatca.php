<?php
// Kết nối CSDL
$link = mysqli_connect("localhost", "SuongMai", "28905", "dulieu");
if (!$link) {
    die("Không thể kết nối: " . mysqli_connect_error());
}

$soLuongXoa = 0;

// Kiểm tra có dữ liệu checkbox gửi lên không
if (isset($_POST['IDNV']) && is_array($_POST['IDNV'])) {
    foreach ($_POST['IDNV'] as $idnv) {
        $stmt = mysqli_prepare($link, "DELETE FROM nhanvien WHERE IDNV = ?");
        mysqli_stmt_bind_param($stmt, "s", $idnv);
        mysqli_stmt_execute($stmt);
        mysqli_stmt_close($stmt);
        $soLuongXoa++;
    }
}

mysqli_close($link);

// Điều hướng hoặc báo lỗi
if ($soLuongXoa > 0) {
    header("Location: xemthongtinNV.php");
    exit();
} else {
    echo "<script>alert('Bạn chưa chọn nhân viên nào để xóa!'); window.location.href='xoatatca.php';</script>";
}
?>
