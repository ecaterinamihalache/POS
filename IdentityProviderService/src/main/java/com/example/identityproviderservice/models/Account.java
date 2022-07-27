package com.example.identityproviderservice.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Accounts")
public class Account
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String email;
    private String password;
    private Boolean isActive;
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Account(Integer id, String email, String password, Boolean isActive, String role)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }
}
