package com.myworkspace.notesManagementServiceApp.services;

import com.myworkspace.notesManagementServiceApp.data.model.Note;
import com.myworkspace.notesManagementServiceApp.data.repositories.NoteRepository;
import com.myworkspace.notesManagementServiceApp.dtos.requests.CreateNoteRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.DeleteNoteRequest;
import com.myworkspace.notesManagementServiceApp.dtos.requests.UpdateNoteRequest;
import com.myworkspace.notesManagementServiceApp.exceptions.NoteDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteServicesImpl implements NoteServices {
    @Autowired
    private UserServices userServices;
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void createNote(CreateNoteRequest createNoteRequest) {
        Note note = new Note();
        note.setTitle(createNoteRequest.getTitle());
        note.setBody(createNoteRequest.getBody());
        note.setAuthor(createNoteRequest.getAuthor());
        noteRepository.save(note);

    }

    @Override
    public void updateNote(UpdateNoteRequest updateNote) {
       // Note note = noteRepository.findNoteByTitle(updateNote.getTitle());
        //UpdateNoteRequest updatedNote = new UpdateNoteRequest();
        Note note = new Note();
        note.setTitle(updateNote.getTitle());
        note.setBody(updateNote.getBody());
        note.setAuthor(updateNote.getAuthor());
        noteRepository.save(note);
    }

    @Override
    public Note findNoteByTitle(String title) {
        return noteRepository.findNoteByTitle(title);
    }

    @Override
    public void deleteNote(DeleteNoteRequest deleteNoteRequest) {
        //if (noteRepository.findNoteByTitle(deleteNoteRequest.getNoteTitle()) != null) throw new NoteDoesNotExistException("Sorry, note doesn't exist");
        //Note note = noteRepository.findNoteByTitle(deleteNoteRequest.getNoteTitle());
        Note note = findNoteByTitle(deleteNoteRequest.getNoteTitle());
        noteRepository.delete(note);
    }

    @Override
    public long count() {
        return noteRepository.count();
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public List<Note> findNoteByAuthor(String Author) {
        return noteRepository.findNoteByAuthor(Author);
    }

}
