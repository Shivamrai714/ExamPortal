package com.exam.models.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qId;         //During Update: take care of this while testing on  postman

    private String title;

    private String description;
    private String maxMarks;  //can be converted to interger loter.
    private  String numberOfQuestions;  //Ques to be showed .
    private  boolean active=false;

    @ManyToOne(fetch =  FetchType.EAGER)         // I MADE A ERROR HERE :  TO CHANGE THE FETCH TYPE TO LAZY , SO ERROR WAS COMMING DISABLE THE SERIAZABLE...
    private Category category;



    //RELATIONSHIP 2 :  One quiz have many questions.
    @OneToMany(mappedBy = "quiz" , fetch = FetchType.LAZY ,  cascade = CascadeType.ALL)             // 1 quiz have many questions.   ,
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    public Quiz() {
    }

    public Quiz(Long qId, String title, String description, String maxMarks, String numberOfQuestions, boolean active) {
        this.qId = qId;
        this.title = title;
        this.description = description;
        this.maxMarks = maxMarks;
        this.numberOfQuestions = numberOfQuestions;
        this.active = active;
    }

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(String numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
