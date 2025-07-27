package com.humanresources.model.dao;

import com.humanresources.model.bean.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for Admin table
 * @author Human Resources Management System
 */
public class AdminDAO {
    
    /**
     * Authenticate admin user
     * @param username Admin username
     * @param password Admin password
     * @return Admin object if authentication successful, null otherwise
     */
    public Admin authenticate(String username, String password) {
        Admin admin = null;
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, username);
            statement.setString(2, password);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    admin = new Admin();
                    admin.setUsername(resultSet.getString("username"));
                    admin.setPassword(resultSet.getString("password"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error authenticating admin: " + e.getMessage());
            e.printStackTrace();
        }
        
        return admin;
    }
    
    /**
     * Add new admin user
     * @param admin Admin object to add
     * @return true if successful, false otherwise
     */
    public boolean addAdmin(Admin admin) {
        String sql = "INSERT INTO admin (username, password) VALUES (?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding admin: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Update admin password
     * @param username Admin username
     * @param newPassword New password
     * @return true if successful, false otherwise
     */
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE admin SET password = ? WHERE username = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, newPassword);
            statement.setString(2, username);
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating admin password: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Check if admin exists
     * @param username Admin username
     * @return true if exists, false otherwise
     */
    public boolean adminExists(String username) {
        String sql = "SELECT COUNT(*) FROM admin WHERE username = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, username);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking if admin exists: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
}