package com.myworkspace.notesManagementServiceApp.services;

import com.myworkspace.notesManagementServiceApp.data.model.Note;
import com.myworkspace.notesManagementServiceApp.data.repositories.NoteRepository;
import com.myworkspace.notesManagementServiceApp.dtos.requests.CreateNoteRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.DeleteNoteRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.UpdateNoteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NoteServicesTest {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private NoteServices noteServices;
    @Test
    public void createNoteTest() {
        noteRepository.deleteAll();
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setTitle("Title");
        createNoteRequest.setBody("Body");
        createNoteRequest.setAuthor("Author");
        noteServices.createNote(createNoteRequest);
        long currentNote = noteServices.findAll().size();
        assertEquals(currentNote, noteServices.count());
        Note foundNote = noteServices.findNoteByTitle("Title");
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
        long currentNote = noteServices.findAll().size();
        assertEquals(currentNote, noteServices.count());
        Note foundNote = noteServices.findNoteByTitle("New title");
        assertEquals("New title", foundNote.getTitle());

        UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest();
        updateNoteRequest.setAuthor("Author");
        updateNoteRequest.setTitle("New Title");
        updateNoteRequest.setBody("New Body");
        noteServices.updateNote(updateNoteRequest);
        Note note = noteServices.findNoteByTitle(updateNoteRequest.getTitle());
        long currentNotes = noteServices.findAll().size();
        assertEquals(currentNotes, noteServices.count());
        assertEquals("New Body", note.getBody());

    }
    @Test
    public void deleteNoteTest() {
        noteRepository.deleteAll();
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setTitle("Title");
        createNoteRequest.setBody("Body");
        createNoteRequest.setAuthor("Author");
        noteServices.createNote(createNoteRequest);
        long currentNote = noteServices.findAll().size();
        assertEquals(currentNote, noteServices.count());
        Note foundNote = noteRepository.findNoteByTitle("Title");
        assertEquals("Title", foundNote.getTitle());

        DeleteNoteRequest deleteNote = new DeleteNoteRequest();
        deleteNote.setNoteTitle("Title");
        deleteNote.setAuthor("Author");
        noteServices.deleteNote(deleteNote);
        long currentNotes = noteServices.findAll().size();
        assertEquals(currentNotes, noteServices.count());

    }

}
