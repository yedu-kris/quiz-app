package com.quiz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.dao.QuestionDao;
import com.quiz.dao.QuizDao;
import com.quiz.model.QuestionWrapper;
import com.quiz.model.Questions;
import com.quiz.model.Quiz;
import com.quiz.model.Response;



@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    

    public ResponseEntity<String> createQuiz(String category, int noOfQue, String title) {
        List<Questions> questions=questionDao.FindRandomQuestionsByCategory(category,noOfQue);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Created "+title,HttpStatus.CREATED);
    }



    public ResponseEntity<List<QuestionWrapper>> getQuiz(int qid) {
        Optional<Quiz> quizs=quizDao.findById(qid);
        List<Questions> questionsFromDb=quizs.get().getQuestions();
        List<QuestionWrapper> questionForUser=new ArrayList<>();

        for(Questions q: questionsFromDb){
            QuestionWrapper qWrapper=new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qWrapper);
        }


        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }



    public ResponseEntity<Integer> calculateResult(int qid, List<Response> responses) {
        Optional<Quiz> quizs=quizDao.findById(qid);
        List<Questions> questions=quizs.get().getQuestions();

        int score=0;
        for(int i=0;i<responses.size();i++){
            if(responses.get(i).getResponse().equals(questions.get(i).getAnswer()))score++;
        }

        return new ResponseEntity<>(score,HttpStatus.OK);
    }
    
}
