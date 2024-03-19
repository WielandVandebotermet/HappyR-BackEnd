package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Repository.SurveyRepository;
import RealDolmen.HappyR.model.Survey;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository SurveyRepository;

    public void createSurvey(Survey surveyRequest){
        Survey survey = Survey.builder()
                .testName(surveyRequest.getTestName())
                .startDate(surveyRequest.getStartDate())
                .reoccuring(surveyRequest.getReoccuring())
                .questions(surveyRequest.getQuestions())
                .groupList(surveyRequest.getGroupList())
                .started(surveyRequest.getStarted())
                .build();

        SurveyRepository.save(survey);
    }

    public void editSurvey(int id, Survey surveyRequest){
        Survey survey = SurveyRepository.findById((long) id).orElse(null);

        if(survey != null)
        {
            survey.setId(survey.getId());
            survey.setStarted(surveyRequest.getStarted());
            survey.setTestName(surveyRequest.getTestName());
            survey.setStartDate(surveyRequest.getStartDate());
            survey.setReoccuring(surveyRequest.getReoccuring());
            survey.setQuestions(surveyRequest.getQuestions());
            survey.setGroupList(surveyRequest.getGroupList());

            SurveyRepository.save(survey);
        }
    }
    public void deleteSurvey(int id){
        SurveyRepository.deleteById((long) id);
    }

    public List<Survey> getAllSurveys() {
        List<Survey> surveys = SurveyRepository.findAll();

        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    public Survey getSurveyById(int id){
        return SurveyRepository.findById((long) id).orElse(null);
    }

    private Survey mapToSurveyResponse(Survey survey) {
        return Survey.builder()
                .id(survey.getId())
                .testName(survey.getTestName())
                .startDate(survey.getStartDate())
                .reoccuring(survey.getReoccuring())
                .questions(survey.getQuestions())
                .groupList(survey.getGroupList())
                .started(survey.getStarted())
                .build();
    }

}
