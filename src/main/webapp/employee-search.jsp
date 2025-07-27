<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tìm kiếm nhân viên | Quản lý nhân sự</title>
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
            <c:if test="${sessionScope.isLoggedIn}">
                <a href="${pageContext.request.contextPath}/employee?action=add"><i class="fa fa-plus"></i> Thêm nhân viên</a>
            </c:if>
        </div>

        <h1><i class="fa fa-search"></i> Tìm kiếm nhân viên</h1>

        <!-- Search form -->
        <div class="search-form">
            <form action="${pageContext.request.contextPath}/employee" method="get">
                <input type="hidden" name="action" value="search">
                <div class="form-row">
                    <div class="form-group">
                        <label for="searchType">Tìm kiếm theo:</label>
                        <select id="searchType" name="searchType">
                            <option value="name" <c:if test="${searchType == 'name'}">selected</c:if>>Tên nhân viên</option>
                            <option value="department" <c:if test="${searchType == 'department'}">selected</c:if>>Phòng ban</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="searchTerm">Từ khóa:</label>
                        <input type="text" 
                               id="searchTerm" 
                               name="searchTerm" 
                               value="<c:out value='${searchTerm}'/>"
                               placeholder="Nhập từ khóa tìm kiếm">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">
                            <i class="fa fa-search"></i> Tìm kiếm
                        </button>
                        <a href="${pageContext.request.contextPath}/employee?action=search" class="btn btn-secondary">
                            <i class="fa fa-refresh"></i> Làm mới
                        </a>
                    </div>
                </div>
            </form>
        </div>

        <!-- Search results -->
        <c:if test="${not empty searchTerm}">
            <div class="message info">
                Kết quả tìm kiếm cho "<strong><c:out value="${searchTerm}"/></strong>" 
                theo <strong><c:out value="${searchType == 'name' ? 'tên nhân viên' : 'phòng ban'}"/></strong>
            </div>
        </c:if>

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
                    <p><strong>Tìm thấy: ${employees.size()} nhân viên</strong></p>
                </div>
            </c:when>
            <c:when test="${not empty searchTerm}">
                <div style="text-align: center; padding: 40px;">
                    <i class="fa fa-search" style="font-size: 48px; color: #6c757d; margin-bottom: 20px;"></i>
                    <h3>Không tìm thấy nhân viên nào</h3>
                    <p>Không có nhân viên nào phù hợp với từ khóa "<c:out value="${searchTerm}"/>"</p>
                </div>
            </c:when>
            <c:otherwise>
                <div style="text-align: center; padding: 40px;">
                    <i class="fa fa-search" style="font-size: 48px; color: #6c757d; margin-bottom: 20px;"></i>
                    <h3>Nhập từ khóa để tìm kiếm</h3>
                    <p>Bạn có thể tìm kiếm nhân viên theo tên hoặc phòng ban</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <footer>
        <p>&copy; 2024 Hệ thống Quản lý nhân sự. Được phát triển bằng JSP & Servlet.</p>
    </footer>
</body>
</html>