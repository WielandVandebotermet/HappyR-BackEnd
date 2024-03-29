package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Data.SurveyRequest;
import RealDolmen.HappyR.Repository.SurveyQuestionRepository;
import RealDolmen.HappyR.Repository.SurveyReoccuringRepository;
import RealDolmen.HappyR.Repository.SurveyRepository;
import RealDolmen.HappyR.model.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository SurveyRepository;
    private final SurveyQuestionRepository surveyQuestionRepository;
    private final SurveyReoccuringRepository surveyReoccuringRepository;

    public void createSurvey(SurveyRequest surveyRequest){

        Survey survey = Survey.builder()
                .testName(surveyRequest.getTestName())
                .startDate(surveyRequest.getStartDate())
                .questions(null)
                .groupList(surveyRequest.getGroupList())
                .started(surveyRequest.getStarted())
                .build();

            if(surveyRequest.getReoccuring() != null) {
                Map<String, Object> reoccuring = surveyRequest.getReoccuring();
                Integer time = (Integer) reoccuring.get("Time");
                String timeMultiplier = (String) reoccuring.get("Multiplier");

            SurveyReoccuring reoccurring = new SurveyReoccuring();
                reoccurring.setSurvey(survey);
                reoccurring.setTime(time);
                reoccurring.setTimeMultiplier(timeMultiplier);

            survey.setSurveyReoccuring(reoccurring);
        }
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
            survey.setSurveyReoccuring(surveyRequest.getSurveyReoccuring());
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

    public List<Survey> getSurveysByUserId(int userId) {
        List<Survey> surveys = SurveyRepository.findSurveysByUserId((long) userId);

        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    public List<Survey> getSurveysByManagerId(int managerId) {
        List<Survey> surveys = SurveyRepository.findSurveysByManagerId((long) managerId);

        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    public void editSurveyReoccuring(int id, SurveyReoccuring surveyRequest){
        SurveyReoccuring reoccuring = surveyReoccuringRepository.findById((long) id).orElse(null);

        if(reoccuring != null)
        {
            reoccuring.setId(reoccuring.getId());
            reoccuring.setTime(surveyRequest.getTime());
            reoccuring.setTimeMultiplier(surveyRequest.getTimeMultiplier());

            surveyReoccuringRepository.save(reoccuring);
        }
    }
    public void deleteSurveyReoccuring(int id){
        surveyReoccuringRepository.deleteById((long) id);
    }


    public void createSurveyQuestion(int SurveyId, SurveyQuestion surveyQuestion){
        Survey survey = SurveyRepository.findById((long) SurveyId).orElse(null);

        if(survey != null) {
            SurveyQuestion question = SurveyQuestion.builder()
                    .survey(survey)
                    .Question(surveyQuestion.getQuestion())
                    .Text(surveyQuestion.getText())
                    .options(surveyQuestion.getOptions())
                    .build();

            surveyQuestionRepository.save(question);
        }
    }

    public void editSurveyQuestion(int id, SurveyQuestion surveyRequest){
        SurveyQuestion question = surveyQuestionRepository.findById((long) id).orElse(null);

        if(question != null)
        {
            question.setId(question.getId());
            question.setQuestion(surveyRequest.getQuestion());
            question.setText(surveyRequest.getText());
            question.setOptions(surveyRequest.getOptions());

            surveyQuestionRepository.save(question);
        }
    }
    public void deleteSurveyQuestion(int id){
        surveyQuestionRepository.deleteById((long) id);
    }


    private Survey mapToSurveyResponse(Survey survey) {
        return Survey.builder()
                .id(survey.getId())
                .testName(survey.getTestName())
                .startDate(survey.getStartDate())
                .surveyReoccuring(survey.getSurveyReoccuring())
                .questions(survey.getQuestions())
                .groupList(survey.getGroupList())
                .started(survey.getStarted())
                .build();
    }

}
