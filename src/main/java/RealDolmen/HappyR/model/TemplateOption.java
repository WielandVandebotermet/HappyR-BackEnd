package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
/**
 * Model class representing options associated with a survey template.
 */
@Entity // Specifies that this class is an entity
public class TemplateOption {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the template option

    @ManyToOne // Specifies many-to-one relationship with Template entity
    @JoinColumn(name = "template_id") // Specifies the foreign key column
    @JsonIgnore // Ignores this property during JSON serialization
    private Template template; // Template associated with the option

    private String Setting; // Setting of the option
    private boolean SettingValue; // Value of the setting

    /**
     * Default constructor.
     */
    public TemplateOption() {
    }

    /**
     * Parameterized constructor to initialize template option data.
     *
     * @param id           The ID of the template option
     * @param template     The template associated with the option
     * @param setting      The setting of the option
     * @param settingValue The value of the setting
     */
    public TemplateOption(Long id, Template template, String setting, boolean settingValue) {
        this.id = id;
        this.template = template;
        Setting = setting;
        SettingValue = settingValue;
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

    public String getSetting() {
        return Setting;
    }

    public void setSetting(String setting) {
        Setting = setting;
    }

    public boolean isSettingValue() {
        return SettingValue;
    }

    public void setSettingValue(boolean settingValue) {
        SettingValue = settingValue;
    }
}
