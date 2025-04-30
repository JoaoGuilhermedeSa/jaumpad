package com.example.jaumpad.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.jaumpad.service.NoteService;

@Controller
public class NoteWebSocketController {

	private final NoteService noteService;
	private final SimpMessagingTemplate messagingTemplate;

	public NoteWebSocketController(NoteService noteService, SimpMessagingTemplate messagingTemplate) {
		this.noteService = noteService;
		this.messagingTemplate = messagingTemplate;
	}

	@MessageMapping("/note.update.{noteId}")
	public void updateNote(@DestinationVariable String noteId, String content) {
		noteService.update(noteId, content);

		messagingTemplate.convertAndSend("/topic/notes/" + noteId, content);
	}
}