package com.humanresources.controller;

import com.humanresources.model.bean.Employee;
import com.humanresources.model.bo.EmployeeBO;
import com.humanresources.model.bo.DepartmentBO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling employee operations
 * @author Human Resources Management System
 */
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    
    private EmployeeBO employeeBO;
    private DepartmentBO departmentBO;
    
    @Override
    public void init() throws ServletException {
        employeeBO = new EmployeeBO();
        departmentBO = new DepartmentBO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set UTF-8 encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listEmployees(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            case "deleteAll":
                deleteAllEmployees(request, response);
                break;
            case "search":
                searchEmployees(request, response);
                break;
            case "checkExists":
                checkEmployeeExists(request, response);
                break;
            default:
                listEmployees(request, response);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set UTF-8 encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/employee");
            return;
        }
        
        switch (action) {
            case "add":
                addEmployee(request, response);
                break;
            case "update":
                updateEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            case "deleteAll":
                deleteAllEmployees(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/employee");
                break;
        }
    }
    
    private void listEmployees(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Employee> employees = employeeBO.getAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/employee-list.jsp").forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Get department list for dropdown
        List<String> departmentIds = departmentBO.getAllDepartmentIds();
        request.setAttribute("departmentIds", departmentIds);
        request.getRequestDispatcher("/employee-add.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String idnv = request.getParameter("idnv");
        if (idnv == null || idnv.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/employee");
            return;
        }
        
        Employee employee = employeeBO.getEmployeeById(idnv);
        if (employee == null) {
            request.setAttribute("errorMessage", "Không tìm thấy nhân viên với ID: " + idnv);
            listEmployees(request, response);
            return;
        }
        
        // Get department list for dropdown
        List<String> departmentIds = departmentBO.getAllDepartmentIds();
        request.setAttribute("employee", employee);
        request.setAttribute("departmentIds", departmentIds);
        request.getRequestDispatcher("/employee-edit.jsp").forward(request, response);
    }
    
    private void addEmployee(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String idnv = request.getParameter("idnv");
        String hoten = request.getParameter("hoten");
        String idpb = request.getParameter("idpb");
        String diachi = request.getParameter("diachi");
        
        boolean success = employeeBO.addEmployee(idnv, hoten, idpb, diachi);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/employee?message=Thêm nhân viên thành công");
        } else {
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi thêm nhân viên. Vui lòng kiểm tra lại thông tin.");
            request.setAttribute("idnv", idnv);
            request.setAttribute("hoten", hoten);
            request.setAttribute("idpb", idpb);
            request.setAttribute("diachi", diachi);
            
            // Get department list for dropdown
            List<String> departmentIds = departmentBO.getAllDepartmentIds();
            request.setAttribute("departmentIds", departmentIds);
            request.getRequestDispatcher("/employee-add.jsp").forward(request, response);
        }
    }
    
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String idnv = request.getParameter("idnv");
        String hoten = request.getParameter("hoten");
        String idpb = request.getParameter("idpb");
        String diachi = request.getParameter("diachi");
        
        boolean success = employeeBO.updateEmployee(idnv, hoten, idpb, diachi);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/employee?message=Cập nhật nhân viên thành công");
        } else {
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật nhân viên. Vui lòng kiểm tra lại thông tin.");
            
            Employee employee = new Employee(idnv, hoten, idpb, diachi);
            request.setAttribute("employee", employee);
            
            // Get department list for dropdown
            List<String> departmentIds = departmentBO.getAllDepartmentIds();
            request.setAttribute("departmentIds", departmentIds);
            request.getRequestDispatcher("/employee-edit.jsp").forward(request, response);
        }
    }
    
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String idnv = request.getParameter("idnv");
        if (idnv == null || idnv.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/employee");
            return;
        }
        
        boolean success = employeeBO.deleteEmployee(idnv);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/employee?message=Xóa nhân viên thành công");
        } else {
            response.sendRedirect(request.getContextPath() + "/employee?error=Có lỗi xảy ra khi xóa nhân viên");
        }
    }
    
    private void deleteAllEmployees(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        boolean success = employeeBO.deleteAllEmployees();
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/employee?message=Xóa tất cả nhân viên thành công");
        } else {
            response.sendRedirect(request.getContextPath() + "/employee?error=Có lỗi xảy ra khi xóa tất cả nhân viên");
        }
    }
    
    private void searchEmployees(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        String searchType = request.getParameter("searchType");
        
        List<Employee> employees;
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            employees = employeeBO.getAllEmployees();
        } else if ("name".equals(searchType)) {
            employees = employeeBO.searchEmployeesByName(searchTerm);
        } else if ("department".equals(searchType)) {
            employees = employeeBO.getEmployeesByDepartment(searchTerm);
        } else {
            employees = employeeBO.getAllEmployees();
        }
        
        request.setAttribute("employees", employees);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("searchType", searchType);
        request.getRequestDispatcher("/employee-search.jsp").forward(request, response);
    }
    
    private void checkEmployeeExists(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idnv = request.getParameter("idnv");
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        if (idnv != null && !idnv.trim().isEmpty() && employeeBO.isEmployeeExists(idnv)) {
            response.getWriter().write("exists");
        } else {
            response.getWriter().write("available");
        }
    }
}