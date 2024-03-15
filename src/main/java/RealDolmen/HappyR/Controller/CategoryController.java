package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.CategoryService;
import RealDolmen.HappyR.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/Category")
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

    @DeleteMapping("/Delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteKoers
            (@PathVariable("id") String id) {
        categoryService.deleteCategory(Integer.parseInt(id));
    }

    @PutMapping("/Edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditKoers
            (@PathVariable("id") String id, @RequestBody Category category) {
        categoryService.editCategory(Integer.parseInt(id), category);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createKoers
            (@RequestBody Category category) {
        categoryService.createCategory(category);
    }
}
