package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Repository.CategoryRepository;
import RealDolmen.HappyR.model.Category;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void LoadData() {
        if (categoryRepository.count() <= 0) {
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
        }
    }
    public void createCategory(Category categoryRequest){
        Category category = Category.builder()
                .CategoryName(categoryRequest.getCategoryName())
                .ScoreImpact(categoryRequest.getScoreImpact())
                .build();

        categoryRepository.save(category);
    }

    public void editCategory(int id, Category categoryRequest){
        Category category = categoryRepository.findById((long) id).orElse(null);

        if(category != null)
        {
            category.setId(category.getId());
            category.setCategoryName(categoryRequest.getCategoryName());
            category.setScoreImpact(categoryRequest.getScoreImpact());

            categoryRepository.save(category);
        }
    }
    public void deleteCategory(int id){
        categoryRepository.deleteById((long) id);
    }

    public List<Category> getAllCategorys() {
        List<Category> categorys = categoryRepository.findAll();

        return categorys.stream().map(this::mapToCategoryResponse).toList();
    }

    public Category getCategoryById(int id){
        return categoryRepository.findById((long) id).orElse(null);
    }

    private Category mapToCategoryResponse(Category category) {
        return Category.builder()
                .id(category.getId())
                .CategoryName(category.getCategoryName())
                .ScoreImpact(category.getScoreImpact())
                .build();
    }

}
