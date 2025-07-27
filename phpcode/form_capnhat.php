<?php
$myID = $_REQUEST['IDPB'] ;
$link = mysqli_connect('localhost', 'SuongMai', '28905') or die('Could not connect: ' . mysqli_error());
$db_selected = mysqli_select_db($link, 'dulieu');
$rs= mysqli_query($link, 'SELECT * FROM phongban WHERE IDPB = "'.$myID.'"');
$row = mysqli_fetch_array($rs, MYSQLI_BOTH);
?>
<html>
    <body>
        <form action="xulicapnhat.php?IDPB= <?php echo $row['IDPB'];?> " method="post">
            <table border="1" width="100%">
                <caption>Thông tin phòng ban</caption>
                <tr>
                    <td>Mã phòng ban</td>
                    <td><input type="text" size="20" name="txtIDPB" readonly value="<?php echo $row['IDPB']; ?> "></td>
                </tr>
                <tr>
                    <td>Tên phòng</td>
                    <td><input type="text" size="20" name="txtTenpb" value="<?php echo $row['Tenpb']; ?>"></td>
                </tr>
                <tr>
                    <td>Mô tả</td>
                    <td><input type="text" size="20" name="txtMota" value="<?php echo $row['Mota']; ?>"></td>
                </tr>
                <tr align="center">
                    <td colspan="2"><input type="submit" value="OK"><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>


