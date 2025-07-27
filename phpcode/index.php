<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Trang chính | Quản lý nhân sự</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    body {
      background: linear-gradient(135deg, #fdfcfb 0%, #e2d1c3 100%);
      color: #333;
    }

    header {
      background: linear-gradient(90deg, #ff6a00, #ee0979);
      color: #fff;
      padding: 25px;
      text-align: center;
      font-size: 24px;
      letter-spacing: 1px;
      font-weight: bold;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    .container {
      max-width: 1000px;
      margin: 40px auto;
      padding: 25px;
      background: #fff;
      border-radius: 12px;
      box-shadow: 0 8px 20px rgba(0,0,0,0.1);
    }

    h1 {
      text-align: center;
      margin-bottom: 30px;
      color: #ff1493;
    }

    .grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
      gap: 25px;
    }

    .card {
      background: linear-gradient(145deg, #ffe3ec, #ffc6d0);
      padding: 25px;
      border-radius: 16px;
      text-align: center;
      transition: 0.4s ease-in-out;
      box-shadow: 0 6px 12px rgba(0,0,0,0.1);
      position: relative;
      overflow: hidden;
    }

    .card:hover {
      transform: translateY(-8px) scale(1.02);
      box-shadow: 0 12px 20px rgba(0,0,0,0.2);
      background: linear-gradient(145deg, #f2709c, #ff9472);
      color: white;
    }

    .card i {
      font-size: 40px;
      margin-bottom: 15px;
      color: #ff5e5e;
      background: white;
      padding: 15px;
      border-radius: 50%;
      transition: 0.3s;
    }

    .card:hover i {
      background: rgba(255, 255, 255, 0.2);
      color: #fff;
      transform: scale(1.1);
    }

    .card a {
      text-decoration: none;
      color: #d6336c;
      font-weight: bold;
      display: inline-block;
      margin-top: 10px;
      transition: color 0.3s;
    }

    .card:hover a {
      color: #fff;
    }

    .logout {
      text-align: right;
      margin-bottom: 15px;
    }

    .logout a {
      color: #dc3545;
      text-decoration: none;
      font-weight: bold;
      transition: 0.3s;
    }

    .logout a:hover {
      color: #ff0000;
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <header>
    <h2>Hệ thống Quản lý nhân sự</h2>
  </header>

  <div class="container">
    <?php
    session_start();
    if (isset($_SESSION['username'])) {
      echo '<div class="logout">Chào, <b>' . htmlspecialchars($_SESSION['username']) . '</b> | <a href="logout.php">Đăng xuất</a></div>';
      echo '<h1>Bảng điều khiển</h1>';
      echo '<div class="grid">
        <div class="card"><i class="fa fa-eye"></i><br><a href="xemthongtinNV.php">Xem nhân viên</a></div>
        <div class="card"><i class="fa fa-building"></i><br><a href="xemthongtinPB.php">Xem phòng ban</a></div>
        <div class="card"><i class="fa fa-search"></i><br><a href="timkiem.php">Tìm kiếm</a></div>
        <div class="card"><i class="fa fa-plus"></i><br><a href="chenthongtin.php">Chèn thông tin</a></div>
        <div class="card"><i class="fa fa-pen"></i><br><a href="capnhat.php">Cập nhật</a></div>
        <div class="card"><i class="fa fa-trash"></i><br><a href="xoa.php">Xóa</a></div>
        <div class="card"><i class="fa fa-times-circle"></i><br><a href="xoatatca.php">Xóa tất cả</a></div>
      </div>';
    } else {
      echo '<h1>Trang chủ</h1>';
      echo '<div class="grid">
        <div class="card"><i class="fa fa-eye"></i><br><a href="xemthongtinNV.php">Xem nhân viên</a></div>
        <div class="card"><i class="fa fa-building"></i><br><a href="xemthongtinPB.php">Xem phòng ban</a></div>
        <div class="card"><i class="fa fa-search"></i><br><a href="timkiem.php">Tìm kiếm</a></div>
        <div class="card"><i class="fa fa-sign-in-alt"></i><br><a href="login.php">Đăng nhập</a></div>
      </div>';
    }
    ?>
  </div>
</body>
</html>
