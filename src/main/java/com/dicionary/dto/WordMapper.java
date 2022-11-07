package com.dicionary.dto;

import com.dicionary.model.Word;

public class WordMapper {
	
	public static Word fromDto(WordDTO dto) {
		return new Word(null, dto.getTerm(), dto.getMeaning());
	}
	

}
