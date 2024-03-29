package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.*;

@Table(name = "Template")
@Builder
@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String templateName;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateOption> options;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateQuestion> questions;

    @ElementCollection
    private List<String> externalPeople;

    public Template() {

    }

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
