package com.exam.controllers;


import com.exam.models.exam.Category;
import com.exam.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController
{

    @Autowired
    private CategoryService categoryService;

    // ADD category
    @PostMapping("/")             //RequestBody is used to fetch the JSon data from the postman inlet.
    public ResponseEntity<?> addCategory(@RequestBody Category category)
    {
       Category category1= this.categoryService.addCategory(category);
         return ResponseEntity.ok(category1);
    }

    // GET  single category
    @GetMapping("/{categoryId}")    //Path varible is used to fetch the id from the passed api format'
    public  Category getCategory(@PathVariable("categoryId") Long categoryId)
    {
     return this.categoryService.getCategory(categoryId);
    }




    //GET All categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories()
    {
      return  ResponseEntity.ok(this.categoryService.getCategories());

      //Or other method  is to change the return type to the Set and here we need to call the this.categoriesService.getCategories() fucntion
    }


    //UPDATE API
    @PutMapping("/")   // @#RequestBody is : Here the data will be comming in json , so old id data is updated to new data
    public  Category updateCategory(@RequestBody Category category)
    {
return  this.categoryService.updateCategory(category);
    }

    //DELETE  api
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId" )  Long categoryId)
    {
      this.categoryService.deleteCategory(categoryId);
    }



/*
    // Another way to create the  update api
    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategories(@PathVariable("categoryId") Long categoryId)
    {
        Category category2= new Category();
        category2.setCid(categoryId);
        return ResponseEntity.ok(this.categoryService.updateCategory(category2));
    }
*/

}
