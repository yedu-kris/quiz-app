package com.quiz.model;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "quizquestions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     private String question;
     @Column(name = "content_category")
     private String category;
     private String option1;
     private String option2;
     private String option3;
     private String option4;
     private String answer;

     



}
