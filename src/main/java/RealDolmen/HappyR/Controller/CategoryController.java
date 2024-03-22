package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.CategoryService;
import RealDolmen.HappyR.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategorys() {
        return categoryService.getAllCategorys();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getCategoryById(@PathVariable("id") String id) {
        return categoryService.getCategoryById(Integer.parseInt(id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory
            (@PathVariable("id") String id) {
        categoryService.deleteCategory(Integer.parseInt(id));
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editCategory
            (@PathVariable("id") String id, @RequestBody Category category) {
        categoryService.editCategory(Integer.parseInt(id), category);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createCategory
            (@RequestBody Category category) {
        categoryService.createCategory(category);
    }
}
