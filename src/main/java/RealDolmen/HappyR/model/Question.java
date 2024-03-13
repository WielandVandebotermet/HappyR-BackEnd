package RealDolmen.HappyR.model;

import java.util.Arrays;
import java.util.Dictionary;

public class Question {
    private int id;
    private int Templateid;

    private Dictionary<String, Boolean> Options;

    private Dictionary<String, String> Q;

    private String[] ExternalPeople;

    public Question(int id, int templateid, Dictionary<String, Boolean> options, Dictionary<String, String> q, String[] externalPeople) {
        this.id = id;
        Templateid = templateid;
        Options = options;
        Q = q;
        ExternalPeople = externalPeople;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemplateid() {
        return Templateid;
    }

    public void setTemplateid(int templateid) {
        Templateid = templateid;
    }

    public Dictionary<String, Boolean> getOptions() {
        return Options;
    }

    public void setOptions(Dictionary<String, Boolean> options) {
        Options = options;
    }

    public Dictionary<String, String> getQ() {
        return Q;
    }

    public void setQ(Dictionary<String, String> q) {
        Q = q;
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
                ", Templateid=" + Templateid +
                ", Options=" + Options +
                ", Q=" + Q +
                ", ExternalPeople=" + Arrays.toString(ExternalPeople) +
                '}';
    }
}
