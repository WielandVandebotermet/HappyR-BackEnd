package RealDolmen.HappyR;

import RealDolmen.HappyR.Data.CategorieRequest;
import RealDolmen.HappyR.Repository.CategoryRepository;
import RealDolmen.HappyR.Service.CategoryService;
import RealDolmen.HappyR.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
// Import necessary packages and classes

// Annotation to enable Mockito extensions for JUnit 5
@ExtendWith(MockitoExtension.class)
public class CategoryTest {

    // Mocking the CategoryRepository
    @Mock
    private CategoryRepository categoryRepository;

    // Injecting mocked CategoryService into test class
    @InjectMocks
    private CategoryService categoryService;

    // Test case for creating a category
    @Test
    void testCreateCategory() {
        // Creating a test request
        CategorieRequest request = new CategorieRequest();
        request.setCategoryName("TestCategory");
        request.setScoreImpact(5);

        // Invoking the method under test
        categoryService.createCategory(request);

        // Verifying that save method of categoryRepository is called once with any Category object
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    // Test case for editing a category
    @Test
    void testEditCategory() {
        // Creating a test request for editing
        CategorieRequest request = new CategorieRequest();
        request.setCategoryName("UpdatedCategory");
        request.setScoreImpact(10);

        // Creating a mock Category object and configuring behavior of categoryRepository mock
        Category category = new Category();
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        // Invoking the method under test
        categoryService.editCategory(1, request);

        // Verifying that category's properties are updated and save method of categoryRepository is called once with any Category object
        assertEquals("UpdatedCategory", category.getCategoryName());
        assertEquals(10, category.getScoreImpact());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    // Test case for deleting a category
    @Test
    void testDeleteCategory() {
        // Invoking the method under test
        categoryService.deleteCategory(1);

        // Verifying that deleteById method of categoryRepository is called once with anyLong argument
        verify(categoryRepository, times(1)).deleteById(anyLong());
    }

    // Test case for retrieving all categories
    @Test
    void testGetAllCategories() {
        // Invoking the method under test
        categoryService.getAllCategorys();

        // Verifying that findAll method of categoryRepository is called once
        verify(categoryRepository, times(1)).findAll();
    }

    // Test case for retrieving a category by ID
    @Test
    void testGetCategoryById() {
        // Creating a mock Category object and configuring behavior of categoryRepository mock
        Category category = new Category();
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        // Invoking the method under test
        Category result = categoryService.getCategoryById(1);

        // Verifying that retrieved category is equal to the expected one and findById method of categoryRepository is called once with anyLong argument
        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(anyLong());
    }
}
