package com.humanresources.controller;

import com.humanresources.model.bean.Department;
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
 * Servlet for handling department operations
 * @author Human Resources Management System
 */
@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
    
    private DepartmentBO departmentBO;
    
    @Override
    public void init() throws ServletException {
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
                listDepartments(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteDepartment(request, response);
                break;
            default:
                listDepartments(request, response);
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
            response.sendRedirect(request.getContextPath() + "/department");
            return;
        }
        
        switch (action) {
            case "add":
                addDepartment(request, response);
                break;
            case "update":
                updateDepartment(request, response);
                break;
            case "delete":
                deleteDepartment(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/department");
                break;
        }
    }
    
    private void listDepartments(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Department> departments = departmentBO.getAllDepartments();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/department-list.jsp").forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        request.getRequestDispatcher("/department-add.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String idpb = request.getParameter("idpb");
        if (idpb == null || idpb.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/department");
            return;
        }
        
        Department department = departmentBO.getDepartmentById(idpb);
        if (department == null) {
            request.setAttribute("errorMessage", "Không tìm thấy phòng ban với ID: " + idpb);
            listDepartments(request, response);
            return;
        }
        
        request.setAttribute("department", department);
        request.getRequestDispatcher("/department-edit.jsp").forward(request, response);
    }
    
    private void addDepartment(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String idpb = request.getParameter("idpb");
        String tenpb = request.getParameter("tenpb");
        
        boolean success = departmentBO.addDepartment(idpb, tenpb);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/department?message=Thêm phòng ban thành công");
        } else {
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi thêm phòng ban. Vui lòng kiểm tra lại thông tin.");
            request.setAttribute("idpb", idpb);
            request.setAttribute("tenpb", tenpb);
            request.getRequestDispatcher("/department-add.jsp").forward(request, response);
        }
    }
    
    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String idpb = request.getParameter("idpb");
        String tenpb = request.getParameter("tenpb");
        
        boolean success = departmentBO.updateDepartment(idpb, tenpb);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/department?message=Cập nhật phòng ban thành công");
        } else {
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật phòng ban. Vui lòng kiểm tra lại thông tin.");
            
            Department department = new Department(idpb, tenpb);
            request.setAttribute("department", department);
            request.getRequestDispatcher("/department-edit.jsp").forward(request, response);
        }
    }
    
    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String idpb = request.getParameter("idpb");
        if (idpb == null || idpb.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/department");
            return;
        }
        
        boolean success = departmentBO.deleteDepartment(idpb);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/department?message=Xóa phòng ban thành công");
        } else {
            response.sendRedirect(request.getContextPath() + "/department?error=Có lỗi xảy ra khi xóa phòng ban");
        }
    }
}