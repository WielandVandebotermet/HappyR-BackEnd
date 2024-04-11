package RealDolmen.HappyR;

import RealDolmen.HappyR.Data.SurveyRequest;
import RealDolmen.HappyR.Repository.SurveyRepository;
import RealDolmen.HappyR.Service.SurveyService;
import RealDolmen.HappyR.model.Survey;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class SurveyTest {
    @InjectMocks
    private SurveyService surveyService;

    @Mock
    private SurveyRepository surveyRepository;

    @Test
    void testGetAllSurvey() {
        Survey survey = new Survey();
        survey.setId(1L);
        survey.setTestName("Satisfaction Survey");
        survey.setStartDate(Calendar.getInstance());
        survey.setStarted(true);
        survey.setQuestions(null);
        survey.setSurveyReoccuring(null);

        List<Long> SurveyList = new ArrayList<>();
        SurveyList.add(1L);
        survey.setGroupList(SurveyList);

        surveyRepository.save(survey);

        Survey survey1 = new Survey();
        survey1.setId(1L);
        survey1.setTestName("Workplace Survey");
        survey1.setStartDate(Calendar.getInstance());
        survey1.setStarted(true);
        survey1.setQuestions(null);
        survey1.setSurveyReoccuring(null);

        List<Long> SurveyList1 = new ArrayList<>();
        SurveyList1.add(1L);
        survey1.setGroupList(SurveyList1);

        surveyRepository.save(survey);

        List<Survey> surveyList = new ArrayList<>();
        surveyList.add(survey);
        surveyList.add(survey1);

        when(surveyRepository.findAll()).thenReturn(surveyList);
        List<Survey> surveys = surveyService.getAllSurveys();


        assertEquals(2, surveys.size());

        assertEquals("Satisfaction Survey", surveys.get(0).getTestName());

        assertEquals("Workplace Survey", surveys.get(1).getTestName());
    }

    @Test
    void testCreateSurvey() {
        SurveyRequest surveyRequest = new SurveyRequest();
        surveyRequest.setTestName("TestName");

        surveyService.createSurvey(surveyRequest);

        // Verify that save method was called with the correct arguments
        verify(surveyRepository, times(1)).save(any(Survey.class));
    }

    @Test
    void testEditSurvey() {
        Survey survey = new Survey();
        survey.setId(5L);
        survey.setTestName("TestName");

        SurveyRequest survey1 = new SurveyRequest();
        survey1.setTestName("EditName");

        when(surveyRepository.findById(5L)).thenReturn(Optional.of(survey));

        surveyService.editSurvey(5, survey1);

        verify(surveyRepository, times(1)).save(survey);

        assertEquals(5L, survey.getId());
        assertEquals("EditName", survey.getTestName());
    }

    @Test
    void testDeleteSurvey() {
        Survey survey = new Survey();
        survey.setId(1L);
        survey.setTestName("Wieland");

        surveyService.deleteSurvey(1);

        verify(surveyRepository, times(1)).deleteById(1L);
    }
}
