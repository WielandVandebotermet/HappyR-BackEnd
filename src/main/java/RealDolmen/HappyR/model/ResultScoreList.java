package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * Model class representing a result score list.
 */
@Entity // Specifies that this class is an entity
@Table(name = "ResultScoreList") // Specifies the table name in the database
public class ResultScoreList {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the result score list

    @ManyToOne // Specifies a many-to-one relationship
    @JsonIgnore // Ignores this property during JSON serialization/deserialization
    private Result result; // Result associated with the score list

    private int questionId; // ID of the question associated with the score

    private int score; // Score value

    private int categoryId; // ID of the category associated with the score

    /**
     * Default constructor.
     */
    public ResultScoreList() {
    }

    /**
     * Parameterized constructor to initialize result score list data.
     *
     * @param id          The ID of the result score list
     * @param result      The result associated with the score list
     * @param questionId  The ID of the question associated with the score
     * @param score       The score value
     * @param categoryId  The ID of the category associated with the score
     */
    public ResultScoreList(Long id, Result result, int questionId, int score, int categoryId) {
        this.id = id;
        this.result = result;
        this.questionId = questionId;
        this.score = score;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
