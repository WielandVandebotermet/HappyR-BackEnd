package RealDolmen.HappyR;

import RealDolmen.HappyR.Data.ResultRequest;
import RealDolmen.HappyR.Repository.ResultRepository;
import RealDolmen.HappyR.Repository.SurveyRepository;
import RealDolmen.HappyR.Service.ResultService;
import RealDolmen.HappyR.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

// Import necessary packages and classes

// Annotation to enable Mockito extensions for JUnit 5
@ExtendWith(MockitoExtension.class)
public class ResultTest {

    // Mocking the ResultRepository
    @Mock
    private ResultRepository resultRepository;

    // Mocking the SurveyRepository
    @Mock
    private SurveyRepository surveyRepository;

    // Injecting mocked ResultService into test class
    @InjectMocks
    private ResultService resultService;

    // Test case for creating a result when survey is not found
    @Test
    void testCreateResult_SurveyNotFound() {
        // Creating test ResultRequest
        ResultRequest resultRequest = new ResultRequest();
        resultRequest.setSurveyId(1);

        // Configuring behavior of surveyRepository mock
        when(surveyRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // Invoking the method under test
        resultService.createResult(resultRequest);

        // Verifying that save method of resultRepository is never called
        verify(resultRepository, never()).save(any(Result.class));
    }

    // Test case for creating a result when survey is not started
    @Test
    void testCreateResult_SurveyNotStarted() {
        // Creating test Survey and ResultRequest
        Survey survey = new Survey();
        ResultRequest resultRequest = new ResultRequest();
        resultRequest.setSurveyId(1);

        // Configuring behavior of surveyRepository mock
        when(surveyRepository.findById(any(Long.class))).thenReturn(Optional.of(survey));

        // Invoking the method under test
        resultService.createResult(resultRequest);

        // Verifying that save method of resultRepository is never called
        verify(resultRepository, never()).save(any(Result.class));
    }

    // Test case for creating a result when existing result already exists
    @Test
    void testCreateResult_ExistingResult() {
        // Creating test Survey, ResultRequest, and existing Result
        Survey survey = new Survey();
        ResultRequest resultRequest = new ResultRequest();
        resultRequest.setSurveyId(1);
        resultRequest.setUserId(1);
        Result existingResult = new Result();

        // Configuring behavior of surveyRepository and resultRepository mocks
        when(surveyRepository.findById(any(Long.class))).thenReturn(Optional.of(survey));
        when(resultRepository.findResultByUserIdAndSurveyId(anyInt(), anyInt())).thenReturn(existingResult);

        // Invoking the method under test
        resultService.createResult(resultRequest);

        // Verifying that save method of resultRepository is never called
        verify(resultRepository, never()).save(any(Result.class));
    }

    // Test case for creating a result successfully
    @Test
    void testCreateResult_Success() {
        // Creating test Survey, ResultRequest, and configuring behavior of mocks
        Survey survey = new Survey();
        survey.setStarted(true);
        ResultRequest resultRequest = new ResultRequest();
        resultRequest.setSurveyId(1);
        resultRequest.setUserId(1);
        resultRequest.setScoreList(Collections.emptyList());

        when(surveyRepository.findById(any(Long.class))).thenReturn(Optional.of(survey));
        when(resultRepository.findResultByUserIdAndSurveyId(anyInt(), anyInt())).thenReturn(null);

        // Invoking the method under test
        resultService.createResult(resultRequest);

        // Verifying that save method of resultRepository is called once
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    // Test case for editing a result successfully
    @Test
    void testEditResult_Success() {
        // Creating test existing Result and ResultRequest
        Result existingResult = new Result();
        ResultRequest resultRequest = new ResultRequest();
        resultRequest.setGroupId(1);
        resultRequest.setUserId(1);
        resultRequest.setSurveyId(1);
        resultRequest.setTotalResult(100);

        // Configuring behavior of resultRepository mock
        when(resultRepository.findById(anyLong())).thenReturn(Optional.of(existingResult));

        // Invoking the method under test
        resultService.editResult(1, existingResult);

        // Verifying that result's properties are updated and save method of resultRepository is called once
        assertEquals(1, existingResult.getGroupId());
        assertEquals(1, existingResult.getUserId());
        assertEquals(1, existingResult.getSurvey().getId());
        assertEquals(100, existingResult.getTotalResult());
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    // Test case for deleting a result successfully
    @Test
    void testDeleteResult_Success() {
        // Invoking the method under test
        resultService.deleteResult(1);

        // Verifying that deleteById method of resultRepository is called once with anyLong argument
        verify(resultRepository, times(1)).deleteById(anyLong());
    }
}