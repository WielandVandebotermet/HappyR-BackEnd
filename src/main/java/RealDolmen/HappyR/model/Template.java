package RealDolmen.HappyR.model;

import java.util.List;

public class Template {
    private int id;
    private String TemplateName;
    private Question Question;

    public Template(int id, String templateName, Question question) {
        this.id = id;
        TemplateName = templateName;
        Question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemplateName() {
        return TemplateName;
    }

    public void setTemplateName(String templateName) {
        TemplateName = templateName;
    }

    public Question getQuestion() {
        return Question;
    }

    public void setQuestion(Question question) {
        Question = question;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", TemplateName='" + TemplateName + '\'' +
                ", Question=" + Question +
                '}';
    }
}
