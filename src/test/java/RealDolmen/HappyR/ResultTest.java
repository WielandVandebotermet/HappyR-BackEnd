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

@ExtendWith(MockitoExtension.class)

public class ResultTest {
    @Mock
    private ResultRepository resultRepository;

    @Mock
    private SurveyRepository surveyRepository;

    @InjectMocks
    private ResultService resultService;

    @Test
    void testCreateResult_SurveyNotFound() {
        ResultRequest resultRequest = new ResultRequest();
        resultRequest.setSurveyId(1);

        when(surveyRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        resultService.createResult(resultRequest);

        verify(resultRepository, never()).save(any(Result.class));
    }

    @Test
    void testCreateResult_SurveyNotStarted() {
        Survey survey = new Survey();
        ResultRequest resultRequest = new ResultRequest();
        resultRequest.setSurveyId(1);

        when(surveyRepository.findById(any(Long.class))).thenReturn(Optional.of(survey));

        resultService.createResult(resultRequest);

        verify(resultRepository, never()).save(any(Result.class));
    }

    @Test
    void testCreateResult_ExistingResult() {
        Survey survey = new Survey();
        ResultRequest resultRequest = new ResultRequest();
        resultRequest.setSurveyId(1);
        resultRequest.setUserId(1);

        Result existingResult = new Result();

        when(surveyRepository.findById(any(Long.class))).thenReturn(Optional.of(survey));
        when(resultRepository.findResultByUserIdAndSurveyId(anyInt(), anyInt())).thenReturn(existingResult);

        resultService.createResult(resultRequest);

        verify(resultRepository, never()).save(any(Result.class));
    }

    @Test
    void testCreateResult_Success() {
        Survey survey = new Survey();
        survey.setStarted(true);
        ResultRequest resultRequest = new ResultRequest();
        resultRequest.setSurveyId(1);
        resultRequest.setUserId(1);
        resultRequest.setScoreList(Collections.emptyList());

        when(surveyRepository.findById(any(Long.class))).thenReturn(Optional.of(survey));
        when(resultRepository.findResultByUserIdAndSurveyId(anyInt(), anyInt())).thenReturn(null);

        resultService.createResult(resultRequest);

        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void testEditResult_Success() {
        Result existingResult = new Result();
        ResultRequest resultRequest = new ResultRequest();
        resultRequest.setGroupId(1);
        resultRequest.setUserId(1);
        resultRequest.setSurveyId(1);
        resultRequest.setTotalResult(100);

        when(resultRepository.findById(anyLong())).thenReturn(Optional.of(existingResult));

        resultService.editResult(1, existingResult);

        assertEquals(1, existingResult.getGroupId());
        assertEquals(1, existingResult.getUserId());
        assertEquals(1, existingResult.getSurvey().getId());
        assertEquals(100, existingResult.getTotalResult());
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void testDeleteResult_Success() {
        resultService.deleteResult(1);

        verify(resultRepository, times(1)).deleteById(anyLong());
    }

}
