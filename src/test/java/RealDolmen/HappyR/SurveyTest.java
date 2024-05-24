package RealDolmen.HappyR;

import RealDolmen.HappyR.Data.SurveyQuestionAndCategorieRequest;
import RealDolmen.HappyR.Data.SurveyQuestionRequest;
import RealDolmen.HappyR.Data.SurveyRequest;
import RealDolmen.HappyR.Repository.CategoryRepository;
import RealDolmen.HappyR.Repository.SurveyQuestionRepository;
import RealDolmen.HappyR.Repository.SurveyRepository;
import RealDolmen.HappyR.Service.SurveyService;
import RealDolmen.HappyR.model.Category;
import RealDolmen.HappyR.model.Survey;
import RealDolmen.HappyR.model.SurveyQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

// Import statements for required classes and libraries

// Annotations to indicate the use of Mockito for testing
@ExtendWith(MockitoExtension.class)
public class SurveyTest {
    // Mocking repositories and injecting service for testing
    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private SurveyQuestionRepository surveyQuestionRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private SurveyService surveyService;

    // Testing method for creating a survey
    @Test
    void testCreateSurvey() {
        // Creating a sample survey request
        SurveyRequest surveyRequest = new SurveyRequest();
        surveyRequest.setTestName("Test Survey");
        String dateString = "2024-05-22"; // Example date string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        surveyRequest.setGroupList(null);
        surveyRequest.setStarted(false);

        // Invoking the method under test
        surveyService.createSurvey(surveyRequest);

        // Verifying that the save method is called once on the survey repository
        verify(surveyRepository, times(1)).save(any(Survey.class));
    }

    // Testing method for editing a survey
    @Test
    void testEditSurvey() {
        // Creating a sample survey request
        SurveyRequest surveyRequest = new SurveyRequest();
        surveyRequest.setTestName("Updated Survey");
        String dateString = "2024-05-22"; // Example date string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        surveyRequest.setStartDate(calendar);
        surveyRequest.setGroupList(null);
        surveyRequest.setStarted(true);

        // Mocking behavior of the survey repository
        Survey survey = new Survey();
        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

        // Invoking the method under test
        surveyService.editSurvey(1, surveyRequest);

        // Verifying that the save method is called once on the survey repository
        verify(surveyRepository, times(1)).save(any(Survey.class));
    }

    // Testing method for editing survey started status
    @Test
    void testEditSurveyStarted() {
        // Creating a sample survey
        Survey survey = new Survey();

        // Invoking the method under test
        surveyService.editSurveyStarted(survey, true);

        // Verifying that the save method is called once on the survey repository
        verify(surveyRepository, times(1)).save(any(Survey.class));
    }

    // Testing method for deleting a survey
    @Test
    void testDeleteSurvey() {
        // Invoking the method under test
        surveyService.deleteSurvey(1);

        // Verifying that the deleteById method is called once on the survey repository
        verify(surveyRepository, times(1)).deleteById(anyLong());
    }


    // Testing method for getting a survey question by its ID
    @Test
    void testGetSurveyQuestionById() {
        // Creating a sample survey question
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        when(surveyQuestionRepository.findById(anyLong())).thenReturn(Optional.of(surveyQuestion));

        // Invoking the method under test
        SurveyQuestion result = surveyService.getSurveyQuestionById(1);

        // Verifying that the retrieved survey question matches the expected one
        assertEquals(surveyQuestion, result);
        // Verifying that the findById method is called once on the survey question repository
        verify(surveyQuestionRepository, times(1)).findById(anyLong());
    }

    // Testing method for getting all surveys
    @Test
    void testGetAllSurveys() {
        // Invoking the method under test
        surveyService.getAllSurveys();

        // Verifying that the findAll method is called once on the survey repository
        verify(surveyRepository, times(1)).findAll();
    }

    // Testing method for getting a survey by its ID
    @Test
    void testGetSurveyById() {
        // Creating a sample survey
        Survey survey = new Survey();
        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

        // Invoking the method under test
        Survey result = surveyService.getSurveyById(1);

        // Verifying that the retrieved survey matches the expected one
        assertEquals(survey, result);
        // Verifying that the findById method is called once on the survey repository
        verify(surveyRepository, times(1)).findById(anyLong());
    }

    // Testing method for getting surveys by user ID
    @Test
    void testGetSurveysByUserId() {
        // Invoking the method under test
        surveyService.getSurveysByUserId(1);

        // Verifying that the findSurveysByUserId method is called once on the survey repository
        verify(surveyRepository, times(1)).findSurveysByUserId(anyLong());
    }

