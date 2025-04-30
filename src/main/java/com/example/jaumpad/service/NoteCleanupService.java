package com.example.jaumpad.service;

import com.example.jaumpad.repository.NoteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoteCleanupService {

	private final NoteRepository noteRepository;

	public NoteCleanupService(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	@Scheduled(fixedRate = 600000)
	public void cleanOldNotes() {
		LocalDateTime cutoff = LocalDateTime.now().minusHours(1);
		noteRepository.deleteAll(noteRepository.findByLastModifiedBefore(cutoff));
	}
}