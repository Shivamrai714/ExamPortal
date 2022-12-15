package com.exam.models.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="category")
public class Category
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cid;

    private String title;
    private String description;

   // @OneToMany(mappedBy = "category" ,  fetch = FetchType.EAGER, cascade = CascadeType.ALL)             //mapped by help in integrating the two tables , ie category_cid  is crated in  " Quiz " table.
   @OneToMany(mappedBy = "category" ,  cascade = CascadeType.ALL)
   @JsonIgnore
    private Set<Quiz>  quizzes= new LinkedHashSet<>();
                                               // EAGER fetchtype helped in getting the quizzes also when we load category (as quizes are less in no here.)  ,  LAZY means its loaded when we call it specifically using it getters *
                                              // Cascade all means saving the quizes also on saving the category.

    //////////////////// UPDATE: REMOVED FETCH TYPE AS EAGER AS ERROR WAS COMMING IN DELETEING QUIZ. (it was deleting in two times)

   public Category(){

   }

    public Category(String title, String description) {
      //  this.cid = cid;   // id id auto generated
        this.title = title;
        this.description = description;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
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


}
