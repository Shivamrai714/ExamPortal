package com.exam.services;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);

    public Set<Question> getQuestions();

    public Question getQuestion(Long questionId);

    public Set<Question> getQuestionsOfQuiz(Quiz quiz);
    public void deleteQuestion(Long quesId);

    //Video 44 : to process evaluation at the server side
    public Question get(Long questionId);


//EXtra method "findByQuiz(Quiz quiz)" just given is declared and defind by springboot itself  in the  QuestionRepopsitory itself

}
