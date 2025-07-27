# Hệ thống Quản lý Nhân sự (Human Resources Management System)

Một ứng dụng web quản lý nhân sự được xây dựng bằng JSP, Servlet theo mô hình MVC, kết nối với MySQL database.

## 🌟 Tính năng chính

- **Đăng nhập/Đăng xuất**: Xác thực người dùng
- **Quản lý nhân viên**: Thêm, sửa, xóa, xem danh sách nhân viên
- **Quản lý phòng ban**: Thêm, sửa, xóa, xem danh sách phòng ban
- **Tìm kiếm**: Tìm kiếm nhân viên theo tên hoặc phòng ban
- **Giao diện đẹp**: UI hiện đại với CSS3 và Font Awesome icons

## 🏗️ Kiến trúc hệ thống

### Mô hình MVC (Model-View-Controller)

```
src/main/java/com/humanresources/
├── controller/          # Controller Layer (Servlet)
│   ├── HomeServlet.java
│   ├── LoginServlet.java
│   ├── LogoutServlet.java
│   ├── EmployeeServlet.java
│   └── DepartmentServlet.java
├── model/               # Model Layer
│   ├── bean/           # Entity Classes (Bean)
│   │   ├── Admin.java
│   │   ├── Employee.java
│   │   └── Department.java
│   ├── dao/            # Data Access Object
│   │   ├── DatabaseConnection.java
│   │   ├── AdminDAO.java
│   │   ├── EmployeeDAO.java
│   │   └── DepartmentDAO.java
│   └── bo/             # Business Object
│       ├── AdminBO.java
│       ├── EmployeeBO.java
│       └── DepartmentBO.java
```

```
src/main/webapp/         # View Layer (JSP)
├── css/style.css       # Stylesheet
├── index.jsp           # Trang chủ
├── login.jsp           # Trang đăng nhập
├── employee-list.jsp   # Danh sách nhân viên
├── employee-add.jsp    # Thêm nhân viên
├── employee-search.jsp # Tìm kiếm nhân viên
├── department-list.jsp # Danh sách phòng ban
└── WEB-INF/web.xml    # Cấu hình web app
```

## 📋 Yêu cầu hệ thống

- **JDK**: 11 hoặc cao hơn
- **Maven**: 3.6 hoặc cao hơn
- **XAMPP**: Cho MySQL và Tomcat
- **IDE**: Eclipse, IntelliJ IDEA, hoặc VS Code

## 🚀 Hướng dẫn cài đặt

