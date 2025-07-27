<?php
// Kết nối
$link = mysqli_connect("localhost", "SuongMai", "28905") or die("Không thể kết nối đến MySQL");
mysqli_select_db($link, "dulieu");

// Truy vấn bảng phòng ban
$rs = mysqli_query($link, 'SELECT * FROM phongban');

echo '<table border="1" width="100%">';
echo '<caption><strong>Dữ liệu truy xuất từ bảng Phòng ban</strong></caption>';
echo '<tr><th>IDPB</th><th>Tên PB</th><th>Mô tả</th><th>Xem</th></tr>';

while ($row = mysqli_fetch_array($rs, MYSQLI_BOTH)) {
    $idpb = urlencode($row['IDPB']); // đảm bảo URL an toàn
    echo '<tr>';
    echo '<td>' . $row['IDPB'] . '</td>';
    echo '<td>' . $row['Tenpb'] . '</td>';
    echo '<td>' . $row['Mota'] . '</td>';
    echo '<td><a href="xemthongtinNVPB.php?IDPB=' . $idpb . '">Xem</a></td>';
    echo '</tr>';
}
echo '</table>';

mysqli_free_result($rs);
mysqli_close($link);
?>
