package com.dicionary.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicionary.dto.WordDTO;
import com.dicionary.dto.WordMapper;
import com.dicionary.model.Word;
import com.dicionary.service.WordService;

import lombok.Data;

@RestController
@RequestMapping("v1/words")
@Data
public class WordController {
	
	private final WordService wordService;
	
	@PostMapping
	public ResponseEntity<Word> saveWord(@RequestBody WordDTO dto){
		Word word = wordService.save(WordMapper.fromDto(dto));
		return ResponseEntity.ok(word);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Word> getWordById(@PathVariable Long id){
		Word word = wordService.getByID(id);
		return ResponseEntity.ok(word);
	}
	
	@GetMapping
	public ResponseEntity<List<Word>> getAllWords(){
		List<Word> words = wordService.getAll();
		return ResponseEntity.ok(words);
				
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Word> updateWord(@PathVariable Long id, @RequestBody WordDTO dto){
		Word word = wordService.update(id, WordMapper.fromDto(dto));
		return ResponseEntity.ok(word);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Word> deleteWord(@PathVariable Long id){
		wordService.delete(id);
		return ResponseEntity.ok().build();
	}

}
