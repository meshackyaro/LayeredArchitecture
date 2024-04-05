package com.myworkspace.notesManagementServiceApp.dtos.responses;

import lombok.Data;

@Data
public class RegistrationResponse {
    private String firstName;
    private String lastName;
    private String username;
//    private String password;
    private String message;
}
