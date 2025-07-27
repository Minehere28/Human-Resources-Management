package com.humanresources.model.dao;

import com.humanresources.model.bean.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Employee (nhanvien) table
 * @author Human Resources Management System
 */
public class EmployeeDAO {
    
    /**
     * Get all employees
     * @return List of all employees
     */
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien ORDER BY IDNV";
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdnv(resultSet.getString("IDNV"));
                employee.setHoten(resultSet.getString("Hoten"));
                employee.setIdpb(resultSet.getString("IDPB"));
                employee.setDiachi(resultSet.getString("Diachi"));
                employees.add(employee);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all employees: " + e.getMessage());
            e.printStackTrace();
        }
        
        return employees;
    }
    
    /**
     * Get employee by ID
     * @param idnv Employee ID
     * @return Employee object or null if not found
     */
    public Employee getEmployeeById(String idnv) {
        Employee employee = null;
        String sql = "SELECT * FROM nhanvien WHERE IDNV = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, idnv);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    employee = new Employee();
                    employee.setIdnv(resultSet.getString("IDNV"));
                    employee.setHoten(resultSet.getString("Hoten"));
                    employee.setIdpb(resultSet.getString("IDPB"));
                    employee.setDiachi(resultSet.getString("Diachi"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting employee by ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return employee;
    }
    
    /**
     * Add new employee
     * @param employee Employee object to add
     * @return true if successful, false otherwise
     */
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO nhanvien (IDNV, Hoten, IDPB, Diachi) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, employee.getIdnv());
            statement.setString(2, employee.getHoten());
            statement.setString(3, employee.getIdpb());
            statement.setString(4, employee.getDiachi());
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Update employee
     * @param employee Employee object with updated information
     * @return true if successful, false otherwise
     */
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE nhanvien SET Hoten = ?, IDPB = ?, Diachi = ? WHERE IDNV = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, employee.getHoten());
            statement.setString(2, employee.getIdpb());
            statement.setString(3, employee.getDiachi());
            statement.setString(4, employee.getIdnv());
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete employee by ID
     * @param idnv Employee ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteEmployee(String idnv) {
        String sql = "DELETE FROM nhanvien WHERE IDNV = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, idnv);
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete all employees
     * @return true if successful, false otherwise
     */
    public boolean deleteAllEmployees() {
        String sql = "DELETE FROM nhanvien";
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected >= 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting all employees: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Search employees by name
     * @param name Name keyword to search
     * @return List of employees matching the search criteria
     */
    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien WHERE Hoten LIKE ? ORDER BY IDNV";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, "%" + name + "%");
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setIdnv(resultSet.getString("IDNV"));
                    employee.setHoten(resultSet.getString("Hoten"));
                    employee.setIdpb(resultSet.getString("IDPB"));
                    employee.setDiachi(resultSet.getString("Diachi"));
                    employees.add(employee);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching employees by name: " + e.getMessage());
            e.printStackTrace();
        }
        
        return employees;
    }
    
    /**
     * Get employees by department ID
     * @param idpb Department ID
     * @return List of employees in the specified department
     */
    public List<Employee> getEmployeesByDepartment(String idpb) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien WHERE IDPB = ? ORDER BY IDNV";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, idpb);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setIdnv(resultSet.getString("IDNV"));
                    employee.setHoten(resultSet.getString("Hoten"));
                    employee.setIdpb(resultSet.getString("IDPB"));
                    employee.setDiachi(resultSet.getString("Diachi"));
                    employees.add(employee);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting employees by department: " + e.getMessage());
            e.printStackTrace();
        }
        
        return employees;
    }
    
    /**
     * Check if employee exists
     * @param idnv Employee ID
     * @return true if exists, false otherwise
     */
    public boolean employeeExists(String idnv) {
        String sql = "SELECT COUNT(*) FROM nhanvien WHERE IDNV = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, idnv);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking if employee exists: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
}