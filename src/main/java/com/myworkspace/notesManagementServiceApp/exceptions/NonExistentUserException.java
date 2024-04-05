package com.myworkspace.notesManagementServiceApp.exceptions;

public class NonExistentUserException extends NoteManagerException {
    private NonExistentUserException(String message) {
        super(message);
    }
}
