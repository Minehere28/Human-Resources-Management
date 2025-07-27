<?php
$link = mysqli_connect('localhost', 'SuongMai', '28905') or die('Could not connect: ' . mysqli_error());
$db_selected = mysqli_select_db($link, 'dulieu');
$rs= mysqli_query($link, 'SELECT * FROM phongban');

echo '<Form action="xulicapnhat.php">';
echo '<table border="1" width="100%">';
echo '<caption>Dữ liệu Phòng ban</caption>';
echo '<tr><th>Mã phòng</th><th>Tên phòng</th><th>Mô tả</th><th>Cập nhật</th></tr>';
while ($row = mysqli_fetch_array($rs, MYSQLI_BOTH)) {
    echo '<tr><td>'.$row['IDPB']. '</td><td>'.$row['Tenpb'].'</td><td>'.$row['Mota']. '</td><td>
    <a href="form_capnhat.php?IDPB='.$row['IDPB'].'">Cập nhật</a></td></tr>';
}

echo '</table>';
?>
