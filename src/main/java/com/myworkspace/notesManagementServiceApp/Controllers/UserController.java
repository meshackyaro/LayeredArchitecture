package com.myworkspace.notesManagementServiceApp.Controllers;

import com.myworkspace.notesManagementServiceApp.dtos.requests.*;
import com.myworkspace.notesManagementServiceApp.dtos.responses.APIResponse;
import com.myworkspace.notesManagementServiceApp.dtos.responses.RegistrationResponse;
import com.myworkspace.notesManagementServiceApp.exceptions.NoteManagerException;
import com.myworkspace.notesManagementServiceApp.services.NoteServices;
import com.myworkspace.notesManagementServiceApp.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor

public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private NoteServices noteServices;
@PostMapping("/register")
    public ResponseEntity<?> register(RegisterUserRequest registerRequest) {
        try {
            RegistrationResponse response = userServices.register(registerRequest);
            return new ResponseEntity<>(new APIResponse(true, response), CREATED);
        } catch(NoteManagerException e) {
            return new ResponseEntity<>(new APIResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(LoginUserRequest loginRequest) {
        try {
            RegistrationResponse response = userServices.login(loginRequest);
            return new ResponseEntity<>(new APIResponse(true, response), CREATED);
        } catch(NoteManagerException e) {
        return new ResponseEntity<>(new APIResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    public String createNote(CreateNoteRequest createNoteRequest) {
        try {
            noteServices.createNote(createNoteRequest);
            return "Note Created Successfully";
        } catch(NoteManagerException e) {
            return e.getMessage();
        }
    }
    public String updateNote(UpdateNoteRequest updateNoteRequest) {
        try {
            noteServices.updateNote(updateNoteRequest);
            return "Updated Successfully";
        } catch(NoteManagerException e) {
            return e.getMessage();
        }
    }
    public String deleteNote(DeleteNoteRequest deleteNoteRequest) {
        try {
            noteServices.deleteNote(deleteNoteRequest);
            return "deleted Successfully";
        } catch(NoteManagerException e) {
            return e.getMessage();
        }
    }
    public String logout(LogoutUserRequest logoutRequest) {
        try {
            userServices.logout(logoutRequest);
            return "Logout Successful";
        } catch(NoteManagerException e) {
            return e.getMessage();
        }
    }
}
