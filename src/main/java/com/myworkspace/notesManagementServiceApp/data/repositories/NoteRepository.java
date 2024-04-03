package com.myworkspace.notesManagementServiceApp.data.repositories;

import com.myworkspace.notesManagementServiceApp.data.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findNoteByAuthor(String Author);

    Note findNoteByTitle(String title);

}
