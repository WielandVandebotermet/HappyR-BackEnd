package RealDolmen.HappyR.model;

public class Category {
    private int id;
    private String CategoryName;
    private int ScoreImpact;

    public Category(int id, String categoryName, int scoreImpact) {
        this.id = id;
        CategoryName = categoryName;
        ScoreImpact = scoreImpact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public int getScoreImpact() {
        return ScoreImpact;
    }

    public void setScoreImpact(int scoreImpact) {
        ScoreImpact = scoreImpact;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", CategoryName='" + CategoryName + '\'' +
                ", ScoreImpact=" + ScoreImpact +
                '}';
    }
}
