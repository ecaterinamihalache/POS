package com.example.identityproviderservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TokenAndClaims
{
    private String token;
    private int accountId;
    private String role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public TokenAndClaims(String token, int accountId, String role) {
        this.token = token;
        this.accountId = accountId;
        this.role = role;
    }
}

