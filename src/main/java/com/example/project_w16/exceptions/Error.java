package com.example.project_w16.exceptions;

import lombok.Data;

@Data
public class Error {
    private String message;
    private String details;
    private String status;
}
