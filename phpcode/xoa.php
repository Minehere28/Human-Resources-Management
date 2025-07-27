<?php
// Kết nối
$link = mysqli_connect("localhost", "SuongMai", "28905", "dulieu");
if (!$link) {
    die("Không thể kết nối: " . mysqli_connect_error());
}

// Truy vấn dữ liệu
$rs = mysqli_query($link, "SELECT * FROM nhanvien");

// Hiển thị bảng
echo '<table border="1" width="100%">';
echo '<caption><h2>Danh sách nhân viên</h2></caption>';
echo '<tr><th>IDNV</th><th>Họ tên</th><th>IDPB</th><th>Địa chỉ</th><th>Xóa</th></tr>';

while ($row = mysqli_fetch_array($rs, MYSQLI_ASSOC)) {
    echo '<tr>';
    echo '<td>'.$row['IDNV'].'</td>';
    echo '<td>'.$row['Hoten'].'</td>';
    echo '<td>'.$row['IDPB'].'</td>';
    echo '<td>'.$row['Diachi'].'</td>';
    echo '<td><a href="xulixoa.php?IDNV=' . urlencode($row['IDNV']) . '">Xóa</a></td>';
    echo '</tr>';
}

echo '</table>';

// Giải phóng & đóng kết nối
mysqli_free_result($rs);
mysqli_close($link);
?>
