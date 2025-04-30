package com.example.jaumpad.service;

import com.example.jaumpad.model.Note;
import com.example.jaumpad.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note getOrCreate(String id) {
        return noteRepository.findById(id).orElseGet(() -> {
            Note note = new Note(id, "");
            return noteRepository.save(note);
        });
    }

    public Note update(String id, String content) {
        Note note = getOrCreate(id);
        note.setContent(content);
        note.setLastModified(LocalDateTime.now());
        return noteRepository.save(note);
    }
}