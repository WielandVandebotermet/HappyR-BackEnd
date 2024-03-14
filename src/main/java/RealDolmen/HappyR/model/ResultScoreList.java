package RealDolmen.HappyR.model;

import jakarta.persistence.*;

@Entity
public class ResultScoreList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Result result;

    private String questionId;
    private String score;

    public ResultScoreList() {
    }

    public ResultScoreList(int id, Result result, String questionId, String score) {
        this.id = id;
        this.result = result;
        this.questionId = questionId;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
