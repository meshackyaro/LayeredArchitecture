package com.myworkspace.notesManagementServiceApp.services;

import com.myworkspace.notesManagementServiceApp.data.model.User;
import com.myworkspace.notesManagementServiceApp.data.repositories.UserRepository;
import com.myworkspace.notesManagementServiceApp.dtos.requests.LoginUserRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.LogoutUserRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.RegisterUserRequest;
import com.myworkspace.notesManagementServiceApp.exceptions.UnregisteredUserException;
import com.myworkspace.notesManagementServiceApp.exceptions.UserAlreadyExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServicesTest {
    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void newUserRegistration_registerUserTest() {
        userRepository.deleteAll();
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstName("firstName");
        registerRequest.setLastName("lastName");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.register(registerRequest);
        long currentlyRegistered = userServices.findAll().size();
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
        long currentlyRegistered = userServices.findAll().size();
        assertEquals(currentlyRegistered, userServices.getNumberOfUsers());
    }
    @Test
    public void registerExistingUser_throwsUserAlreadyExistException() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setFirstName("firstName");
        registerUserRequest1.setLastName("lastName");
        registerUserRequest1.setUsername("username");
        registerUserRequest1.setPassword("password");
        userServices.register(registerUserRequest1);
        assertEquals(1, userServices.getNumberOfUsers());

        RegisterUserRequest registerUserRequest2 = new RegisterUserRequest();
        registerUserRequest2.setFirstName("firstName");
        registerUserRequest2.setLastName("lastName");
        registerUserRequest2.setUsername("username");
        registerUserRequest2.setPassword("password");
//        userServices.register(registerUserRequest);
        assertThrows(UserAlreadyExistException.class,()-> userServices.register(registerUserRequest2));
    }
    @Test
    public void loginRegisteredUser_loginTest() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstName("firstName");
        registerUserRequest.setLastName("lastName");
        registerUserRequest.setUsername("username765");
        registerUserRequest.setPassword("password765");
        userServices.register(registerUserRequest);
        long currentlyRegistered = userServices.findAll().size();
        assertEquals(currentlyRegistered, userServices.getNumberOfUsers());
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername(registerUserRequest.getUsername());
        loginRequest.setPassword(registerUserRequest.getPassword());
        userServices.login(loginRequest);
        User user = userServices.findUserByUsername(loginRequest.getUsername());
        assertTrue(user.isLogged());
    }
    @Test
    public void unregisteredUserLogin_throwUnregisteredUserExceptionTest() {
        userRepository.deleteAll();
//        RegisterUserRequest registerRequest = new RegisterUserRequest();
//        registerRequest.setFirstName("firstName");
//        registerRequest.setLastName("lastName");
//        registerRequest.setUsername("username");
//        registerRequest.setPassword("password");
//        userServices.register(registerRequest);
//        long currentUsers = userServices.findAll().size();
//        assertEquals(currentUsers, userServices.getNumberOfUsers());
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
//        User user = userServices.login(loginRequest);
        assertThrows(UnregisteredUserException.class, ()-> userServices.login(loginRequest));
    }
    @Test
    public void logoutTest() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstName("firstName");
        registerUserRequest.setLastName("lastName");
        registerUserRequest.setUsername("username");
        registerUserRequest.setPassword("password");
        userServices.register(registerUserRequest);
        long currentlyRegistered = userRepository.findAll().size();
        assertEquals(currentlyRegistered, userServices.getNumberOfUsers());
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername(registerUserRequest.getUsername());
        loginRequest.setPassword(registerUserRequest.getPassword());
        User user = userServices.login(loginRequest);
//        User user = userRepository.findUserByUsername(loginRequest.getUsername());
        assertTrue(user.isLogged());
        LogoutUserRequest logoutRequest = new LogoutUserRequest();
        logoutRequest.setUsername("username");
        user = userServices.logout(logoutRequest);
        assertFalse(user.isLogged());

    }
}
