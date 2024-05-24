package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Data.CategorieRequest;
import RealDolmen.HappyR.Repository.CategoryRepository;
import RealDolmen.HappyR.model.Category;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing category-related operations.
 */
@Service
@RequiredArgsConstructor // Generates a constructor with required arguments for the final fields
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * Creates a new category based on the provided category request.
     *
     * @param categoryRequest The request containing category information.
     */
    public void createCategory(CategorieRequest categoryRequest){
        // Construct a new category object based on the request
        Category category = Category.builder()
                .CategoryName(categoryRequest.getCategoryName())
                .ScoreImpact(categoryRequest.getScoreImpact())
                .build();

        // Save the new category to the repository
        categoryRepository.save(category);
    }

    /**
     * Edits an existing category with the specified ID based on the provided category request.
     *
     * @param id              The ID of the category to edit.
     * @param categoryRequest The request containing updated category information.
     */
    public void editCategory(int id, CategorieRequest categoryRequest){
        // Retrieve the existing category from the repository
        Category category = categoryRepository.findById((long) id).orElse(null);

        // If the category exists, update its attributes and save the changes
        if(category != null)
        {
            category.setCategoryName(categoryRequest.getCategoryName());
            category.setScoreImpact(categoryRequest.getScoreImpact());

            categoryRepository.save(category);
        }
    }

    /**
     * Deletes the category with the specified ID.
     *
     * @param id The ID of the category to delete.
     */
    public void deleteCategory(int id){
        categoryRepository.deleteById((long) id);
    }

    /**
     * Retrieves all categories from the repository.
     *
     * @return A list of all categories.
     */
    public List<Category> getAllCategorys() {
        // Retrieve all categories from the repository
        List<Category> categories = categoryRepository.findAll();

        // Map each category to a response object and collect them into a list
        return categories.stream().map(this::mapToCategoryResponse).toList();
    }

    /**
     * Retrieves the category with the specified ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The category with the specified ID, or null if not found.
     */
    public Category getCategoryById(int id){
        return categoryRepository.findById((long) id).orElse(null);
    }

    /**
     * Maps a category entity to a category response object.
     *
     * @param category The category entity to map.
     * @return A category response object.
     */
    private Category mapToCategoryResponse(Category category) {
        return Category.builder()
                .id(category.getId())
                .CategoryName(category.getCategoryName())
                .ScoreImpact(category.getScoreImpact())
                .build();
    }
}