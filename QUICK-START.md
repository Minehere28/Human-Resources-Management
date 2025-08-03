# 🚀 Hướng dẫn chạy nhanh - Hệ thống Quản lý Nhân sự

## ⚡ Chạy nhanh trong 5 phút

### Bước 1: Khởi động XAMPP
```bash
# Mở XAMPP Control Panel
# Bật Apache và MySQL
```

### Bước 2: Thiết lập Database
1. Mở phpMyAdmin: `http://localhost/phpmyadmin`
2. Tạo database `dulieu`
3. Import file `database-setup.sql`

### Bước 3: Build và Deploy
```bash
# Build project
mvn clean package

# Copy WAR file to Tomcat
cp target/human-resources-management.war /path/to/xampp/tomcat/webapps/

# Khởi động Tomcat từ XAMPP
```

### Bước 4: Truy cập ứng dụng
- URL: `http://localhost:8080/human-resources-management/`
- Login: `admin` / `admin123`

---

## 📋 Checklist

- [ ] XAMPP đã cài đặt
- [ ] MySQL đang chạy (port 3306)
- [ ] Tomcat đang chạy (port 8080)
- [ ] Database `dulieu` đã tạo
- [ ] Data đã import từ `database-setup.sql`
- [ ] File WAR đã deploy thành công

---

## 🔧 Troubleshooting

**Lỗi kết nối database:**
- Kiểm tra MySQL đang chạy
- Kiểm tra username/password trong `DatabaseConnection.java`

**Lỗi 404:**
- Kiểm tra file WAR đã copy đúng thư mục webapps
- Restart Tomcat

**Lỗi encoding tiếng Việt:**
- Đảm bảo database dùng utf8mb4_unicode_ci
- Kiểm tra web.xml có UTF-8 filter

---

## 📊 Dữ liệu mẫu

### Tài khoản admin:
| Username | Password   |
|----------|------------|
| admin    | admin123   |
| manager  | manager123 |
| SuongMai | 28905      |

### Phòng ban mẫu:
- **PB01**: Phòng Nhân sự
- **PB02**: Phòng Hành chính
- **PB03**: Phòng Kế toán
- **PB04**: Phòng Công nghệ thông tin
- **PB05**: Phòng Marketing
- **PB06**: Phòng Kinh doanh

### Nhân viên mẫu:
- NV001: Nguyễn Văn An (PB01)
- NV002: Trần Thị Bình (PB01)
- NV003: Lê Văn Cường (PB02)
- ...và 9 nhân viên khác

---

## 🎯 Chức năng chính

### Không cần đăng nhập:
- ✅ Xem danh sách nhân viên
- ✅ Xem danh sách phòng ban  
- ✅ Tìm kiếm nhân viên
- ✅ Đăng nhập

### Sau khi đăng nhập:
- ✅ Thêm/sửa/xóa nhân viên
- ✅ Thêm/sửa/xóa phòng ban
- ✅ Xóa tất cả nhân viên
- ✅ Tất cả chức năng xem

---

## 📱 Giao diện

- **Responsive**: Tương thích mobile/tablet/desktop
- **Modern UI**: CSS3 với gradient và animations
- **Icons**: Font Awesome 6.5
- **Colors**: Gradient cam-hồng chuyên nghiệp

---

## 🔒 Bảo mật

- ✅ Session management
- ✅ SQL injection prevention
- ✅ XSS protection
- ✅ Input validation
- ✅ CSRF protection

---

**Happy coding! 🎉**