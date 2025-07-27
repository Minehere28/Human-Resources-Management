<?php
// Kiểm tra xem các dữ liệu cần thiết có tồn tại không
if (isset($_REQUEST['IDPB']) && isset($_REQUEST['txtTenpb']) && isset($_REQUEST['txtMota'])) {
    $idpb = trim($_REQUEST['IDPB']); 
    $tenpb = trim($_REQUEST['txtTenpb']);
    $mota = trim($_REQUEST['txtMota']);

    $link = mysqli_connect('localhost', 'SuongMai', '28905') or die('Could not connect: ' . mysqli_error());
    $db_selected = mysqli_select_db($link, 'dulieu');
    $sql = "UPDATE phongban SET Tenpb = '$tenpb', Mota = '$mota' WHERE IDPB = '$idpb'";
    $rs = mysqli_query($link, $sql);

    // Kiểm tra kết quả và phản hồi
    if ($rs) {
        // Thành công → Chuyển về lại danh sách phòng ban
        header("Location: capnhat.php");
        exit();
    } else {
        echo "<p style='color:red;'>Cập nhật thất bại: " . mysqli_error($link) . "</p>";
    }

    // Đóng kết nối
    mysqli_close($link);
} else {
    echo "<p style='color:red;'>Thiếu dữ liệu, không thể cập nhật.</p>";
}
?>
