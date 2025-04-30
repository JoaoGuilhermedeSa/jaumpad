package com.example.jaumpad.controller;

import com.example.jaumpad.model.Note;
import com.example.jaumpad.service.NoteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NoteRestController {

    private final NoteService noteService;

    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable String id) {
        return noteService.getOrCreate(id);
    }

    @PostMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody String content) {
        return noteService.update(id, content);
    }
}