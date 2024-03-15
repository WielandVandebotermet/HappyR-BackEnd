package RealDolmen.HappyR;

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
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void testGetAllCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("General happiness");
        category.setScoreImpact(100);
        categoryRepository.save(category);

        Category category1 = new Category();
        category1.setId(2L);
        category1.setCategoryName("Workplace");
        category1.setScoreImpact(80);
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setId(3L);
        category2.setCategoryName("Project");
        category2.setScoreImpact(90);
        categoryRepository.save(category2);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        categoryList.add(category1);
        categoryList.add(category2);

        when(categoryRepository.findAll()).thenReturn(categoryList);
        List<Category> categorys = categoryService.getAllCategorys();


        assertEquals(3, categorys.size());

        assertEquals("General happiness", categorys.get(0).getCategoryName());
        assertEquals(100, categorys.get(0).getScoreImpact());

        assertEquals("Workplace", categorys.get(1).getCategoryName());
        assertEquals(80, categorys.get(1).getScoreImpact());


        assertEquals("Project", categorys.get(2).getCategoryName());
        assertEquals(90, categorys.get(2).getScoreImpact());

    }

    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setCategoryName("General happiness");
        category.setScoreImpact(100);

        categoryService.createCategory(category);

        // Verify that save method was called with the correct arguments
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testEditCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("General happiness");
        category.setScoreImpact(100);

        Category category1 = new Category();
        category1.setId(1L);
        category1.setCategoryName("Workplace");
        category1.setScoreImpact(80);
        categoryRepository.save(category1);

        when(categoryRepository.findById(5L)).thenReturn(Optional.of(category));

        categoryService.editCategory(5, category1);

        verify(categoryRepository, times(1)).save(category);

        assertEquals(1L, category.getId());
        assertEquals("Workplace", category.getCategoryName());
        assertEquals(80, category.getScoreImpact());
    }

    @Test
    void testDeleteCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("General happiness");
        category.setScoreImpact(100);

        categoryService.deleteCategory(1);

        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
