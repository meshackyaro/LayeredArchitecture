package com.myworkspace.notesManagementServiceApp.exceptions;

public class NoteDoesNotExistException extends NoteManagerException {
    public NoteDoesNotExistException(String message) {
    super(message);
    }
}
