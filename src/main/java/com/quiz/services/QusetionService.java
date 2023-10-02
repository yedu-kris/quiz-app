package com.quiz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.dao.QuestionDao;
import com.quiz.model.Questions;


@Service
public class QusetionService {

    @Autowired
    QuestionDao questionDao;

    // @Autowired
    // Questions questions;


    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
       
    }
    public ResponseEntity<Long> getCountOfRecords() {
        return new ResponseEntity<>(questionDao.count(),HttpStatus.OK);
    }

    public List<Questions> getQuestionsByCategory(String category) {
        return questionDao.findByCategoryIgnoreCase(category);
    }
    public ResponseEntity<String> addQuestion(Questions questions) {

        questionDao.save( questions);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public String deleteQuestion(int id) {
        Optional<Questions> question=questionDao.findById(id);
        return question.map(q->{
            questionDao.deleteById(id);
            return "Success: Deleted Qid:"+id+" Question :"+q.getQuestion();
        }).orElse( "Error: Question with ID " + id + " not found");
    }
    public String addQuestions(List<Questions> questions) {
        questionDao.saveAll(questions);
        return "success";
    }
    public String updateQuestion(int id) {
        return questionDao.findById(id).map(q->{
            q.setQuestion("new Question");
            questionDao.save(q);
            return "success "+id;
        }).orElse("Error id: "+id+" does not exists");
       
    }
   
}
