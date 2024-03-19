package com.example.RegisterLogin.Repo;

import com.example.RegisterLogin.Entity.Role;

public class LoginRepo {
    String message;
    Boolean status;
    Role role;
    public LoginRepo() {
    }
    public LoginRepo(String message, Boolean status,Role role) {
        this.message = message;
        this.status = status;
        this.role = role;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    
    
}