    // Testing method for getting surveys by manager ID
    @Test
    void testGetSurveysByManagerId() {
        // Invoking the method under test
        surveyService.getSurveysByManagerId(1);

        // Verifying that the findSurveysByManagerId method is called once on the survey repository
        verify(surveyRepository, times(1)).findSurveysByManagerId(anyInt());
    }

    // Testing method for getting survey results by manager ID
    @Test
    void testGetSurveysResultsByManagerId() {
        // Invoking the method under test
        surveyService.getSurveysResultsByManagerId(1);

        // Verifying that the findSurveysResultsByManagerId method is called once on the survey repository
        verify(surveyRepository, times(1)).findSurveysResultsByManagerId(anyInt());
    }

    // Testing method for creating a survey question and category
    @Test
    void testCreateSurveyQuestionAndCategory() {
        // Creating a sample request
        SurveyQuestionAndCategorieRequest request = new SurveyQuestionAndCategorieRequest();
        request.setSurveyId(1);
        request.setCategoryName("TestCategory");
        request.setScoreImpact(5);
        request.setQuestion("TestQuestion");
        request.setText("TestText");
        request.setTemplateId("1");

        // Mocking behavior of repositories
        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(new Survey()));
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());
        when(surveyQuestionRepository.save(any(SurveyQuestion.class))).thenReturn(new SurveyQuestion());

        // Invoking the method under test
        surveyService.createSurveyQuestionAndCategory(request);

        // Verifying that the findById, save, and save methods are called on respective repositories
        verify(surveyRepository, times(1)).findById(anyLong());
        verify(categoryRepository, times(1)).save(any(Category.class));
        verify(surveyQuestionRepository, times(1)).save(any(SurveyQuestion.class));
    }

    // Testing method for editing a survey question and category
    @Test
    void testEditSurveyQuestionAndCategory() {
        // Creating a sample request
        SurveyQuestionAndCategorieRequest request = new SurveyQuestionAndCategorieRequest();
        request.setSurveyId(1);
        request.setCategoryName("TestCategory");
        request.setScoreImpact(5);
        request.setQuestion("TestQuestion");
        request.setText("TestText");
        request.setTemplateId("1");

        // Mocking behavior of repositories
        when(surveyQuestionRepository.findById(anyLong())).thenReturn(Optional.of(new SurveyQuestion()));
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());
        when(surveyQuestionRepository.save(any(SurveyQuestion.class))).thenReturn(new SurveyQuestion());

        // Invoking the method under test
        surveyService.editSurveyQuestionAndCategory(1, request);

        // Verifying that the findById, save, and save methods are called on respective repositories
        verify(surveyQuestionRepository, times(1)).findById(anyLong());
        verify(categoryRepository, times(1)).save(any(Category.class));
        verify(surveyQuestionRepository, times(1)).save(any(SurveyQuestion.class));
    }

    // Testing method for creating a survey question
    @Test
    void testCreateSurveyQuestion() {
        // Creating a sample request
        SurveyQuestionRequest request = new SurveyQuestionRequest();
        request.setSurveyId(1);
        request.setQuestion("TestQuestion");
        request.setText("TestText");
        request.setTemplateId("1");

        // Mocking behavior of repositories
        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(new Survey()));
        when(surveyQuestionRepository.save(any(SurveyQuestion.class))).thenReturn(new SurveyQuestion());

        // Invoking the method under test
        surveyService.createSurveyQuestion(request);

        // Verifying that the findById and save methods are called on respective repositories
        verify(surveyRepository, times(1)).findById(anyLong());
        verify(surveyQuestionRepository, times(1)).save(any(SurveyQuestion.class));
    }

    // Testing method for editing a survey question
    @Test
    void testEditSurveyQuestion() {
        // Creating a sample request
        SurveyQuestionRequest request = new SurveyQuestionRequest();
        request.setSurveyId(1);
        request.setQuestion("TestQuestion");
        request.setText("TestText");
        request.setTemplateId("1");

        // Mocking behavior of repositories
        when(surveyQuestionRepository.findById(anyLong())).thenReturn(Optional.of(new SurveyQuestion()));
        when(surveyQuestionRepository.save(any(SurveyQuestion.class))).thenReturn(new SurveyQuestion());

        // Invoking the method under test
        surveyService.editSurveyQuestion(1, request);

        // Verifying that the findById and save methods are called on the survey question repository
        verify(surveyQuestionRepository, times(1)).findById(anyLong());
        verify(surveyQuestionRepository, times(1)).save(any(SurveyQuestion.class));
    }

    // Testing method for deleting a survey question
    @Test
    void testDeleteSurveyQuestion() {
        // Invoking the method under test
        surveyService.deleteSurveyQuestion(1);

        // Verifying that the deleteById method is called on the survey question repository
        verify(surveyQuestionRepository, times(1)).deleteById(anyLong());
    }
}