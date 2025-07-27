package com.humanresources.model.bean;

import java.io.Serializable;

/**
 * Bean class representing Admin table
 * @author Human Resources Management System
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;
    
    // Default constructor
    public Admin() {
    }
    
    // Constructor with parameters
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Admin admin = (Admin) obj;
        return username != null ? username.equals(admin.username) : admin.username == null;
    }
    
    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}