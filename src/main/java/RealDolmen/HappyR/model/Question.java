package RealDolmen.HappyR.model;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;

public class Question {
    private int id;
    private int Surveyid;
    private HashMap<String, Boolean> options;
    private HashMap<String, String> q;
    private String[] ExternalPeople;

    public Question(int id, int surveyid, HashMap<String, Boolean> options, HashMap<String, String> q, String[] externalPeople) {
        this.id = id;
        Surveyid = surveyid;
        this.options = options;
        this.q = q;
        ExternalPeople = externalPeople;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurveyid() {
        return Surveyid;
    }

    public void setSurveyid(int surveyid) {
        Surveyid = surveyid;
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
        return ExternalPeople;
    }

    public void setExternalPeople(String[] externalPeople) {
        ExternalPeople = externalPeople;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", Surveyid=" + Surveyid +
                ", options=" + options +
                ", q=" + q +
                ", ExternalPeople=" + Arrays.toString(ExternalPeople) +
                '}';
    }
}

