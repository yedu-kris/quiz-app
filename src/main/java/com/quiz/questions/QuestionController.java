package com.quiz.questions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.quiz.model.Questions;
import com.quiz.services.QusetionService;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QusetionService qusetionService;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Questions>> getAllQuestions(){
        return qusetionService.getAllQuestions();
    }

     @GetMapping("/questionCount")
    public ResponseEntity<Long> getCountOfRecords(){
        return qusetionService.getCountOfRecords();
    }
    @GetMapping("/category/{category}")
    public List<Questions> getQuestionsByCategory(@PathVariable String category){
        return qusetionService.getQuestionsByCategory(category);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Questions questions){
        return qusetionService.addQuestion(questions);
    }

    @PostMapping("/addQuestions")
    public String addQuestions(@RequestBody List<Questions> questions){
        return qusetionService.addQuestions(questions);
    }

    @DeleteMapping("/deleteQuestion/{id}")
        public String deleteQuestion(@PathVariable int id){
        return qusetionService.deleteQuestion(id);
    }

    @PutMapping("/update/{id}")
    public String updateQuestion(@PathVariable int id){
        return qusetionService.updateQuestion(id);
    }
    
}
