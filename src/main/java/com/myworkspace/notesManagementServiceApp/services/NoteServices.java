package com.myworkspace.notesManagementServiceApp.services;

import com.myworkspace.notesManagementServiceApp.data.model.Note;
import com.myworkspace.notesManagementServiceApp.dtos.requests.CreateNoteRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.UpdateNoteRequest;

public interface NoteServices {
void createNote(CreateNoteRequest createNoteRequest);

    void updateNote(UpdateNoteRequest updateNoteRequest);

    Note findNoteByTitle(String title);

}
