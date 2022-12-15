package com.exam.services;

import com.exam.models.exam.Category;

import java.util.Set;

public interface CategoryService {

    //Only defining the methods over here and the defination is given in the implementation classes.

    public Category addCategory(Category category);

    public  Category updateCategory(Category category);

    public Set<Category> getCategories();

    public  Category getCategory(Long categoryId);

      public  void deleteCategory(Long categoryId);



}
