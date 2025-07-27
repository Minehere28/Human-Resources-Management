package com.humanresources.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling home page
 * @author Human Resources Management System
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set UTF-8 encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Get session to check login status
        HttpSession session = request.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("isLoggedIn") != null);
        String username = null;
        
        if (isLoggedIn) {
            username = (String) session.getAttribute("username");
        }
        
        // Set attributes for JSP
        request.setAttribute("isLoggedIn", isLoggedIn);
        request.setAttribute("username", username);
        
        // Forward to home page
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}