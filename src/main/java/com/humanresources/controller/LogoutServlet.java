package com.humanresources.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling admin logout
 * @author Human Resources Management System
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get current session
        HttpSession session = request.getSession(false);
        
        // Invalidate session if it exists
        if (session != null) {
            session.invalidate();
        }
        
        // Redirect to home page with logout message
        response.sendRedirect(request.getContextPath() + "/?message=Đăng xuất thành công");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}