package com.dicionary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicionary.model.Word;
import com.dicionary.repository.WordRepository;

import lombok.Data;

@Service
@Data
public class WordService {

	@Autowired
	private final WordRepository wordRepository;
	
	public Word save(Word word) {
		return wordRepository.save(word);
	}
	
	public Word getByID(Long id) {
		Optional<Word> word = wordRepository.findById(id);
		return word.get();
	}
	
	public List<Word> getAll(){
		return wordRepository.findAll();
	}
	
	public Word update(Long id, Word word) {
		Word original = getByID(id);
		word.setId(original.getId());
		return wordRepository.save(word);
	}
	
	public void delete(Long id) {
		Word word = getByID(id);
		wordRepository.delete(word);
	}
	
	
}
