package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Data.*;
import RealDolmen.HappyR.Repository.CategoryRepository;
import RealDolmen.HappyR.Repository.SurveyQuestionRepository;
import RealDolmen.HappyR.Repository.SurveyRepository;
import RealDolmen.HappyR.model.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyQuestionRepository surveyQuestionRepository;
    private final CategoryRepository categoryRepository;

    public void createSurvey(SurveyRequest surveyRequest) {
        Survey survey = Survey.builder()
                .testName(surveyRequest.getTestName())
                .startDate(surveyRequest.getStartDate())
                .questions(null)
                .groupList(surveyRequest.getGroupList())
                .started(surveyRequest.isStarted())
                .build();

        surveyRepository.save(survey);
    }

    public void editSurvey(int id, SurveyRequest surveyRequest) {
        Survey survey = surveyRepository.findById((long) id).orElse(null);

        if (survey != null) {
            survey.setId(survey.getId());
            survey.setStarted(surveyRequest.isStarted());
            survey.setTestName(surveyRequest.getTestName());
            survey.setStartDate(surveyRequest.getStartDate());
            survey.setGroupList(surveyRequest.getGroupList());

            surveyRepository.save(survey);
        }
    }

    public void editSurveyStarted(Survey survey, boolean started) {
        if (survey != null) {
            survey.setStarted(started);
            surveyRepository.save(survey);
        }
    }

    public void deleteSurvey(int id) {
        surveyRepository.deleteById((long) id);
    }

    public SurveyQuestion getSurveyQuestionById(int id) {
        return surveyQuestionRepository.findById((long) id).orElse(null);
    }

    public List<Survey> getAllSurveys() {
        List<Survey> surveys = surveyRepository.findAll();
        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    public Survey getSurveyById(int id) {
        return surveyRepository.findById((long) id).orElse(null);
    }

    public List<Survey> getSurveysByUserId(int userId) {
        List<Survey> surveys = surveyRepository.findSurveysByUserId((long) userId);
        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    public List<Survey> getSurveysByManagerId(int managerId) {
        List<Survey> surveys = surveyRepository.findSurveysByManagerId(managerId);
        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    public List<Survey> getSurveysResultsByManagerId(int managerId) {
        List<Survey> surveys = surveyRepository.findSurveysResultsByManagerId(managerId);
        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    public void createSurveyQuestionAndCategory(SurveyQuestionAndCategorieRequest surveyQuestionAndCategorieRequest) {
        Survey survey = surveyRepository.findById((long) surveyQuestionAndCategorieRequest.getSurveyId()).orElse(null);

        if (survey != null) {
            Category category = Category.builder()
                    .CategoryName(surveyQuestionAndCategorieRequest.getCategoryName())
                    .ScoreImpact(surveyQuestionAndCategorieRequest.getScoreImpact())
                    .build();

            categoryRepository.save(category);

            SurveyQuestion question = new SurveyQuestion();
            question.setSurvey(survey);
            question.setQuestion(surveyQuestionAndCategorieRequest.getQuestion());
            question.setText(surveyQuestionAndCategorieRequest.getText());
            question.setTemplateId(surveyQuestionAndCategorieRequest.getTemplateId());

            List<SurveyQuestionOption> options = new ArrayList<>();
            List<SurveyQuestionSetting> settings = new ArrayList<>();

            // Handle options
            List<SurveyQuestionOptionRequest> optionRequests = surveyQuestionAndCategorieRequest.getOptions();
            if (optionRequests != null) {
                for (SurveyQuestionOptionRequest optionRequest : optionRequests) {
                    SurveyQuestionOption option = new SurveyQuestionOption();
                    option.setSetting(optionRequest.getSetting());
                    option.setSettingValue(optionRequest.isSettingValue());
                    option.setSurveyQuestion(question); // Maintain bidirectional relationship if needed
                    options.add(option);
                }
            }

            // Handle settings
            List<SurveyQuestionSettingRequest> settingRequests = surveyQuestionAndCategorieRequest.getSettings();
            if (settingRequests != null) {
                for (SurveyQuestionSettingRequest settingRequest : settingRequests) {
                    SurveyQuestionSetting setting = new SurveyQuestionSetting();
                    setting.setQuestion(settingRequest.getQuestion());
                    if (settingRequest.getQuestion().equals("categorieId")) {
                        setting.setText(String.valueOf(category.getId()));
                    } else {
                        setting.setText(settingRequest.getText());
                    }                    setting.setSurveyQuestion(question); // Maintain bidirectional relationship if needed
                    settings.add(setting);
                }
            }
            question.setOptions(options);
            question.setSettings(settings);

            surveyQuestionRepository.save(question);
        }
    }

    public void editSurveyQuestionAndCategory(int id, SurveyQuestionAndCategorieRequest surveyQuestionAndCategorieRequest) {
        SurveyQuestion question = surveyQuestionRepository.findById((long) id).orElse(null);

        if (question != null) {
            Category category = Category.builder()
                    .CategoryName(surveyQuestionAndCategorieRequest.getCategoryName())
                    .ScoreImpact(surveyQuestionAndCategorieRequest.getScoreImpact())
                    .build();

            categoryRepository.save(category);

            question.setQuestion(surveyQuestionAndCategorieRequest.getQuestion());
            question.setText(surveyQuestionAndCategorieRequest.getText());

            // Handle options
            List<SurveyQuestionOptionRequest> optionRequests = surveyQuestionAndCategorieRequest.getOptions();
            if (optionRequests != null) {
                question.getOptions().clear(); // Clear the existing collection
                for (SurveyQuestionOptionRequest optionRequest : optionRequests) {
                    SurveyQuestionOption option = new SurveyQuestionOption();
                    option.setSetting(optionRequest.getSetting());
                    option.setSettingValue(optionRequest.isSettingValue());
                    option.setSurveyQuestion(question); // Maintain bidirectional relationship if needed
                    question.getOptions().add(option);
                }
            }

            // Handle settings
            List<SurveyQuestionSettingRequest> settingRequests = surveyQuestionAndCategorieRequest.getSettings();
            if (settingRequests != null) {
                question.getSettings().clear(); // Clear the existing collection
                for (SurveyQuestionSettingRequest settingRequest : settingRequests) {
                    SurveyQuestionSetting setting = new SurveyQuestionSetting();
                    setting.setQuestion(settingRequest.getQuestion());
                    if (settingRequest.getQuestion().equals("categorieId")) {
                        setting.setText(String.valueOf(category.getId()));
                    } else {
                        setting.setText(settingRequest.getText());
                    }
                    setting.setSurveyQuestion(question); // Maintain bidirectional relationship if needed
                    question.getSettings().add(setting);
                }
            }

            surveyQuestionRepository.save(question);
        }
    }

    public void createSurveyQuestion(SurveyQuestionRequest surveyQuestionRequest) {
        Survey survey = surveyRepository.findById((long) surveyQuestionRequest.getSurveyId()).orElse(null);

        if (survey != null) {
            SurveyQuestion question = new SurveyQuestion();
            question.setSurvey(survey);
            question.setQuestion(surveyQuestionRequest.getQuestion());
            question.setText(surveyQuestionRequest.getText());
            question.setTemplateId(surveyQuestionRequest.getTemplateId());

            List<SurveyQuestionOption> options = new ArrayList<>();
            List<SurveyQuestionSetting> settings = new ArrayList<>();

            // Handle options
            List<SurveyQuestionOptionRequest> optionRequests = surveyQuestionRequest.getOptions();
            if (optionRequests != null) {
                for (SurveyQuestionOptionRequest optionRequest : optionRequests) {
                    SurveyQuestionOption option = new SurveyQuestionOption();
                    option.setSetting(optionRequest.getSetting());
                    option.setSettingValue(optionRequest.isSettingValue());
                    option.setSurveyQuestion(question); // Maintain bidirectional relationship if needed
                    options.add(option);
                }
            }

            // Handle settings
            List<SurveyQuestionSettingRequest> settingRequests = surveyQuestionRequest.getSettings();
            if (settingRequests != null) {
                for (SurveyQuestionSettingRequest settingRequest : settingRequests) {
                    SurveyQuestionSetting setting = new SurveyQuestionSetting();
                    setting.setQuestion(settingRequest.getQuestion());
                    setting.setText(settingRequest.getText());
                    setting.setSurveyQuestion(question); // Maintain bidirectional relationship if needed
                    settings.add(setting);
                }
            }
            question.setOptions(options);
            question.setSettings(settings);

            surveyQuestionRepository.save(question);
        }
    }

    public void editSurveyQuestion(int id, SurveyQuestionRequest surveyQuestionRequest) {
        SurveyQuestion question = surveyQuestionRepository.findById((long) id).orElse(null);

        if (question != null) {
            question.setQuestion(surveyQuestionRequest.getQuestion());
            question.setText(surveyQuestionRequest.getText());

            // Handle options
            List<SurveyQuestionOptionRequest> optionRequests = surveyQuestionRequest.getOptions();
            if (optionRequests != null) {
                question.getOptions().clear(); // Clear the existing collection
                for (SurveyQuestionOptionRequest optionRequest : optionRequests) {
                    SurveyQuestionOption option = new SurveyQuestionOption();
                    option.setSurveyQuestion(question); // Maintain bidirectional relationship if needed
                    option.setSetting(optionRequest.getSetting());
                    option.setSettingValue(optionRequest.isSettingValue());
                    question.getOptions().add(option);
                }
            }

            // Handle settings
            List<SurveyQuestionSettingRequest> settingRequests = surveyQuestionRequest.getSettings();
            if (settingRequests != null) {
                question.getSettings().clear(); // Clear the existing collection
                for (SurveyQuestionSettingRequest settingRequest : settingRequests) {
                    SurveyQuestionSetting setting = new SurveyQuestionSetting();
                    setting.setQuestion(settingRequest.getQuestion());
                    setting.setText(settingRequest.getText());
                    setting.setSurveyQuestion(question); // Maintain bidirectional relationship if needed
                    question.getSettings().add(setting);
                }
            }

            surveyQuestionRepository.save(question);
        }
    }

    public void deleteSurveyQuestion(int id) {
        surveyQuestionRepository.deleteById((long) id);
    }

    private Survey mapToSurveyResponse(Survey survey) {
        return Survey.builder()
                .id(survey.getId())
                .testName(survey.getTestName())
                .startDate(survey.getStartDate())
                .questions(survey.getQuestions())
                .groupList(survey.getGroupList())
                .started(survey.getStarted())
                .build();
    }
}
