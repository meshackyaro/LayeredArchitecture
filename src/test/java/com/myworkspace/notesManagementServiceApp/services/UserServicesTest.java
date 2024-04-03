package com.myworkspace.notesManagementServiceApp.services;

import com.myworkspace.notesManagementServiceApp.data.model.User;
import com.myworkspace.notesManagementServiceApp.data.repositories.UserRepository;
import com.myworkspace.notesManagementServiceApp.dtos.requests.LoginUserRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.RegisterUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServicesTest {
    @Autowired
    UserServices userServices;
    @Autowired
    UserRepository userRepository;

    @Test
    public void newUserRegistration_registerUserTest() {
        userRepository.deleteAll();
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstName("firstName");
        registerRequest.setLastName("lastName");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.register(registerRequest);
        long currentlyRegistered = userRepository.findAll().size();
        assertEquals(currentlyRegistered, userServices.getNumberOfUsers());

    }
    @Test
    public void newUserRegistration_registeredUserIncreasesTest() {
        userRepository.deleteAll();
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstName("firstName");
        registerRequest.setLastName("lastName");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.register(registerRequest);
        RegisterUserRequest registerRequest1 = new RegisterUserRequest();
        registerRequest1.setFirstName("firstName");
        registerRequest1.setLastName("lastName");
        registerRequest1.setUsername("username1");
        registerRequest1.setPassword("password");
        userServices.register(registerRequest1);
        long currentlyRegistered = userRepository.findAll().size();
        assertEquals(currentlyRegistered, userServices.getNumberOfUsers());
    }
    @Test
    public void loginRegisteredUser_loginUserTest() {
        userRepository.deleteAll();
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstName("firstName");
        registerRequest.setLastName("lastName");
        registerRequest.setUsername("username765");
        registerRequest.setPassword("password765");
        userServices.register(registerRequest);
        long currentlyRegistered = userRepository.findAll().size();
        assertEquals(currentlyRegistered, userServices.getNumberOfUsers());
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername(registerRequest.getUsername());
        loginRequest.setPassword(registerRequest.getPassword());
        userServices.login(loginRequest);
        User user = userRepository.findUserByUsername(loginRequest.getUsername());
       assertTrue(user.isLogged());
    }
}
