package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Survey survey;

    private String Question;
    private String Text;

    public SurveyQuestion() {
    }

    public SurveyQuestion(Long id, Survey survey, String question, String text) {
        this.id = id;
        this.survey = survey;
        Question = question;
        Text = text;
    }

}
