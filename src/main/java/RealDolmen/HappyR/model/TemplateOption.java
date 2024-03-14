package RealDolmen.HappyR.model;

import jakarta.persistence.*;

@Entity
public class TemplateOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Template template;

    private String key;
    private boolean value;

    public TemplateOption() {
    }

    public TemplateOption(Long id, Template template, String key, boolean value) {
        this.id = id;
        this.template = template;
        this.key = key;
        this.value = value;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
