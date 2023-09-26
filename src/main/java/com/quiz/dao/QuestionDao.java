package com.quiz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.model.Questions;



@Repository
public interface QuestionDao  extends JpaRepository<Questions,Integer> {
    List<Questions> findByCategoryIgnoreCase( String category);
}
