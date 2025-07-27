-- Tạo database
CREATE DATABASE IF NOT EXISTS dulieu CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE dulieu;

-- Tạo bảng admin
CREATE TABLE IF NOT EXISTS admin (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(50) NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Tạo bảng phongban
CREATE TABLE IF NOT EXISTS phongban (
    IDPB VARCHAR(10) NOT NULL PRIMARY KEY,
    TenPB VARCHAR(100) NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Tạo bảng nhanvien
CREATE TABLE IF NOT EXISTS nhanvien (
    IDNV VARCHAR(10) NOT NULL PRIMARY KEY,
    Hoten VARCHAR(100) NOT NULL,
    IDPB VARCHAR(10) NOT NULL,
    Diachi VARCHAR(200) NOT NULL,
    FOREIGN KEY (IDPB) REFERENCES phongban(IDPB) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Thêm dữ liệu mẫu cho bảng admin
INSERT INTO admin (username, password) VALUES 
('admin', 'admin123'),
('manager', 'manager123'),
('SuongMai', '28905')
ON DUPLICATE KEY UPDATE password = VALUES(password);

-- Thêm dữ liệu mẫu cho bảng phongban
INSERT INTO phongban (IDPB, TenPB) VALUES 
('IT', 'Phòng Công nghệ thông tin'),
('HR', 'Phòng Nhân sự'),
('ACC', 'Phòng Kế toán'),
('MKT', 'Phòng Marketing'),
('SALE', 'Phòng Kinh doanh')
ON DUPLICATE KEY UPDATE TenPB = VALUES(TenPB);

-- Thêm dữ liệu mẫu cho bảng nhanvien
INSERT INTO nhanvien (IDNV, Hoten, IDPB, Diachi) VALUES 
('NV001', 'Nguyễn Văn An', 'IT', 'Hà Nội'),
('NV002', 'Trần Thị Bình', 'HR', 'TP. Hồ Chí Minh'),
('NV003', 'Lê Văn Cường', 'ACC', 'Đà Nẵng'),
('NV004', 'Phạm Thị Dung', 'MKT', 'Hải Phòng'),
('NV005', 'Hoàng Văn Em', 'SALE', 'Cần Thơ'),
('NV006', 'Vũ Thị Phương', 'IT', 'Hà Nội'),
('NV007', 'Đỗ Văn Giang', 'HR', 'TP. Hồ Chí Minh'),
('NV008', 'Bùi Thị Hạnh', 'ACC', 'Đà Nẵng'),
('NV009', 'Ngô Văn Ích', 'MKT', 'Hải Phòng'),
('NV010', 'Lý Thị Kim', 'SALE', 'Cần Thơ')
ON DUPLICATE KEY UPDATE 
    Hoten = VALUES(Hoten),
    IDPB = VALUES(IDPB),
    Diachi = VALUES(Diachi);

-- Hiển thị thông tin các bảng đã tạo
SELECT 'Bảng admin:' AS Info;
SELECT * FROM admin;

SELECT 'Bảng phongban:' AS Info;
SELECT * FROM phongban;

SELECT 'Bảng nhanvien:' AS Info;
SELECT * FROM nhanvien;

-- Hiển thị thống kê
SELECT 'Thống kê:' AS Info;
SELECT 
    (SELECT COUNT(*) FROM admin) AS 'Số admin',
    (SELECT COUNT(*) FROM phongban) AS 'Số phòng ban',
    (SELECT COUNT(*) FROM nhanvien) AS 'Số nhân viên';