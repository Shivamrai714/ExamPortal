package com.exam.services;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizzes();

    public Quiz getQuiz(Long quizid);
   public  void deleteQuiz(Long quizid);


    public List<Quiz> getQuizzesOfCategory(Category category);

    //define the method
    public List<Quiz> getActiveQuizzes();
    public List<Quiz> getActiveQuizzesOfCategory(Category category);
}
