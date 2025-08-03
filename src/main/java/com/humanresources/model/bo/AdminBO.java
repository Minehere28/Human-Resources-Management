package com.humanresources.model.bo;

import com.humanresources.model.bean.Admin;
import com.humanresources.model.dao.AdminDAO;

/**
 * Business Object for Admin operations
 * @author Human Resources Management System
 */
public class AdminBO {
    
    private AdminDAO adminDAO;
    
    public AdminBO() {
        this.adminDAO = new AdminDAO();
    }
    
    /**
     * Authenticate admin login
     * @param username Admin username
     * @param password Admin password
     * @return Admin object if authentication successful, null otherwise
     */
    public Admin login(String username, String password) {
        // Validate input
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            return null;
        }
        
        // Trim whitespace
        username = username.trim();
        password = password.trim();
        
        // Authenticate with database
        return adminDAO.authenticate(username, password);
    }
    
    /**
     * Register new admin
     * @param username Admin username
     * @param password Admin password
     * @return true if registration successful, false otherwise
     */
    public boolean registerAdmin(String username, String password) {
        // Validate input
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            return false;
        }
        
        // Trim whitespace
        username = username.trim();
        password = password.trim();
        
        // Validate username length
        if (username.length() < 3 || username.length() > 50) {
            return false;
        }
        
        // Validate password length
        if (password.length() < 4 || password.length() > 50) {
            return false;
        }
        
        // Check if admin already exists
        if (adminDAO.adminExists(username)) {
            return false;
        }
        
        // Create new admin
        Admin admin = new Admin(username, password);
        return adminDAO.addAdmin(admin);
    }
    
    /**
     * Change admin password
     * @param username Admin username
     * @param oldPassword Current password
     * @param newPassword New password
     * @return true if password changed successfully, false otherwise
     */
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        // Validate input
        if (username == null || username.trim().isEmpty() || 
            oldPassword == null || oldPassword.trim().isEmpty() ||
            newPassword == null || newPassword.trim().isEmpty()) {
            return false;
        }
        
        // Trim whitespace
        username = username.trim();
        oldPassword = oldPassword.trim();
        newPassword = newPassword.trim();
        
        // Validate new password length
        if (newPassword.length() < 4 || newPassword.length() > 50) {
            return false;
        }
        
        // Verify current password
        Admin admin = adminDAO.authenticate(username, oldPassword);
        if (admin == null) {
            return false;
        }
        
        // Update password
        return adminDAO.updatePassword(username, newPassword);
    }
    
    /**
     * Check if admin exists
     * @param username Admin username
     * @return true if exists, false otherwise
     */
    public boolean isAdminExists(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        return adminDAO.adminExists(username.trim());
    }
}