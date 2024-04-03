package com.myworkspace.notesManagementServiceApp.dtos.requests;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private String title;
    private String body;
    private String author;
}
