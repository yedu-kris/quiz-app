package com.quiz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quiz.model.Questions;



@Repository
public interface QuestionDao  extends JpaRepository<Questions,Integer> {
    List<Questions> findByCategoryIgnoreCase( String category);

    @Query("SELECT q FROM Questions q WHERE LOWER(q.category) = LOWER(:category) ORDER BY RANDOM() LIMIT :noOfQue")
    List<Questions> FindRandomQuestionsByCategory(String category, int noOfQue);




}
