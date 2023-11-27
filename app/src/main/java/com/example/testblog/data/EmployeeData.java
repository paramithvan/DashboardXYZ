package com.example.testblog.data;

public class EmployeeData {
    public int id;
    public int photo;
    public String employeeId;
    public String fullName;
    public String role;
    public String status;

    public EmployeeData() {
    }

    public EmployeeData(int photo, int id, String employeeId, String fullName, String role, String status) {
        this.photo = photo;
        this.id = id;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.role = role;
        this.status = status;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
