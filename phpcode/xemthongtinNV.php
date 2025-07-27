<?php
// Khai báo kết nối
$link = mysqli_connect("localhost", "SuongMai", "28905") or die ("Không
thể kết nối đến máy chủ MySQL");
// Chọn cơ sở dữ liệu
mysqli_select_db($link, "dulieu") ;
$rs = mysqli_query($link, 'SELECT * FROM nhanvien');
echo '<table border="1" width="100%">';
echo '<caption>Dữ liệu truy xuất từ bảng Nhân viên</caption>';
// in Tiêu đề của bảng
echo '<TR><TH>IDNV</TH><TH>Họ tên</TH><TH>IDPB</TH><TH>Địa chỉ</TH></TR>';
while ($row = mysqli_fetch_array($rs, MYSQLI_BOTH)) {
    echo '<tr><td>'.$row['IDNV'].'</td><td>'.$row['Hoten'].'</td><td>'.$row['IDPB'].
    '</td><td>'.$row['Diachi'].'</td></tr>';
}
echo '</table>';
//Giải phóng tập các bản ghi trong $rs
mysqli_free_result($rs);
// Đóng kết nối
mysqli_close($link);
?>