package com.myworkspace.notesManagementServiceApp.services;

import com.myworkspace.notesManagementServiceApp.data.model.User;
import com.myworkspace.notesManagementServiceApp.data.repositories.UserRepository;
import com.myworkspace.notesManagementServiceApp.dtos.requests.LoginUserRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;

    @Override
    public long getNumberOfUsers() {
        return userRepository.count();
    }

    @Override
    public void register(RegisterUserRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        if(userRepository.findUserByUsername(registerRequest.getUsername())==null)userRepository.save(user);
    }

    @Override
    public void login(LoginUserRequest loginRequest) {
        User loggedInUser = userRepository.findUserByUsername(loginRequest.getUsername());
        loggedInUser.setLogged(true);
        userRepository.save(loggedInUser);
    }

}
