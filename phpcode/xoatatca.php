<?php
            $link = mysqli_connect("localhost", "SuongMai", "28905", "dulieu");
            if (!$link) {
                die("Không thể kết nối: " . mysqli_connect_error());
            }

            $result = mysqli_query($link, "SELECT * FROM nhanvien");

            echo '<form method="post" action="xulixoatatca.php">';
            echo '<table border="1" width="100%">';
            echo '<caption><h2>Danh sách nhân viên</h2></caption>';
            echo '<tr><th>IDNV</th><th>Họ tên</th><th>IDPB</th><th>Địa chỉ</th><th>Chọn</th></tr>';

            while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
                echo "<tr>";
                echo "<td>{$row['IDNV']}</td>";
                echo "<td>{$row['Hoten']}</td>";
                echo "<td>{$row['IDPB']}</td>";
                echo "<td>{$row['Diachi']}</td>";
                echo '<td><input type="checkbox" name="IDNV[]" value="' . $row['IDNV'] . '"></td>';
                echo "</tr>";
            }
        echo '<tr><td align="right" colspan="4"><input type="submit" value="Xóa nhân viên"></td></tr>';
            echo '</table>';
            echo '</form>';
            mysqli_close($link);
            ?>
       