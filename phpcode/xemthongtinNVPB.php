<?php
// Kết nối đến MySQL
$link = mysqli_connect("localhost", "SuongMai", "28905") or die("Không thể kết nối đến máy chủ MySQL");
mysqli_select_db($link, "dulieu");

// Kiểm tra tham số IDPB (GET hoặc POST)
if (!isset($_REQUEST['IDPB']) || empty($_REQUEST['IDPB'])) {
    echo '<p style="color:red;">Không có mã phòng ban được truyền vào.</p>';
    exit();
}

$idpb = $_REQUEST['IDPB'];

// Truy vấn thông tin phòng ban
$sql_pb = "SELECT * FROM phongban WHERE IDPB = '$idpb'";
$rs_pb = mysqli_query($link, $sql_pb);

// Kiểm tra tồn tại
if (mysqli_num_rows($rs_pb) == 0) {
    echo "<p style='color:red;'>Không tìm thấy phòng ban với IDPB = $idpb</p>";
    exit();
}

$pb = mysqli_fetch_array($rs_pb, MYSQLI_ASSOC);

// Hiển thị thông tin phòng ban
echo '<h2>Thông tin Phòng ban</h2>';
echo '<p><strong>Tên phòng ban:</strong> ' . $pb['Tenpb'] . '</p>';
echo '<p><strong>Mô tả:</strong> ' . $pb['Mota'] . '</p>';

// Truy vấn danh sách nhân viên
$sql_nv = "SELECT * FROM nhanvien WHERE IDPB = '$idpb'";
$rs_nv = mysqli_query($link, $sql_nv);

if (mysqli_num_rows($rs_nv) > 0) {
    echo '<table border="1" width="100%" cellpadding="8" cellspacing="0">';
    echo '<tr><th>IDNV</th><th>Họ tên</th><th>IDPB</th><th>Địa chỉ</th></tr>';
    while ($nv = mysqli_fetch_array($rs_nv, MYSQLI_ASSOC)) {
        echo '<tr>';
        echo '<td>' . $nv['IDNV'] . '</td>';
        echo '<td>' . $nv['Hoten'] . '</td>';
        echo '<td>' . $nv['IDPB'] . '</td>';
        echo '<td>' . $nv['Diachi'] . '</td>';
        echo '</tr>';
    }
    echo '</table>';
} else {
    echo '<p><i>Không có nhân viên nào trong phòng ban này.</i></p>';
}

// Giải phóng bộ nhớ và đóng kết nối
mysqli_free_result($rs_pb);
mysqli_free_result($rs_nv);
mysqli_close($link);
?>
