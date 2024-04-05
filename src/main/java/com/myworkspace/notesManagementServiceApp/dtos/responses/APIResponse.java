package com.myworkspace.notesManagementServiceApp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse {
    boolean isSuccessful;
    Object RegistrationResponse;
}
