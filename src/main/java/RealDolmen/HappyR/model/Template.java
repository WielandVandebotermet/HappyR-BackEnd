package RealDolmen.HappyR.model;

import java.util.*;

public class Template {
    private int id;
    private String templateName;

    private HashMap<String, Boolean> options;
    private HashMap<String, String> q;
    private String[] externalPeople;

    public Template(int id, String templateName, HashMap<String, Boolean> options, HashMap<String, String> q, String[] externalPeople) {
        this.id = id;
        this.templateName = templateName;
        this.options = options;
        this.q = q;
        this.externalPeople = externalPeople;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public HashMap<String, Boolean> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, Boolean> options) {
        this.options = options;
    }

    public HashMap<String, String> getQ() {
        return q;
    }

    public void setQ(HashMap<String, String> q) {
        this.q = q;
    }

    public String[] getExternalPeople() {
        return externalPeople;
    }

    public void setExternalPeople(String[] externalPeople) {
        this.externalPeople = externalPeople;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", templateName='" + templateName + '\'' +
                ", options=" + options +
                ", q=" + q +
                ", externalPeople=" + Arrays.toString(externalPeople) +
                '}';
    }
}
