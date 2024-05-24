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
/**
 * Service class for managing operations related to surveys.
 */
@Service
@RequiredArgsConstructor // Generates a constructor with required arguments for the final fields
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyQuestionRepository surveyQuestionRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Creates a new survey.
     *
     * @param surveyRequest The survey object to be created.
     */
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

    /**
     * Edits an existing survey.
     *
     * @param id            The ID of the survey to be edited.
     * @param surveyRequest The updated survey object.
     */
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

    /**
     * Edits the started status of a survey.
     *
     * @param survey  The survey to be edited.
     * @param started The new started status.
     */
    public void editSurveyStarted(Survey survey, boolean started) {
        if (survey != null) {
            survey.setStarted(started);
            surveyRepository.save(survey);
        }
    }

    /**
     * Deletes a survey by its ID.
     *
     * @param id The ID of the survey to be deleted.
     */
    public void deleteSurvey(int id) {
        surveyRepository.deleteById((long) id);
    }

    /**
     * Retrieves a survey question by its ID.
     *
     * @param id The ID of the survey question.
     * @return The survey question object if found, otherwise null.
     */
    public SurveyQuestion getSurveyQuestionById(int id) {
        return surveyQuestionRepository.findById((long) id).orElse(null);
    }

    /**
     * Retrieves all surveys.
     *
     * @return A list of all surveys.
     */
    public List<Survey> getAllSurveys() {
        List<Survey> surveys = surveyRepository.findAll();
        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    /**
     * Retrieves a survey by its ID.
     *
     * @param id The ID of the survey.
     * @return The survey object if found, otherwise null.
     */
    public Survey getSurveyById(int id) {
        return surveyRepository.findById((long) id).orElse(null);
    }

    /**
     * Retrieves surveys by user ID.
     *
     * @param userId The ID of the user.
     * @return A list of surveys associated with the user.
     */
    public List<Survey> getSurveysByUserId(int userId) {
        List<Survey> surveys = surveyRepository.findSurveysByUserId((long) userId);
        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    /**
     * Retrieves surveys by manager ID.
     *
     * @param managerId The ID of the manager.
     * @return A list of surveys associated with the manager.
     */
    public List<Survey> getSurveysByManagerId(int managerId) {
        List<Survey> surveys = surveyRepository.findSurveysByManagerId(managerId);
        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    /**
     * Retrieves surveys results by manager ID.
     *
     * @param managerId The ID of the manager.
     * @return A list of surveys results associated with the manager.
     */
    public List<Survey> getSurveysResultsByManagerId(int managerId) {
        List<Survey> surveys = surveyRepository.findSurveysResultsByManagerId(managerId);
        return surveys.stream().map(this::mapToSurveyResponse).toList();
    }

    /**
     * Creates a survey question along with its category.
     *
     * @param surveyQuestionAndCategorieRequest The request containing information about the survey question and category.
     */
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

    /**
     * Edits a survey question along with its category.
     *
     * @param id                                  The ID of the survey question.
     * @param surveyQuestionAndCategorieRequest The updated request containing information about the survey question and category.
     */
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

    /**
     * Creates a new survey question based on the provided request.
     *
     * @param surveyQuestionRequest The request containing information about the survey question.
     */
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

    /**
     * Edits an existing survey question based on the provided request.
     *
     * @param id                     The ID of the survey question to edit.
     * @param surveyQuestionRequest The updated request containing information about the survey question.
     */
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

    /**
     * Deletes a survey question based on the provided ID.
     *
     * @param id The ID of the survey question to delete.
     */
    public void deleteSurveyQuestion(int id) {
        surveyQuestionRepository.deleteById((long) id);
    }

    /**
     * Maps a survey entity to a response object for presentation.
     *
     * @param survey The survey entity to map.
     * @return A mapped survey response object.
     */
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
