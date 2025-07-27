<?php
if (isset($_GET['IDNV'])) {
    $idnv = $_GET['IDNV'];

    // Kết nối
    $link = mysqli_connect("localhost", "SuongMai", "28905", "dulieu");
    if (!$link) {
        die("Không thể kết nối: " . mysqli_connect_error());
    }

    // Xóa nhân viên
    $stmt = mysqli_prepare($link, "DELETE FROM nhanvien WHERE IDNV = ?");
    mysqli_stmt_bind_param($stmt, "s", $idnv);
    mysqli_stmt_execute($stmt);

    // Kiểm tra có bị lỗi không
    if (mysqli_stmt_affected_rows($stmt) > 0) {
        // Thành công: quay lại xoa.php
        header("Location: xoa.php");
    } else {
        echo "Không tìm thấy nhân viên có ID: $idnv hoặc lỗi khi xóa.";
    }

    mysqli_stmt_close($stmt);
    mysqli_close($link);
} else {
    echo "Không có IDNV được cung cấp để xóa.";
}
?>
