package com.notes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.notes.DTOs.NotesDto;
@Service
public interface NotesService {
	
	//create
	NotesDto createNote(NotesDto notesDto,String userId);
	
	
	//update
	NotesDto updateNote(NotesDto notesDto,Integer notesId);
	
	//delete
	void deleteNote(Integer notesId);
	
	//get
	NotesDto getNote(Integer notesId);
	
	//getAll
	List<NotesDto> getAllNote();

	//getByUser
    List<NotesDto> getNoteByUser(String userId);
}
