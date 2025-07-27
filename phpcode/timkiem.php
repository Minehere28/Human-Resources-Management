<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tìm kiếm nhân viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        form {
            margin-bottom: 20px;
        }
        .search-bar {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        input[type="text"] {
            padding: 4px;
            font-size: 14px;
        }
        input[type="submit"] {
            padding: 5px 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>

<h2>Tìm kiếm nhân viên</h2>

<form id="searchForm" class="search-bar" method="get" action="timkiem.php" onsubmit="return buildQuery();">
    <label><input type="radio" name="tieuchi" value="IDNV" required> IDNV</label>
    <label><input type="radio" name="tieuchi" value="Hoten"> Họ tên</label>
    <label><input type="radio" name="tieuchi" value="IDPB"> IDPB</label>
    <label><input type="radio" name="tieuchi" value="Diachi"> Địa chỉ</label>

    <input type="text" id="valueInput" placeholder="Nhập thông tin..." required>
    <input type="submit" value="Tìm kiếm">
</form>

<hr>

<?php
// Kết nối MySQL
$link = mysqli_connect("localhost", "root", "", "DULIEU");
if (!$link) {
    die("Không thể kết nối MySQL: " . mysqli_connect_error());
}

$sql = "";
if (!empty($_GET)) {
    $fields = ['IDNV', 'Hoten', 'IDPB', 'Diachi'];
    foreach ($fields as $field) {
        if (isset($_GET[$field])) {
            $value = mysqli_real_escape_string($link, $_GET[$field]);
            $sql = "SELECT * FROM nhanvien WHERE $field LIKE '%$value%'";
            break;
        }
    }
}

if ($sql != "") {
    $result = mysqli_query($link, $sql);
    if (mysqli_num_rows($result) > 0) {
        echo "<table border='1' cellpadding='5'><tr><th>IDNV</th><th>Họ tên</th><th>IDPB</th><th>Địa chỉ</th></tr>";
        while ($row = mysqli_fetch_assoc($result)) {
            echo "<tr>
                    <td>{$row['IDNV']}</td>
                    <td>{$row['Hoten']}</td>
                    <td>{$row['IDPB']}</td>
                    <td>{$row['Diachi']}</td>
                  </tr>";
        }
        echo "</table>";
    } else {
        echo "Không tìm thấy kết quả.";
    }
}

mysqli_close($link);
?>

<script>
function buildQuery() {
    const radios = document.getElementsByName("tieuchi");
    let selectedField = "";
    for (const radio of radios) {
        if (radio.checked) {
            selectedField = radio.value;
            break;
        }
    }

    const inputValue = document.getElementById("valueInput").value.trim();
    if (selectedField && inputValue) {
        const url = `timkiem.php?${selectedField}=${encodeURIComponent(inputValue)}`;
        window.location.href = url;
    }

    return false; // Ngăn submit mặc định
}
</script>

</body>
</html>
