<?php
// Kết nối CSDL
$link = mysqli_connect("localhost", "SuongMai", "28905", "dulieu");
if (!$link) {
    die("Không thể kết nối: " . mysqli_connect_error());
}

// Lấy danh sách phòng ban để hiển thị trong combo box
$sql = "SELECT IDPB FROM phongban";
$result = mysqli_query($link, $sql);
?>

<!DOCTYPE html>
<html>
<head>
    <title>Thêm nhân viên</title>
    <meta charset="UTF-8">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f0f2f5;
        }

        /* Container tổng */
        .main-container {
            display: flex;
            justify-content: center;
            align-items: center;
            padding-top: 100px;
            min-height: 100vh;
        }

        /* Form */
        .form-container {
            background: white;
            padding: 30px 35px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 340px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
            color: #444;
        }

        input[type="text"], select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 25px;
            background-color: black;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: black;
        }
    </style>
</head>
<body>

    <div class="main-container">
        <div class="form-container">
            <h2>Thêm nhân viên</h2>
            <form method="POST" action="xulichenthongtin.php">

                <label for="IDNV">IDNV:</label>
                <input type="text" name="IDNV" id="IDNV" required>
                <div id="idnv-error" style="color:red;font-size:13px;"></div>


                <label for="Hoten">Họ tên:</label>
                <input type="text" name="Hoten" id="Hoten" required>

                <label for="IDPB">IDPB:</label>
                <select name="IDPB" required>
                    <?php
                    while ($row = mysqli_fetch_assoc($result)) {
                        echo "<option value='{$row['IDPB']}'>{$row['IDPB']}</option>";
                    }
                    ?>
                </select>

                <label for="Diachi">Địa chỉ:</label>
                <input type="text" name="Diachi" required>

                <input type="submit" value="Thêm nhân viên" id="submit-btn">
            </form>
        </div>
    </div>
<script>
    var idnvInput = document.getElementById("IDNV");
    var hotenInput = document.getElementById("Hoten");
    var errorDiv = document.getElementById("idnv-error");
    var submitBtn = document.getElementById("submit-btn");

    hotenInput.addEventListener("focus", function () {
        var idnv = idnvInput.value.trim();
        if (idnv === "") {
            errorDiv.textContent = "";
            submitBtn.disabled = false;
            hotenInput.disabled = false;
            return;
        }
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "xulichenthongtin.php?check_idnv=" + encodeURIComponent(idnv), true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                if (xhr.responseText === "exists") {
                    errorDiv.textContent = "IDNV đã tồn tại! Vui lòng nhập IDNV khác.";
                    submitBtn.disabled = true;
                    hotenInput.disabled = true;
                } else {
                    errorDiv.textContent = "";
                    submitBtn.disabled = false;
                    hotenInput.disabled = false;
                }
            }
        };
        xhr.send();
    });

    idnvInput.addEventListener("input", function () {
        errorDiv.textContent = "";
        submitBtn.disabled = false;
        hotenInput.disabled = false;
    });
</script>
</body>
</html>
