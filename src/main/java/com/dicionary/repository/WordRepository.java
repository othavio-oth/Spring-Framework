package com.dicionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dicionary.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

}
