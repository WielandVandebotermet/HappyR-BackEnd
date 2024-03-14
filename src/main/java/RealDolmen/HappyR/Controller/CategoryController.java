package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.CategoryService;
import RealDolmen.HappyR.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Category")
public class CategoryController {
    
    @GetMapping("/all")
    public List<Category> getAllCategories(CategoryService CategoryService) {
        return CategoryService.getCategoryList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getResultById(@PathVariable("id") int categoryId, CategoryService CategoryService) {
        Optional<Category> category = CategoryService.getOptionalCategoryById(categoryId);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addcategory(@RequestBody Category newcategory, CategoryService CategoryService){
        return CategoryService.addCategory(newcategory);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updatecategory(@RequestBody Category updatecategory, @PathVariable("id") int categoryId, CategoryService CategoryService){
        Category category = CategoryService.updateCategoryById(updatecategory, categoryId);
        if (category==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deletecategory(@PathVariable("id") int categoryId, CategoryService CategoryService){
        Optional<Category> category = CategoryService.getOptionalCategoryById(categoryId);
        if (category.isPresent()){
            CategoryService.getCategoryList().remove(category.get());
            return new ResponseEntity<>(CategoryService.getCategoryList().size(), HttpStatus.OK);
        }
        return new ResponseEntity<>(CategoryService.getCategoryList().size(), HttpStatus.NOT_FOUND);
    }
}
