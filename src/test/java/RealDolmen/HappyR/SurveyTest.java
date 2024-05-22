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

@ExtendWith(MockitoExtension.class)
public class SurveyTest {
    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private SurveyQuestionRepository surveyQuestionRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private SurveyService surveyService;

    @Test
    void testCreateSurvey() {
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

        surveyService.createSurvey(surveyRequest);

        verify(surveyRepository, times(1)).save(any(Survey.class));
    }

    @Test
    void testEditSurvey() {
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

        Survey survey = new Survey();
        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

        surveyService.editSurvey(1, surveyRequest);

        verify(surveyRepository, times(1)).save(any(Survey.class));
    }

    @Test
    void testEditSurveyStarted() {
        Survey survey = new Survey();

        surveyService.editSurveyStarted(survey, true);

        verify(surveyRepository, times(1)).save(any(Survey.class));
    }

    @Test
    void testDeleteSurvey() {
        surveyService.deleteSurvey(1);

        verify(surveyRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetSurveyQuestionById() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        when(surveyQuestionRepository.findById(anyLong())).thenReturn(Optional.of(surveyQuestion));

        SurveyQuestion result = surveyService.getSurveyQuestionById(1);

        assertEquals(surveyQuestion, result);
        verify(surveyQuestionRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetAllSurveys() {
        surveyService.getAllSurveys();

        verify(surveyRepository, times(1)).findAll();
    }

    @Test
    void testGetSurveyById() {
        Survey survey = new Survey();
        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

        Survey result = surveyService.getSurveyById(1);

        assertEquals(survey, result);
        verify(surveyRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetSurveysByUserId() {
        surveyService.getSurveysByUserId(1);

        verify(surveyRepository, times(1)).findSurveysByUserId(anyLong());
    }

    @Test
    void testGetSurveysByManagerId() {
        surveyService.getSurveysByManagerId(1);

        verify(surveyRepository, times(1)).findSurveysByManagerId(anyInt());
    }

    @Test
    void testGetSurveysResultsByManagerId() {
        surveyService.getSurveysResultsByManagerId(1);

        verify(surveyRepository, times(1)).findSurveysResultsByManagerId(anyInt());
    }

    @Test
    void testCreateSurveyQuestionAndCategory() {
        SurveyQuestionAndCategorieRequest request = new SurveyQuestionAndCategorieRequest();
        request.setSurveyId(1);
        request.setCategoryName("TestCategory");
        request.setScoreImpact(5);
        request.setQuestion("TestQuestion");
        request.setText("TestText");
        request.setTemplateId("1");

        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(new Survey()));
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());
        when(surveyQuestionRepository.save(any(SurveyQuestion.class))).thenReturn(new SurveyQuestion());

        surveyService.createSurveyQuestionAndCategory(request);

        verify(surveyRepository, times(1)).findById(anyLong());
        verify(categoryRepository, times(1)).save(any(Category.class));
        verify(surveyQuestionRepository, times(1)).save(any(SurveyQuestion.class));
    }

    @Test
    void testEditSurveyQuestionAndCategory() {
        SurveyQuestionAndCategorieRequest request = new SurveyQuestionAndCategorieRequest();
        request.setSurveyId(1);
        request.setCategoryName("TestCategory");
        request.setScoreImpact(5);
        request.setQuestion("TestQuestion");
        request.setText("TestText");
        request.setTemplateId("1");

        when(surveyQuestionRepository.findById(anyLong())).thenReturn(Optional.of(new SurveyQuestion()));
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());
        when(surveyQuestionRepository.save(any(SurveyQuestion.class))).thenReturn(new SurveyQuestion());

        surveyService.editSurveyQuestionAndCategory(1, request);

        verify(surveyQuestionRepository, times(1)).findById(anyLong());
        verify(categoryRepository, times(1)).save(any(Category.class));
        verify(surveyQuestionRepository, times(1)).save(any(SurveyQuestion.class));
    }

    @Test
    void testCreateSurveyQuestion() {
        SurveyQuestionRequest request = new SurveyQuestionRequest();
        request.setSurveyId(1);
        request.setQuestion("TestQuestion");
        request.setText("TestText");
        request.setTemplateId("1");

        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(new Survey()));
        when(surveyQuestionRepository.save(any(SurveyQuestion.class))).thenReturn(new SurveyQuestion());

        surveyService.createSurveyQuestion(request);

        verify(surveyRepository, times(1)).findById(anyLong());
        verify(surveyQuestionRepository, times(1)).save(any(SurveyQuestion.class));
    }

    @Test
    void testEditSurveyQuestion() {
        SurveyQuestionRequest request = new SurveyQuestionRequest();
        request.setSurveyId(1);
        request.setQuestion("TestQuestion");
        request.setText("TestText");
        request.setTemplateId("1");

        when(surveyQuestionRepository.findById(anyLong())).thenReturn(Optional.of(new SurveyQuestion()));
        when(surveyQuestionRepository.save(any(SurveyQuestion.class))).thenReturn(new SurveyQuestion());

        surveyService.editSurveyQuestion(1, request);

        verify(surveyQuestionRepository, times(1)).findById(anyLong());
        verify(surveyQuestionRepository, times(1)).save(any(SurveyQuestion.class));
    }

    @Test
    void testDeleteSurveyQuestion() {
        surveyService.deleteSurveyQuestion(1);

        verify(surveyQuestionRepository, times(1)).deleteById(anyLong());
    }

}
