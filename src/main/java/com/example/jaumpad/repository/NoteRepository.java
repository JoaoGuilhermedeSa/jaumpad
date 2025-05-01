package com.example.jaumpad.repository;

import com.example.jaumpad.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, String> {
	
    List<Note> findByLastModifiedBefore(LocalDateTime cutoff);
}