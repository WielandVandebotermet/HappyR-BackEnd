package RealDolmen.HappyR.Data;

/**
 * Data class representing a request for creating or updating a category.
 */
public class CategorieRequest {
    private String categoryName; // Name of the category
    private int scoreImpact; // Impact score of the category

    /**
     * Default constructor.
     */
    public CategorieRequest() {
    }

    /**
     * Parameterized constructor.
     * @param categoryName The name of the category.
     * @param scoreImpact The impact score of the category.
     */
    public CategorieRequest(String categoryName, int scoreImpact) {
        this.categoryName = categoryName;
        this.scoreImpact = scoreImpact;
    }

    /**
     * Getter for categoryName.
     * @return The name of the category.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Setter for categoryName.
     * @param categoryName The name of the category to set.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Getter for scoreImpact.
     * @return The impact score of the category.
     */
    public int getScoreImpact() {
        return scoreImpact;
    }

    /**
     * Setter for scoreImpact.
     * @param scoreImpact The impact score of the category to set.
     */
    public void setScoreImpact(int scoreImpact) {
        this.scoreImpact = scoreImpact;
    }
}
