<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa nhân viên | Quản lý nhân sự</title>
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
            <a href="${pageContext.request.contextPath}/employee"><i class="fa fa-list"></i> Danh sách nhân viên</a>
        </div>

        <div class="form-container">
            <h2><i class="fa fa-user-edit"></i> Chỉnh sửa thông tin nhân viên</h2>
            
            <!-- Display error message -->
            <c:if test="${not empty errorMessage}">
                <div class="message error">
                    <c:out value="${errorMessage}"/>
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/employee" method="post">
                <input type="hidden" name="action" value="update">
                
                <div class="form-group">
                    <label for="idnv">ID Nhân viên: <span style="color: red;">*</span></label>
                    <input type="text" 
                           id="idnv" 
                           name="idnv" 
                           value="<c:out value='${employee.idnv}'/>"
                           readonly
                           style="background-color: #f8f9fa; cursor: not-allowed;">
                    <small style="color: #666; font-style: italic;">ID nhân viên không thể thay đổi</small>
                </div>
                
                <div class="form-group">
                    <label for="hoten">Họ tên: <span style="color: red;">*</span></label>
                    <input type="text" 
                           id="hoten" 
                           name="hoten" 
                           value="<c:out value='${employee.hoten}'/>"
                           placeholder="Nhập họ tên nhân viên" 
                           maxlength="100"
                           required>
                </div>
                
                <div class="form-group">
                    <label for="idpb">Phòng ban: <span style="color: red;">*</span></label>
                    <select id="idpb" name="idpb" required>
                        <option value="">-- Chọn phòng ban --</option>
                        <c:forEach var="deptId" items="${departmentIds}">
                            <option value="<c:out value='${deptId}'/>" 
                                    <c:if test="${deptId == employee.idpb}">selected</c:if>>
                                <c:out value="${deptId}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="diachi">Địa chỉ: <span style="color: red;">*</span></label>
                    <textarea id="diachi" 
                              name="diachi" 
                              placeholder="Nhập địa chỉ nhân viên" 
                              maxlength="200"
                              rows="3"
                              required><c:out value="${employee.diachi}"/></textarea>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-save"></i> Cập nhật
                    </button>
                    <a href="${pageContext.request.contextPath}/employee" class="btn btn-secondary">
                        <i class="fa fa-times"></i> Hủy bỏ
                    </a>
                </div>
            </form>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 Hệ thống Quản lý nhân sự. Được phát triển bằng JSP & Servlet.</p>
    </footer>
</body>
</html>