package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Data.CategorieRequest;
import RealDolmen.HappyR.Service.CategoryService;
import RealDolmen.HappyR.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Import statements for required classes and libraries

// Controller class responsible for handling Category-related HTTP requests
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    // CategoryService instance for handling Category-related business logic
    private final CategoryService categoryService;

    // Endpoint to retrieve all categories
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategorys() {
        return categoryService.getAllCategorys();
    }

    // Endpoint to retrieve a category by its ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getCategoryById(@PathVariable("id") String id) {
        return categoryService.getCategoryById(Integer.parseInt(id));
    }

    // Endpoint to delete a category by its ID
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("id") String id) {
        categoryService.deleteCategory(Integer.parseInt(id));
    }

    // Endpoint to edit a category by its ID
    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editCategory(@PathVariable("id") String id, @RequestBody CategorieRequest categorieRequest) {
        // Log received category name and score impact
        System.out.println("Received CategoryName: " + categorieRequest.getCategoryName());
        System.out.println("Received ScoreImpact: " + categorieRequest.getScoreImpact());
        categoryService.editCategory(Integer.parseInt(id), categorieRequest);
    }

    // Endpoint to create a new category
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createCategory(@RequestBody CategorieRequest categorieRequest) {
        categoryService.createCategory(categorieRequest);
    }
}
