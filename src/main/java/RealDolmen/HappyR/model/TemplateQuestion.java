package RealDolmen.HappyR.model;

import jakarta.persistence.*;

@Entity
public class TemplateQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Template template;

    private String Question;
    private String Text;

    public TemplateQuestion() {
    }

    public TemplateQuestion(Long id, Template template, String question, String text) {
        this.id = id;
        this.template = template;
        Question = question;
        Text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
