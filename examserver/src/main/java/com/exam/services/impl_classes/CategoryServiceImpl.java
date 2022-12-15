package com.exam.services.impl_classes;

import com.exam.models.exam.Category;
import com.exam.repository.CategoryRepository;
import com.exam.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl  implements CategoryService {

   @Autowired
   private CategoryRepository categoryRepository;



    @Override
    public Category addCategory(Category category) {
        return  this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        //if id is same then save method itself update its values
        return  this.categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCategories() {
     return   new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public Category getCategory(Long categoryId) {
        return this.categoryRepository.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(Long categoryId) {


        Category category= new Category();
        category.setCid(categoryId);
        this.categoryRepository.delete(category);


        // can direclly delete by Id also.
      //  this.categoryRepository.deleteById(categoryId);

    }
}
