package com.exam.repository;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long>
{
    //Just created this method : defination is given by the spring container itself.
    Set<Question> findByQuiz(Quiz quiz);



}
