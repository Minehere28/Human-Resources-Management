package com.humanresources.model.bean;

import java.io.Serializable;

/**
 * Bean class representing Employee (nhanvien) table
 * @author Human Resources Management System
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idnv;
    private String hoten;
    private String idpb;
    private String diachi;
    
    // Default constructor
    public Employee() {
    }
    
    // Constructor with parameters
    public Employee(String idnv, String hoten, String idpb, String diachi) {
        this.idnv = idnv;
        this.hoten = hoten;
        this.idpb = idpb;
        this.diachi = diachi;
    }
    
    // Getters and Setters
    public String getIdnv() {
        return idnv;
    }
    
    public void setIdnv(String idnv) {
        this.idnv = idnv;
    }
    
    public String getHoten() {
        return hoten;
    }
    
    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
    
    public String getIdpb() {
        return idpb;
    }
    
    public void setIdpb(String idpb) {
        this.idpb = idpb;
    }
    
    public String getDiachi() {
        return diachi;
    }
    
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "idnv='" + idnv + '\'' +
                ", hoten='" + hoten + '\'' +
                ", idpb='" + idpb + '\'' +
                ", diachi='" + diachi + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return idnv != null ? idnv.equals(employee.idnv) : employee.idnv == null;
    }
    
    @Override
    public int hashCode() {
        return idnv != null ? idnv.hashCode() : 0;
    }
}