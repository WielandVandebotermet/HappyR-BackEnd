package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryService {
    private List<Category> categoryList= new ArrayList<>();

    public CategoryService() {
        categoryList.add(new Category(1, "General happiness", 100));
        categoryList.add(new Category(2, "Workplace", 90));
        categoryList.add(new Category(3, "Team Contribution", 130));
        categoryList.add(new Category(4, "Team Members", 100));
        categoryList.add(new Category(5, "Project", 50));
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Optional<Category> getOptionalCategoryById(int categoryId){
        return getCategoryList().stream().filter(c-> c.getId()==categoryId).findFirst();
    }
    public Category getCategoryById(Optional<Category> optionalCategory){
        return optionalCategory.orElse(null);
    }

    public Category addCategory(Category newCategory) {
        newCategory.setId(categoryList.size()+1);
        categoryList.add(newCategory);
        return categoryList.get(categoryList.size()-1);
    }

    public Category updateCategoryById(Category updateCategory, int categoryId) {
        Optional<Category> productOptional = getOptionalCategoryById(categoryId);
        if (productOptional.isPresent()){
            Category category = productOptional.get();
            category.setCategoryName(updateCategory.getCategoryName());
            category.setScoreImpact(updateCategory.getScoreImpact());
            return category;
        }
        return null;
    }
}
