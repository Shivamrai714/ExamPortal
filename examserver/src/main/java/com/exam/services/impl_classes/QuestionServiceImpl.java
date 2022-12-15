package com.exam.services.impl_classes;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import com.exam.repository.QuestionRepository;
import com.exam.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

   @Override               // 1. To get All questions : Not needed at all
    public Set<Question> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }


    @Override        // 2. To get specific Question
    public Question getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).get();
    }



    //3. To get the Questions of the givenQuiz
    // Extra method just  declared in the inteface and springboot by default gives its implementations.
    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);

    }

    @Override
   public void deleteQuestion(Long quesId) {
        Question question= new Question();
        question.setQuesId(quesId);
       this.questionRepository.delete(question);
   }

    @Override
    public Question get(Long questionId) {
        return this.questionRepository.getOne(questionId);
    }


}
