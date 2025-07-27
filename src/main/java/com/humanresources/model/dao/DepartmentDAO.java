package com.humanresources.model.dao;

import com.humanresources.model.bean.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Department (phongban) table
 * @author Human Resources Management System
 */
public class DepartmentDAO {
    
    /**
     * Get all departments
     * @return List of all departments
     */
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM phongban ORDER BY IDPB";
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Department department = new Department();
                department.setIdpb(resultSet.getString("IDPB"));
                department.setTenpb(resultSet.getString("TenPB"));
                departments.add(department);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all departments: " + e.getMessage());
            e.printStackTrace();
        }
        
        return departments;
    }
    
    /**
     * Get department by ID
     * @param idpb Department ID
     * @return Department object or null if not found
     */
    public Department getDepartmentById(String idpb) {
        Department department = null;
        String sql = "SELECT * FROM phongban WHERE IDPB = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, idpb);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    department = new Department();
                    department.setIdpb(resultSet.getString("IDPB"));
                    department.setTenpb(resultSet.getString("TenPB"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting department by ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return department;
    }
    
    /**
     * Add new department
     * @param department Department object to add
     * @return true if successful, false otherwise
     */
    public boolean addDepartment(Department department) {
        String sql = "INSERT INTO phongban (IDPB, TenPB) VALUES (?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, department.getIdpb());
            statement.setString(2, department.getTenpb());
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding department: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Update department
     * @param department Department object with updated information
     * @return true if successful, false otherwise
     */
    public boolean updateDepartment(Department department) {
        String sql = "UPDATE phongban SET TenPB = ? WHERE IDPB = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, department.getTenpb());
            statement.setString(2, department.getIdpb());
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating department: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete department by ID
     * @param idpb Department ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteDepartment(String idpb) {
        String sql = "DELETE FROM phongban WHERE IDPB = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, idpb);
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting department: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Check if department exists
     * @param idpb Department ID
     * @return true if exists, false otherwise
     */
    public boolean departmentExists(String idpb) {
        String sql = "SELECT COUNT(*) FROM phongban WHERE IDPB = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, idpb);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking if department exists: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Get all department IDs for dropdown list
     * @return List of department IDs
     */
    public List<String> getAllDepartmentIds() {
        List<String> departmentIds = new ArrayList<>();
        String sql = "SELECT IDPB FROM phongban ORDER BY IDPB";
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                departmentIds.add(resultSet.getString("IDPB"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting department IDs: " + e.getMessage());
            e.printStackTrace();
        }
        
        return departmentIds;
    }
}