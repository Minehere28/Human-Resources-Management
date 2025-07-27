package com.humanresources.model.bo;

import com.humanresources.model.bean.Employee;
import com.humanresources.model.dao.EmployeeDAO;
import com.humanresources.model.dao.DepartmentDAO;
import java.util.List;

/**
 * Business Object for Employee operations
 * @author Human Resources Management System
 */
public class EmployeeBO {
    
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;
    
    public EmployeeBO() {
        this.employeeDAO = new EmployeeDAO();
        this.departmentDAO = new DepartmentDAO();
    }
    
    /**
     * Get all employees
     * @return List of all employees
     */
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
    
    /**
     * Get employee by ID
     * @param idnv Employee ID
     * @return Employee object or null if not found
     */
    public Employee getEmployeeById(String idnv) {
        if (idnv == null || idnv.trim().isEmpty()) {
            return null;
        }
        
        return employeeDAO.getEmployeeById(idnv.trim());
    }
    
    /**
     * Add new employee
     * @param idnv Employee ID
     * @param hoten Employee name
     * @param idpb Department ID
     * @param diachi Employee address
     * @return true if successful, false otherwise
     */
    public boolean addEmployee(String idnv, String hoten, String idpb, String diachi) {
        // Validate input
        if (idnv == null || idnv.trim().isEmpty() ||
            hoten == null || hoten.trim().isEmpty() ||
            idpb == null || idpb.trim().isEmpty() ||
            diachi == null || diachi.trim().isEmpty()) {
            return false;
        }
        
        // Trim whitespace
        idnv = idnv.trim();
        hoten = hoten.trim();
        idpb = idpb.trim();
        diachi = diachi.trim();
        
        // Validate length
        if (idnv.length() > 10 || hoten.length() > 100 || 
            idpb.length() > 10 || diachi.length() > 200) {
            return false;
        }
        
        // Check if employee ID already exists
        if (employeeDAO.employeeExists(idnv)) {
            return false;
        }
        
        // Check if department exists
        if (!departmentDAO.departmentExists(idpb)) {
            return false;
        }
        
        // Create new employee
        Employee employee = new Employee(idnv, hoten, idpb, diachi);
        return employeeDAO.addEmployee(employee);
    }
    
    /**
     * Update employee information
     * @param idnv Employee ID
     * @param hoten Employee name
     * @param idpb Department ID
     * @param diachi Employee address
     * @return true if successful, false otherwise
     */
    public boolean updateEmployee(String idnv, String hoten, String idpb, String diachi) {
        // Validate input
        if (idnv == null || idnv.trim().isEmpty() ||
            hoten == null || hoten.trim().isEmpty() ||
            idpb == null || idpb.trim().isEmpty() ||
            diachi == null || diachi.trim().isEmpty()) {
            return false;
        }
        
        // Trim whitespace
        idnv = idnv.trim();
        hoten = hoten.trim();
        idpb = idpb.trim();
        diachi = diachi.trim();
        
        // Validate length
        if (hoten.length() > 100 || idpb.length() > 10 || diachi.length() > 200) {
            return false;
        }
        
        // Check if employee exists
        if (!employeeDAO.employeeExists(idnv)) {
            return false;
        }
        
        // Check if department exists
        if (!departmentDAO.departmentExists(idpb)) {
            return false;
        }
        
        // Update employee
        Employee employee = new Employee(idnv, hoten, idpb, diachi);
        return employeeDAO.updateEmployee(employee);
    }
    
    /**
     * Delete employee by ID
     * @param idnv Employee ID
     * @return true if successful, false otherwise
     */
    public boolean deleteEmployee(String idnv) {
        if (idnv == null || idnv.trim().isEmpty()) {
            return false;
        }
        
        idnv = idnv.trim();
        
        // Check if employee exists
        if (!employeeDAO.employeeExists(idnv)) {
            return false;
        }
        
        return employeeDAO.deleteEmployee(idnv);
    }
    
    /**
     * Delete all employees
     * @return true if successful, false otherwise
     */
    public boolean deleteAllEmployees() {
        return employeeDAO.deleteAllEmployees();
    }
    
    /**
     * Search employees by name
     * @param name Name keyword to search
     * @return List of employees matching the search criteria
     */
    public List<Employee> searchEmployeesByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllEmployees(); // Return all if search term is empty
        }
        
        return employeeDAO.searchEmployeesByName(name.trim());
    }
    
    /**
     * Get employees by department
     * @param idpb Department ID
     * @return List of employees in the specified department
     */
    public List<Employee> getEmployeesByDepartment(String idpb) {
        if (idpb == null || idpb.trim().isEmpty()) {
            return getAllEmployees(); // Return all if department ID is empty
        }
        
        return employeeDAO.getEmployeesByDepartment(idpb.trim());
    }
    
    /**
     * Check if employee exists
     * @param idnv Employee ID
     * @return true if exists, false otherwise
     */
    public boolean isEmployeeExists(String idnv) {
        if (idnv == null || idnv.trim().isEmpty()) {
            return false;
        }
        
        return employeeDAO.employeeExists(idnv.trim());
    }
    
    /**
     * Get total number of employees
     * @return Total number of employees
     */
    public int getTotalEmployees() {
        return getAllEmployees().size();
    }
}