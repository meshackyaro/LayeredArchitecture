package com.myworkspace.notesManagementServiceApp.services;

import com.myworkspace.notesManagementServiceApp.data.model.User;
import com.myworkspace.notesManagementServiceApp.data.repositories.UserRepository;
import com.myworkspace.notesManagementServiceApp.dtos.requests.LoginUserRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.LogoutUserRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.RegisterUserRequest;
import com.myworkspace.notesManagementServiceApp.dtos.responses.RegistrationResponse;
import com.myworkspace.notesManagementServiceApp.exceptions.IncorrectPasswordException;
import com.myworkspace.notesManagementServiceApp.exceptions.UnregisteredUserException;
import com.myworkspace.notesManagementServiceApp.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;

    @Override
    public long getNumberOfUsers() {
        return userRepository.count();
    }

    @Override
    public RegistrationResponse register(RegisterUserRequest registerRequest) {
        User user = new User();
        if (userRepository.findUserByUsername(registerRequest.getUsername()) != null) throw new UserAlreadyExistException("username already exist");
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        userRepository.save(user);

        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setFirstName(registerRequest.getFirstName());
        registrationResponse.setLastName(registerRequest.getLastName());
        registrationResponse.setUsername(registerRequest.getUsername());
        registrationResponse.setMessage("Registration Successful");
        return registrationResponse;
    }

    @Override
    public User login(LoginUserRequest loginRequest) {
        User foundUser = userRepository.findUserByUsername(loginRequest.getUsername());
        if (foundUser == null)
            throw new UnregisteredUserException("You do not have an account, please signup to login");
//        User logInUser = userRepository.findUserByUsername(loginRequest.getUsername());
        if (foundUser.getPassword().equals(loginRequest.getPassword())) {
            foundUser.setLogged(true);
            userRepository.save(foundUser);
            return foundUser;
        }
        throw new IncorrectPasswordException("Incorrect password");
    }

    @Override
    public User logout(LogoutUserRequest logoutRequest) {
        User user = userRepository.findUserByUsername(logoutRequest.getUsername());
        user.setLogged(false);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