### Bước 1: Thiết lập XAMPP
1. Tải và cài đặt XAMPP từ [https://www.apachefriends.org/](https://www.apachefriends.org/)
2. Khởi động Apache và MySQL trong XAMPP Control Panel
3. Mở phpMyAdmin (http://localhost/phpmyadmin)

### Bước 2: Thiết lập Database
1. Trong phpMyAdmin, tạo database mới tên `dulieu`
2. Import file `database-setup.sql` vào database `dulieu`
3. Hoặc chạy script SQL sau:

```sql
-- Tạo database
CREATE DATABASE IF NOT EXISTS dulieu CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE dulieu;

-- Tạo các bảng và dữ liệu mẫu (xem file database-setup.sql)
```

### Bước 3: Cấu hình Database Connection
Kiểm tra file `DatabaseConnection.java` và cập nhật thông tin kết nối nếu cần:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/dulieu";
private static final String DB_USERNAME = "root";      // Username mặc định XAMPP
private static final String DB_PASSWORD = "";          // Password mặc định XAMPP (trống)
```

### Bước 4: Build và Deploy
1. Clone hoặc tải project
2. Mở terminal trong thư mục project
3. Chạy lệnh Maven:

```bash
# Build project
mvn clean compile

# Tạo file WAR
mvn clean package

# Hoặc chạy trực tiếp với Tomcat (nếu có plugin)
mvn tomcat7:run
```

### Bước 5: Deploy lên Tomcat
1. Copy file `target/human-resources-management.war` 
2. Paste vào thư mục `htdocs/tomcat/webapps/` trong XAMPP
3. Khởi động Tomcat từ XAMPP Control Panel
4. Truy cập: `http://localhost:8080/human-resources-management/`

## 🔑 Thông tin đăng nhập

Hệ thống có sẵn 3 tài khoản admin:

| Username | Password   |
|----------|------------|
| admin    | admin123   |
| manager  | manager123 |
| SuongMai | 28905      |

## 📊 Cấu trúc Database

### Bảng `admin`
```sql
CREATE TABLE admin (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);
```

### Bảng `phongban`
```sql
CREATE TABLE phongban (
    IDPB VARCHAR(10) PRIMARY KEY,
    TenPB VARCHAR(100) NOT NULL
);
```

### Bảng `nhanvien`
```sql
CREATE TABLE nhanvien (
    IDNV VARCHAR(10) PRIMARY KEY,
    Hoten VARCHAR(100) NOT NULL,
    IDPB VARCHAR(10) NOT NULL,
    Diachi VARCHAR(200) NOT NULL,
    FOREIGN KEY (IDPB) REFERENCES phongban(IDPB)
);
```

## 🖥️ Screenshots

### Trang chủ
- Hiển thị các chức năng chính
- Phân quyền dựa trên trạng thái đăng nhập

### Quản lý nhân viên
- Xem danh sách nhân viên
- Thêm/sửa/xóa nhân viên
- Kiểm tra trùng lặp ID nhân viên

### Tìm kiếm
- Tìm kiếm theo tên nhân viên
- Tìm kiếm theo phòng ban

## 🔧 Chức năng chi tiết

### Người dùng chưa đăng nhập
- Xem danh sách nhân viên (chỉ đọc)
- Xem danh sách phòng ban (chỉ đọc)  
- Tìm kiếm nhân viên
- Đăng nhập

### Người dùng đã đăng nhập
- Tất cả chức năng của người dùng chưa đăng nhập
- Thêm/sửa/xóa nhân viên
- Thêm/sửa/xóa phòng ban
- Xóa tất cả nhân viên
- Đăng xuất

## 🛠️ Công nghệ sử dụng

- **Backend**: Java, JSP, Servlet
- **Database**: MySQL 8.0
- **Frontend**: HTML5, CSS3, JavaScript
- **Framework**: None (Pure Java EE)
- **Build Tool**: Maven
- **Server**: Apache Tomcat 9
- **Icons**: Font Awesome 6.5

## 📱 Responsive Design

Ứng dụng được thiết kế responsive, tương thích với:
- Desktop (1200px+)
- Tablet (768px - 1199px) 
- Mobile (< 768px)

## 🔒 Bảo mật

- Session management cho đăng nhập
- SQL injection prevention với PreparedStatement
- XSS protection với JSTL escaping
- Input validation ở cả client và server side

## 🐛 Xử lý lỗi

- Error pages cho 404, 500
- Validation messages
- User-friendly error messages
- Database connection error handling

## 📝 Logs

Hệ thống ghi log các hoạt động:
- Database operations
- Authentication attempts
- Error tracking

## 🤝 Đóng góp

1. Fork project
2. Tạo feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Tạo Pull Request

## 📞 Hỗ trợ

Nếu gặp vấn đề, hãy:
1. Kiểm tra XAMPP đang chạy MySQL và Tomcat
2. Xem logs trong Tomcat/logs/
3. Kiểm tra kết nối database
4. Đảm bảo đã import đúng database

## 📄 License

Dự án này được phát hành dưới MIT License.

---

**Phát triển bởi**: Hệ thống Quản lý Nhân sự JSP/Servlet  
**Năm**: 2024  
**Công nghệ**: Java EE, MySQL, HTML5, CSS3 
