package com.myworkspace.notesManagementServiceApp.services;

import com.myworkspace.notesManagementServiceApp.data.model.Note;
import com.myworkspace.notesManagementServiceApp.data.repositories.NoteRepository;
import com.myworkspace.notesManagementServiceApp.dtos.requests.CreateNoteRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.UpdateNoteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NoteServicesTest {
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    NoteServices noteServices;
    @Test
    public void createNoteTest() {
        noteRepository.deleteAll();
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setTitle("Title");
        createNoteRequest.setBody("Body");
        createNoteRequest.setAuthor("Author");
        noteServices.createNote(createNoteRequest);
        long currentNote = noteRepository.findAll().size();
        assertEquals(currentNote, noteRepository.count());
        Note foundNote = noteRepository.findNoteByTitle("Title");
        assertEquals("Title", foundNote.getTitle());

    }
    @Test
    public void updateNoteTest() {
        noteRepository.deleteAll();
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setTitle("New title");
        createNoteRequest.setBody("body");
        createNoteRequest.setAuthor("Author");
        noteServices.createNote(createNoteRequest);
        long currentNote = noteRepository.findAll().size();
        assertEquals(currentNote, noteRepository.count());
        Note foundNote = noteRepository.findNoteByTitle("New title");
        assertEquals("New title", foundNote.getTitle());

        UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest();
        updateNoteRequest.setAuthor("Author");
        updateNoteRequest.setTitle("New Title");
        updateNoteRequest.setBody("New Body");
        noteServices.updateNote(updateNoteRequest);
        Note note = noteServices.findNoteByTitle(updateNoteRequest.getTitle());
        long currentNotes = noteRepository.findAll().size();
        assertEquals(currentNotes, noteRepository.count());
        assertEquals("New Body", note.getBody());

    }



}
