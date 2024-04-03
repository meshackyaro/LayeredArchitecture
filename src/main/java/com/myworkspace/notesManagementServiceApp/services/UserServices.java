package com.myworkspace.notesManagementServiceApp.services;

import com.myworkspace.notesManagementServiceApp.dtos.requests.LoginUserRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.RegisterUserRequest;

public interface UserServices {
    long getNumberOfUsers();

    void register(RegisterUserRequest registerRequest);

    void login(LoginUserRequest loginRequest);


}
