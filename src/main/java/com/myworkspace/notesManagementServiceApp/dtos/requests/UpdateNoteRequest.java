package com.myworkspace.notesManagementServiceApp.dtos.requests;

import lombok.Data;
@Data
public class UpdateNoteRequest {
    private String author;
    private String title;
    private String body;

}
