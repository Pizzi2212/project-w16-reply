package com.example.project_w16.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
