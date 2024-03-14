package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "Category")
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String CategoryName;
    private int ScoreImpact;

    public Category() {
    }

    public Category(Long id, String categoryName, int scoreImpact) {
        this.id = id;
        CategoryName = categoryName;
        ScoreImpact = scoreImpact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
