<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm phòng ban | Quản lý nhân sự</title>
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
            <h2><i class="fa fa-plus-circle"></i> Thêm phòng ban mới</h2>
            
            <!-- Display error message -->
            <c:if test="${not empty errorMessage}">
                <div class="message error">
                    <c:out value="${errorMessage}"/>
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/department" method="post">
                <input type="hidden" name="action" value="add">
                
                <div class="form-group">
                    <label for="idpb">ID Phòng ban: <span style="color: red;">*</span></label>
                    <input type="text" 
                           id="idpb" 
                           name="idpb" 
                           value="<c:out value='${idpb}'/>"
                           placeholder="Ví dụ: PB01, PB02, ..." 
                           maxlength="10"
                           pattern="PB[0-9]{2,}"
                           title="ID phòng ban phải có định dạng PBxx (ví dụ: PB01)"
                           required>
                    <small style="color: #666; font-style: italic;">Định dạng: PBxx (ví dụ: PB01, PB02, PB03)</small>
                </div>
                
                <div class="form-group">
                    <label for="tenpb">Tên phòng ban: <span style="color: red;">*</span></label>
                    <input type="text" 
                           id="tenpb" 
                           name="tenpb" 
                           value="<c:out value='${tenpb}'/>"
                           placeholder="Nhập tên phòng ban" 
                           maxlength="100"
                           required>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-save"></i> Thêm phòng ban
                    </button>
                    <a href="${pageContext.request.contextPath}/department" class="btn btn-secondary">
                        <i class="fa fa-times"></i> Hủy bỏ
                    </a>
                </div>
            </form>
            
            <div style="margin-top: 30px; padding: 15px; background-color: #f8f9fa; border-radius: 5px;">
                <h4><i class="fa fa-lightbulb"></i> Gợi ý tên phòng ban:</h4>
                <ul style="margin-top: 10px; margin-left: 20px;">
                    <li>Phòng Nhân sự</li>
                    <li>Phòng Hành chính</li>
                    <li>Phòng Kế toán</li>
                    <li>Phòng Công nghệ thông tin</li>
                    <li>Phòng Marketing</li>
                    <li>Phòng Kinh doanh</li>
                    <li>Phòng Sản xuất</li>
                    <li>Phòng Chất lượng</li>
                </ul>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 Hệ thống Quản lý nhân sự. Được phát triển bằng JSP & Servlet.</p>
    </footer>

    <script>
        // Auto-suggest next department ID
        document.addEventListener('DOMContentLoaded', function() {
            // You could fetch the next available ID via AJAX here
            // For now, just focus on the input field
            document.getElementById('idpb').focus();
        });
        
        // Format ID input
        document.getElementById('idpb').addEventListener('input', function() {
            let value = this.value.toUpperCase();
            // Auto-add PB prefix if user starts typing numbers
            if (/^[0-9]/.test(value)) {
                value = 'PB' + value;
                this.value = value;
            }
        });
    </script>
</body>
</html>