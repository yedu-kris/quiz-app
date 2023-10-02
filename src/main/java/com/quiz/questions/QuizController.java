package com.quiz.questions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.QuestionWrapper;
import com.quiz.model.Questions;
import com.quiz.model.Response;
import com.quiz.services.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category ,@RequestParam int noOfQue,@RequestParam String title){
        return quizService.createQuiz(category,noOfQue,title);
    }
    @GetMapping("/get/{qid}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int qid){
        return quizService.getQuiz(qid);
    }
    @PostMapping("/submit/{qid}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int qid , @RequestBody List<Response> responses){
        return quizService.calculateResult(qid,responses);
    }
    
}
