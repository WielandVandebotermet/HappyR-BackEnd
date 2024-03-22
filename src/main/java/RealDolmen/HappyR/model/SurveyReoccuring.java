package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class SurveyReoccuring {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Survey survey;

    private int Time;

    private String TimeMultiplier;

    public SurveyReoccuring() {
    }

    public SurveyReoccuring(Long id, Survey survey, int time, String timeMultiplier) {
        this.id = id;
        this.survey = survey;
        Time = time;
        TimeMultiplier = timeMultiplier;
    }

}
