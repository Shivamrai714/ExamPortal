package com.exam.controllers;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // add quiz service
    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz)
    {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }


    //Update quiz api
    @PutMapping("/")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz)
    {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    // get a quiz single
    @GetMapping("/{qid}")
    public Quiz getQuiz(@PathVariable("qid") Long qid)
    {
       return this.quizService.getQuiz(qid);
    }

    //get all the Quizes

    @GetMapping("/")
    public ResponseEntity<?> getQuizzes()         // we can also use Set here to return them .
    {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }


    // delete api

    @DeleteMapping("/{qid}")
    public void deleteQuiz(@PathVariable("qid") Long qid)
    {
        this.quizService.deleteQuiz(qid);
    }


     //VIDEO 37 : Adding url to get the quizes acccording to the category button clicked by the user
     //This fucntion will be used in the fontend to call the api using the angular.
    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid)
    {
        Category category = new Category();
        category.setCid(cid);
       return this.quizService.getQuizzesOfCategory(category);
    }

    //Video : 38
//get active Quizzes
    @GetMapping("/active")
    public  List<Quiz> getActiveQuizzes()
    { return this.quizService.getActiveQuizzes();
    }

 // get Active quizzes of category.
 @GetMapping("/category/active/{cid}")
 public List<Quiz> getActiveQuizzes(@PathVariable("cid") Long cid)
 {
     Category category = new Category();
     category.setCid(cid);
     return this.quizService.getActiveQuizzesOfCategory(category);
 }



}
