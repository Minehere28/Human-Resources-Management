package com.humanresources.controller;

import com.humanresources.model.bean.Admin;
import com.humanresources.model.bo.AdminBO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling admin login
 * @author Human Resources Management System
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    private AdminBO adminBO;
    
    @Override
    public void init() throws ServletException {
        adminBO = new AdminBO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Forward to login page
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set UTF-8 encoding for Vietnamese characters
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Get form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate input
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng nhập đủ thông tin đăng nhập");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        // Authenticate user
        Admin admin = adminBO.login(username, password);
        
        if (admin != null) {
            // Login successful
            HttpSession session = request.getSession();
            session.setAttribute("username", admin.getUsername());
            session.setAttribute("isLoggedIn", true);
            
            // Redirect to home page
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            // Login failed
            request.setAttribute("errorMessage", "Thông tin đăng nhập không đúng");
            request.setAttribute("username", username); // Keep username for user convenience
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}