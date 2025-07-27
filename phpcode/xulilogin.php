<?php
session_start(); // BẮT BUỘC có để dùng session

// Kết nối đến database
$link = mysqli_connect('localhost', 'SuongMai', '28905') or die('Không thể kết nối: ' . mysqli_error($link));
$db_selected = mysqli_select_db($link, 'dulieu');
if (!$db_selected) {
    die('Không chọn được CSDL: ' . mysqli_error($link));
}


$username = isset($_POST['username']) ? trim($_POST['username']) : '';
$password = isset($_POST['password']) ? trim($_POST['password']) : '';


if (empty($username) || empty($password)) {
    header("Location: login.php?error=Vui lòng nhập đủ thông tin đăng nhập");
    exit();
}

// Truy vấn kiểm tra tài khoản
$sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
$stmt = mysqli_prepare($link, $sql);
mysqli_stmt_bind_param($stmt, "ss", $username, $password);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);

if (mysqli_num_rows($result) == 1) {
    $_SESSION['username'] = $username; 
    header("Location: index.php");   
    exit();
} else {
    header("Location: login.php?error=Thông tin đăng nhập không đúng");
    exit();
}
?>
