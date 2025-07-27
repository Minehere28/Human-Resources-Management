package com.humanresources.model.bean;

import java.io.Serializable;

/**
 * Bean class representing Department (phongban) table
 * @author Human Resources Management System
 */
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idpb;
    private String tenpb;
    
    // Default constructor
    public Department() {
    }
    
    // Constructor with parameters
    public Department(String idpb, String tenpb) {
        this.idpb = idpb;
        this.tenpb = tenpb;
    }
    
    // Getters and Setters
    public String getIdpb() {
        return idpb;
    }
    
    public void setIdpb(String idpb) {
        this.idpb = idpb;
    }
    
    public String getTenpb() {
        return tenpb;
    }
    
    public void setTenpb(String tenpb) {
        this.tenpb = tenpb;
    }
    
    @Override
    public String toString() {
        return "Department{" +
                "idpb='" + idpb + '\'' +
                ", tenpb='" + tenpb + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department department = (Department) obj;
        return idpb != null ? idpb.equals(department.idpb) : department.idpb == null;
    }
    
    @Override
    public int hashCode() {
        return idpb != null ? idpb.hashCode() : 0;
    }
}