<?php
// Kết nối CSDL
$link = mysqli_connect("localhost", "SuongMai", "28905", "dulieu");
if (!$link) {
    die("Không thể kết nối: " . mysqli_connect_error());
}

if (isset($_GET['check_idnv'])) {
    $checkIDNV = $_GET['check_idnv'];
    $sql = "SELECT IDNV FROM nhanvien WHERE IDNV = ?";
    $stmt = mysqli_prepare($link, $sql);
    mysqli_stmt_bind_param($stmt, "s", $checkIDNV);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);
    echo (mysqli_num_rows($result) > 0) ? "exists" : "ok";
    mysqli_close($link);
    exit();
}

// Lấy dữ liệu từ form POST
if (isset($_POST['IDNV']) && isset($_POST['Hoten']) && isset($_POST['IDPB']) && isset($_POST['Diachi'])) {
    $IDNV = $_POST['IDNV'];
    $Hoten = $_POST['Hoten'];
    $IDPB = $_POST['IDPB'];
    $Diachi = $_POST['Diachi'];

    // Kiểm tra IDNV có tồn tại chưa
    $sql_check = "SELECT * FROM nhanvien WHERE IDNV = ?";
    $stmt = mysqli_prepare($link, $sql_check);
    mysqli_stmt_bind_param($stmt, "s", $IDNV);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);

    if (mysqli_num_rows($result) > 0) {
        echo "<script>alert('IDNV đã tồn tại, vui lòng chọn ID khác!'); window.history.back();</script>";
        mysqli_close($link);
        exit();
    }

    // Nếu không trùng, thêm mới nhân viên
    $sql_insert = "INSERT INTO nhanvien (IDNV, Hoten, IDPB, Diachi) VALUES (?, ?, ?, ?)";
    $stmt_insert = mysqli_prepare($link, $sql_insert);
    mysqli_stmt_bind_param($stmt_insert, "ssss", $IDNV, $Hoten, $IDPB, $Diachi);

    if (mysqli_stmt_execute($stmt_insert)) {
        mysqli_close($link);
        header("Location: xemthongtinNV.php");
        exit();
    } else {
        echo "Lỗi khi thêm dữ liệu: " . mysqli_error($link);
    }
} else {
    echo "Thiếu dữ liệu đầu vào.";
    mysqli_close($link);
    exit();
}
?>
