package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
/**
 * Model class representing questions associated with a survey template.
 */
@Entity // Specifies that this class is an entity
public class TemplateQuestion {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the template question

    @ManyToOne // Specifies many-to-one relationship with Template entity
    @JoinColumn(name = "template_id") // Specifies the foreign key column
    @JsonIgnore // Ignores this property during JSON serialization
    private Template template; // Template associated with the question

    private String Question; // The question text
    private String Text; // Additional text related to the question

    /**
     * Default constructor.
     */
    public TemplateQuestion() {
    }

    /**
     * Parameterized constructor to initialize template question data.
     *
     * @param id       The ID of the template question
     * @param template The template associated with the question
     * @param question The text of the question
     * @param text     Additional text related to the question
     */
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
