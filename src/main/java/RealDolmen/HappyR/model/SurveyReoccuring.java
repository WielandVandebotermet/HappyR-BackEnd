package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Builder
public class SurveyReoccuring {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "survey_id")
    @JsonIgnore
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
