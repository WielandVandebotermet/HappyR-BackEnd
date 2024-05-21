package RealDolmen.HappyR.Data;

public class CategorieRequest {
    private String categoryName;
    private int scoreImpact;

    public CategorieRequest() {
    }

    public CategorieRequest(String categoryName, int scoreImpact) {
        this.categoryName = categoryName;
        this.scoreImpact = scoreImpact;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getScoreImpact() {
        return scoreImpact;
    }

    public void setScoreImpact(int scoreImpact) {
        this.scoreImpact = scoreImpact;
    }
}
