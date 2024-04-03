package com.myworkspace.notesManagementServiceApp.dtos.requests;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
