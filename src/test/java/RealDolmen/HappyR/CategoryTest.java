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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CategoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void testCreateCategory() {
        CategorieRequest request = new CategorieRequest();
        request.setCategoryName("TestCategory");
        request.setScoreImpact(5);

        categoryService.createCategory(request);

        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testEditCategory() {
        CategorieRequest request = new CategorieRequest();
        request.setCategoryName("UpdatedCategory");
        request.setScoreImpact(10);

        Category category = new Category();
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        categoryService.editCategory(1, request);

        assertEquals("UpdatedCategory", category.getCategoryName());
        assertEquals(10, category.getScoreImpact());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testDeleteCategory() {
        categoryService.deleteCategory(1);

        verify(categoryRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetAllCategories() {
        categoryService.getAllCategorys();

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetCategoryById() {
        Category category = new Category();
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(1);

        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(anyLong());
    }
}
