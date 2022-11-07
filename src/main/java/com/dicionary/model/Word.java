package com.dicionary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String term;
	String meaning;
}
