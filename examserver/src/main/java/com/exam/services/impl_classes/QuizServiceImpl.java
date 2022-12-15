package com.exam.services.impl_classes;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;
import com.exam.repository.QuizRepository;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {


    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return  this.quizRepository.save(quiz);

    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return  this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new HashSet<> (this .quizRepository.findAll());

    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long qId) {

// Error comming , not deleting in one time.
//        Quiz quiz= new Quiz();
//        quiz.setqId(qId);
//this.quizRepository.delete(quiz);

this.quizRepository.deleteById(qId);

    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category category) {
        return this.quizRepository.findBycategory(category);
    }

    // defined the methods of interface
    @Override
    public List<Quiz> getActiveQuizzes() {
       return this.quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category category) {
        return   this.quizRepository.findByCategoryAndActive(category,true);
    }


}
