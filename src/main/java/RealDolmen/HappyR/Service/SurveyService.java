package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Data.SurveyQuestionOptionRequest;
import RealDolmen.HappyR.Data.SurveyQuestionRequest;
import RealDolmen.HappyR.Data.SurveyQuestionSettingRequest;
import RealDolmen.HappyR.Data.SurveyRequest;
import RealDolmen.HappyR.Repository.SurveyQuestionRepository;
import RealDolmen.HappyR.Repository.SurveyReoccuringRepository;
import RealDolmen.HappyR.Repository.SurveyRepository;
import RealDolmen.HappyR.model.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                .started(surveyRequest.isStarted())
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

    public void editSurvey(int id, SurveyRequest surveyRequest){
        Survey survey = SurveyRepository.findById((long) id).orElse(null);

        if(survey != null)
        {
            survey.setId(survey.getId());
            survey.setStarted(surveyRequest.isStarted());
            survey.setTestName(surveyRequest.getTestName());
            survey.setStartDate(surveyRequest.getStartDate());
            survey.setGroupList(surveyRequest.getGroupList());

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
            else {
                surveyReoccuringRepository.delete(survey.getSurveyReoccuring());
                survey.setSurveyReoccuring(null);
            }

            SurveyRepository.save(survey);
        }
    }
    public void deleteSurvey(int id){
        SurveyRepository.deleteById((long) id);
    }

    public SurveyQuestion getSurveyQuestionById(int id) {
        return surveyQuestionRepository.findById((long) id).orElse(null);
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
        List<Survey> surveys = SurveyRepository.findSurveysResultsByManagerId(managerId);

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


    public void createSurveyQuestion(SurveyQuestionRequest surveyQuestionRequest){
        Survey survey = SurveyRepository.findById((long) surveyQuestionRequest.getSurveyId()).orElse(null);

        if (survey != null) {
            SurveyQuestion question = new SurveyQuestion();
            question.setSurvey(survey);
            question.setQuestion(surveyQuestionRequest.getQuestion());
            question.setText(surveyQuestionRequest.getText());
            question.setTemplateId(surveyQuestionRequest.getTemplateId());

            // Initialize lists for options and settings
            List<SurveyQuestionOption> options = new ArrayList<>();
            List<SurveyQuestionSetting> settings = new ArrayList<>();

            // Iterate over options from the request and create SurveyQuestionOption objects
            for (SurveyQuestionOptionRequest optionRequest : surveyQuestionRequest.getOptions()) {
                SurveyQuestionOption option = new SurveyQuestionOption();
                option.setSurveyQuestion(question);
                option.setSetting(optionRequest.getSetting());
                option.setSettingValue(optionRequest.isSettingValue());
                options.add(option);
            }

            // Iterate over settings from the request and create SurveyQuestionSetting objects
            for (SurveyQuestionSettingRequest settingRequest : surveyQuestionRequest.getSettings()) {
                SurveyQuestionSetting setting = new SurveyQuestionSetting();
                setting.setSurveyQuestion(question);
                setting.setQuestion(settingRequest.getQuestion()); // Assuming getQuestion() retrieves Bmax/Bmin
                setting.setText(settingRequest.getText());       // Assuming getText() retrieves the value of Bmax/Bmin
                settings.add(setting);
            }

            // Set options and settings lists in the question
            question.setOptions(options);
            question.setSettings(settings);

            // Save the question along with its options and settings
            surveyQuestionRepository.save(question);
        }
    }

    public void editSurveyQuestion(int id, SurveyQuestionRequest surveyQuestionRequest){
        SurveyQuestion question = surveyQuestionRepository.findById((long) id).orElse(null);

        if (question != null) {
            question.setQuestion(surveyQuestionRequest.getQuestion());
            question.setText(surveyQuestionRequest.getText());

            List<SurveyQuestionOptionRequest> optionRequests = surveyQuestionRequest.getOptions();
            List<SurveyQuestionOption> options = new ArrayList<>();
            if (optionRequests != null) {
                for (SurveyQuestionOptionRequest optionRequest : optionRequests) {
                    SurveyQuestionOption option = new SurveyQuestionOption();
                    // Populate option properties from optionRequest
                    option.setSetting(optionRequest.getSetting());
                    option.setSettingValue(optionRequest.isSettingValue());
                    // Add option to the list
                    options.add(option);
                }
            }

            // Update settings
            List<SurveyQuestionSettingRequest> settingRequests = surveyQuestionRequest.getSettings();
            List<SurveyQuestionSetting> settings = new ArrayList<>();
            if (settingRequests != null) {
                for (SurveyQuestionSettingRequest settingRequest : settingRequests) {
                    SurveyQuestionSetting setting = new SurveyQuestionSetting();
                    // Populate setting properties from settingRequest
                    setting.setQuestion(settingRequest.getQuestion());
                    setting.setText(settingRequest.getText());
                    // Add setting to the list
                    settings.add(setting);
                }
            }

            // Save the updated question
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
