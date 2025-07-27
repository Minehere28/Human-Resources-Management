<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách nhân viên | Quản lý nhân sự</title>
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
            <a href="${pageContext.request.contextPath}/employee?action=search"><i class="fa fa-search"></i> Tìm kiếm</a>
            <c:if test="${sessionScope.isLoggedIn}">
                <a href="${pageContext.request.contextPath}/employee?action=add"><i class="fa fa-plus"></i> Thêm nhân viên</a>
            </c:if>
        </div>

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

        <h1><i class="fa fa-users"></i> Danh sách nhân viên</h1>

        <c:choose>
            <c:when test="${not empty employees}">
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>ID Nhân viên</th>
                                <th>Họ tên</th>
                                <th>ID Phòng ban</th>
                                <th>Địa chỉ</th>
                                <c:if test="${sessionScope.isLoggedIn}">
                                    <th>Thao tác</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="employee" items="${employees}">
                                <tr>
                                    <td><c:out value="${employee.idnv}"/></td>
                                    <td><c:out value="${employee.hoten}"/></td>
                                    <td><c:out value="${employee.idpb}"/></td>
                                    <td><c:out value="${employee.diachi}"/></td>
                                    <c:if test="${sessionScope.isLoggedIn}">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/employee?action=edit&idnv=${employee.idnv}" 
                                               class="btn btn-success btn-small">
                                                <i class="fa fa-edit"></i> Sửa
                                            </a>
                                            <a href="${pageContext.request.contextPath}/employee?action=delete&idnv=${employee.idnv}" 
                                               class="btn btn-danger btn-small"
                                               onclick="return confirm('Bạn có chắc muốn xóa nhân viên ${employee.hoten}?')">
                                                <i class="fa fa-trash"></i> Xóa
                                            </a>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <div style="text-align: center; margin-top: 20px;">
                    <p><strong>Tổng số nhân viên: ${employees.size()}</strong></p>
                    <c:if test="${sessionScope.isLoggedIn && employees.size() > 0}">
                        <a href="${pageContext.request.contextPath}/employee?action=deleteAll" 
                           class="btn btn-danger"
                           onclick="return confirm('Bạn có chắc muốn xóa TẤT CẢ nhân viên? Thao tác này không thể hoàn tác!')">
                            <i class="fa fa-times-circle"></i> Xóa tất cả nhân viên
                        </a>
                    </c:if>
                </div>
            </c:when>
            <c:otherwise>
                <div style="text-align: center; padding: 40px;">
                    <i class="fa fa-info-circle" style="font-size: 48px; color: #6c757d; margin-bottom: 20px;"></i>
                    <h3>Không có nhân viên nào trong hệ thống</h3>
                    <c:if test="${sessionScope.isLoggedIn}">
                        <a href="${pageContext.request.contextPath}/employee?action=add" class="btn btn-primary">
                            <i class="fa fa-plus"></i> Thêm nhân viên đầu tiên
                        </a>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <footer>
        <p>&copy; 2024 Hệ thống Quản lý nhân sự. Được phát triển bằng JSP & Servlet.</p>
    </footer>
</body>
</html>