package com.humanresources.model.bo;

import com.humanresources.model.bean.Department;
import com.humanresources.model.dao.DepartmentDAO;
import java.util.List;

/**
 * Business Object for Department operations
 * @author Human Resources Management System
 */
public class DepartmentBO {
    
    private DepartmentDAO departmentDAO;
    
    public DepartmentBO() {
        this.departmentDAO = new DepartmentDAO();
    }
    
    /**
     * Get all departments
     * @return List of all departments
     */
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }
    
    /**
     * Get department by ID
     * @param idpb Department ID
     * @return Department object or null if not found
     */
    public Department getDepartmentById(String idpb) {
        if (idpb == null || idpb.trim().isEmpty()) {
            return null;
        }
        
        return departmentDAO.getDepartmentById(idpb.trim());
    }
    
    /**
     * Add new department
     * @param idpb Department ID
     * @param tenpb Department name
     * @return true if successful, false otherwise
     */
    public boolean addDepartment(String idpb, String tenpb) {
        // Validate input
        if (idpb == null || idpb.trim().isEmpty() ||
            tenpb == null || tenpb.trim().isEmpty()) {
            return false;
        }
        
        // Trim whitespace
        idpb = idpb.trim();
        tenpb = tenpb.trim();
        
        // Validate length
        if (idpb.length() > 10 || tenpb.length() > 100) {
            return false;
        }
        
        // Check if department ID already exists
        if (departmentDAO.departmentExists(idpb)) {
            return false;
        }
        
        // Create new department
        Department department = new Department(idpb, tenpb);
        return departmentDAO.addDepartment(department);
    }
    
    /**
     * Update department information
     * @param idpb Department ID
     * @param tenpb Department name
     * @return true if successful, false otherwise
     */
    public boolean updateDepartment(String idpb, String tenpb) {
        // Validate input
        if (idpb == null || idpb.trim().isEmpty() ||
            tenpb == null || tenpb.trim().isEmpty()) {
            return false;
        }
        
        // Trim whitespace
        idpb = idpb.trim();
        tenpb = tenpb.trim();
        
        // Validate length
        if (tenpb.length() > 100) {
            return false;
        }
        
        // Check if department exists
        if (!departmentDAO.departmentExists(idpb)) {
            return false;
        }
        
        // Update department
        Department department = new Department(idpb, tenpb);
        return departmentDAO.updateDepartment(department);
    }
    
    /**
     * Delete department by ID
     * @param idpb Department ID
     * @return true if successful, false otherwise
     */
    public boolean deleteDepartment(String idpb) {
        if (idpb == null || idpb.trim().isEmpty()) {
            return false;
        }
        
        idpb = idpb.trim();
        
        // Check if department exists
        if (!departmentDAO.departmentExists(idpb)) {
            return false;
        }
        
        return departmentDAO.deleteDepartment(idpb);
    }
    
    /**
     * Check if department exists
     * @param idpb Department ID
     * @return true if exists, false otherwise
     */
    public boolean isDepartmentExists(String idpb) {
        if (idpb == null || idpb.trim().isEmpty()) {
            return false;
        }
        
        return departmentDAO.departmentExists(idpb.trim());
    }
    
    /**
     * Get all department IDs for dropdown list
     * @return List of department IDs
     */
    public List<String> getAllDepartmentIds() {
        return departmentDAO.getAllDepartmentIds();
    }
    
    /**
     * Get total number of departments
     * @return Total number of departments
     */
    public int getTotalDepartments() {
        return getAllDepartments().size();
    }
}