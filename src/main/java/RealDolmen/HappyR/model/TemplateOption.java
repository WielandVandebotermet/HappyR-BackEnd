package RealDolmen.HappyR.model;

import jakarta.persistence.*;

@Entity
public class TemplateOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Template template;

    private String Setting;
    private boolean SettingValue;

    public TemplateOption() {
    }

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
