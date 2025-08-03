<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập | Quản lý nhân sự</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h2>Hệ thống Quản lý nhân sự</h2>
    </header>

    <div class="container">
        <div class="form-container">
            <h2><i class="fa fa-sign-in-alt"></i> Đăng nhập</h2>
            
            <!-- Display error message -->
            <c:if test="${not empty errorMessage}">
                <div class="message error">
                    <c:out value="${errorMessage}"/>
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="username">Tên đăng nhập:</label>
                    <input type="text" 
                           id="username" 
                           name="username" 
                           value="<c:out value='${username}'/>"
                           placeholder="Nhập tên đăng nhập" 
                           required>
                </div>
                
                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="password" 
                           id="password" 
                           name="password" 
                           placeholder="Nhập mật khẩu" 
                           required>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" style="width: 100%;">
                        <i class="fa fa-sign-in-alt"></i> Đăng nhập
                    </button>
                </div>
            </form>
            
            <div style="text-align: center; margin-top: 20px;">
                <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">
                    <i class="fa fa-home"></i> Về trang chủ
                </a>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 Hệ thống Quản lý nhân sự. Được phát triển bằng JSP & Servlet.</p>
    </footer>
</body>
</html>