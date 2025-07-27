<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chính | Quản lý nhân sự</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h2>Hệ thống Quản lý nhân sự</h2>
    </header>

    <div class="container">
        <!-- User information and logout -->
        <c:if test="${isLoggedIn}">
            <div class="user-info">
                Chào, <strong><c:out value="${username}"/></strong> | 
                <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
            </div>
        </c:if>
        
        <!-- Display messages -->
        <c:if test="${not empty param.message}">
            <div class="message success">
                <c:out value="${param.message}"/>
            </div>
        </c:if>
        
        <c:if test="${not empty param.error}">
            <div class="message error">
                <c:out value="${param.error}"/>
            </div>
        </c:if>

        <!-- Main content -->
        <c:choose>
            <c:when test="${isLoggedIn}">
                <h1>Bảng điều khiển</h1>
                <div class="grid">
                    <div class="card">
                        <i class="fa fa-eye"></i><br>
                        <a href="${pageContext.request.contextPath}/employee">Xem nhân viên</a>
                    </div>
                    <div class="card">
                        <i class="fa fa-building"></i><br>
                        <a href="${pageContext.request.contextPath}/department">Xem phòng ban</a>
                    </div>
                    <div class="card">
                        <i class="fa fa-search"></i><br>
                        <a href="${pageContext.request.contextPath}/employee?action=search">Tìm kiếm</a>
                    </div>
                    <div class="card">
                        <i class="fa fa-plus"></i><br>
                        <a href="${pageContext.request.contextPath}/employee?action=add">Thêm nhân viên</a>
                    </div>
                    <div class="card">
                        <i class="fa fa-plus-circle"></i><br>
                        <a href="${pageContext.request.contextPath}/department?action=add">Thêm phòng ban</a>
                    </div>
                    <div class="card">
                        <i class="fa fa-times-circle"></i><br>
                        <a href="${pageContext.request.contextPath}/employee?action=deleteAll" 
                           onclick="return confirm('Bạn có chắc muốn xóa tất cả nhân viên?')">Xóa tất cả NV</a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <h1>Trang chủ</h1>
                <div class="grid">
                    <div class="card">
                        <i class="fa fa-eye"></i><br>
                        <a href="${pageContext.request.contextPath}/employee">Xem nhân viên</a>
                    </div>
                    <div class="card">
                        <i class="fa fa-building"></i><br>
                        <a href="${pageContext.request.contextPath}/department">Xem phòng ban</a>
                    </div>
                    <div class="card">
                        <i class="fa fa-search"></i><br>
                        <a href="${pageContext.request.contextPath}/employee?action=search">Tìm kiếm</a>
                    </div>
                    <div class="card">
                        <i class="fa fa-sign-in-alt"></i><br>
                        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <footer>
        <p>&copy; 2024 Hệ thống Quản lý nhân sự. Được phát triển bằng JSP & Servlet.</p>
    </footer>
</body>
</html>