package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Builder;
/**
 * Model class representing a category.
 */
@Entity
@Table(name = "Category") // Specifies the table name in the database
@Builder // Lombok annotation for builder pattern
public class Category {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the category

    private String CategoryName; // Name of the category
    private int ScoreImpact; // Impact score of the category

    /**
     * Default constructor.
     */
    public Category() {
    }

    /**
     * Parameterized constructor to initialize category data.
     *
     * @param id            The ID of the category
     * @param categoryName  The name of the category
     * @param scoreImpact   The impact score of the category
     */
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
