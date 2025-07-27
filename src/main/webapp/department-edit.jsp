<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa phòng ban | Quản lý nhân sự</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h2>Hệ thống Quản lý nhân sự</h2>
    </header>

    <div class="container">
        <!-- Navigation -->
        <div class="nav">
            <a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> Trang chủ</a>
            <a href="${pageContext.request.contextPath}/department"><i class="fa fa-list"></i> Danh sách phòng ban</a>
        </div>

        <div class="form-container">
            <h2><i class="fa fa-edit"></i> Chỉnh sửa thông tin phòng ban</h2>
            
            <!-- Display error message -->
            <c:if test="${not empty errorMessage}">
                <div class="message error">
                    <c:out value="${errorMessage}"/>
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/department" method="post">
                <input type="hidden" name="action" value="update">
                
                <div class="form-group">
                    <label for="idpb">ID Phòng ban: <span style="color: red;">*</span></label>
                    <input type="text" 
                           id="idpb" 
                           name="idpb" 
                           value="<c:out value='${department.idpb}'/>"
                           readonly
                           style="background-color: #f8f9fa; cursor: not-allowed;">
                    <small style="color: #666; font-style: italic;">ID phòng ban không thể thay đổi</small>
                </div>
                
                <div class="form-group">
                    <label for="tenpb">Tên phòng ban: <span style="color: red;">*</span></label>
                    <input type="text" 
                           id="tenpb" 
                           name="tenpb" 
                           value="<c:out value='${department.tenpb}'/>"
                           placeholder="Nhập tên phòng ban" 
                           maxlength="100"
                           required>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-save"></i> Cập nhật
                    </button>
                    <a href="${pageContext.request.contextPath}/department" class="btn btn-secondary">
                        <i class="fa fa-times"></i> Hủy bỏ
                    </a>
                </div>
            </form>
            
            <div style="margin-top: 30px; padding: 15px; background-color: #fff3cd; border: 1px solid #ffeaa7; border-radius: 5px;">
                <h4><i class="fa fa-exclamation-triangle" style="color: #f39c12;"></i> Lưu ý quan trọng:</h4>
                <p style="margin-top: 10px; color: #856404;">
                    Khi bạn thay đổi tên phòng ban, tất cả nhân viên thuộc phòng ban này sẽ vẫn giữ nguyên. 
                    Chỉ có tên phòng ban được cập nhật trong hệ thống.
                </p>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 Hệ thống Quản lý nhân sự. Được phát triển bằng JSP & Servlet.</p>
    </footer>

    <script>
        // Focus on the editable field
        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById('tenpb').focus();
            document.getElementById('tenpb').select();
        });
    </script>
</body>
</html>