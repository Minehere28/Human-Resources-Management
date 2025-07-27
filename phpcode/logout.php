<?php
session_start();
session_destroy(); // Xoá toàn bộ session
header("Location: index.php"); // Quay lại trang chủ
exit();
?>
