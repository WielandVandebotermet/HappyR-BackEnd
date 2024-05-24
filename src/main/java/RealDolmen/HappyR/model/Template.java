package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.*;

/**
 * Model class representing a template for surveys.
 */
@Table(name = "Template") // Specifies the table name in the database
@Builder // Provides a builder pattern for creating instances of the class
@Entity // Specifies that this class is an entity
public class Template {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the template

    private String templateName; // Name of the template

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateOption> options; // Options associated with the template

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateQuestion> questions; // Questions associated with the template

    @ElementCollection // Specifies a collection of basic types
    private List<String> externalPeople; // External people related to the template

    /**
     * Default constructor.
     */
    public Template() {
    }

    /**
     * Parameterized constructor to initialize template data.
     *
     * @param id             The ID of the template
     * @param templateName   The name of the template
     * @param options        The options associated with the template
     * @param questions      The questions associated with the template
     * @param externalPeople The external people related to the template
     */
    public Template(Long id, String templateName, List<TemplateOption> options, List<TemplateQuestion> questions, List<String> externalPeople) {
        this.id = id;
        this.templateName = templateName;
        this.options = options;
        this.questions = questions;
        this.externalPeople = externalPeople;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<TemplateOption> getOptions() {
        return options;
    }

    public void setOptions(List<TemplateOption> options) {
        this.options = options;
    }

    public List<TemplateQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TemplateQuestion> questions) {
        this.questions = questions;
    }

    public List<String> getExternalPeople() {
        return externalPeople;
    }

    public void setExternalPeople(List<String> externalPeople) {
        this.externalPeople = externalPeople;
    }
}
