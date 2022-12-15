package com.exam.repository;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import  java.util.*;

public interface QuizRepository extends JpaRepository <Quiz, Long>
{
public List<Quiz> findBycategory(Category category);

//VIDEO 38 :  STep 1 define method to filter out acc to acive status

    public List<Quiz> findByActive(Boolean b);
    public  List<Quiz> findByCategoryAndActive(Category c , Boolean b);

}
