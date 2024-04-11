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
    @InjectMocks
    private ResultService resultService;

    @Mock
    private ResultRepository resultRepository;
    @Mock
    private SurveyRepository surveyRepository;

    @Test
    void testGetAllResult() {
        Result result = new Result();
        result.setId(1L);
        result.setSurveyId(1);
        result.setUserId(1);
        result.setTotalResult(4);
        result.setScoreList(null);
        resultRepository.save(result);

        Result result1 = new Result();
        result1.setId(2L);
        result1.setSurveyId(1);
        result1.setUserId(2);
        result1.setTotalResult(3);
        result1.setScoreList(null);
        resultRepository.save(result1);


        List<Result> resultList = new ArrayList<>();
        resultList.add(result);
        resultList.add(result1);


        when(resultRepository.findAll()).thenReturn(resultList);
        List<Result> results = resultService.getAllResults();


        assertEquals(2, results.size());

        assertEquals(4, results.get(0).getTotalResult());
        assertEquals(1, results.get(0).getSurveyId());
        assertEquals(1, results.get(0).getUserId());
        assertNull(results.get(0).getScoreList());

        assertEquals(3, results.get(1).getTotalResult());
        assertEquals(1, results.get(1).getSurveyId());
        assertEquals(2, results.get(1).getUserId());
        assertNull(results.get(1).getScoreList());

    }

    @Test
    void testCreateResult() {

        Survey survey = new Survey();
        survey.setId(5L);
        survey.setTestName("Inactive Happiness Test");
        survey.setStartDate(Calendar.getInstance());
        survey.setStarted(false);

        List<SurveyQuestion> question = new ArrayList<>();

        SurveyQuestion question1 = new SurveyQuestion();
        question1.setSurvey(survey);
        question1.setTemplateId("1");
        question1.setQuestion("How do you currently like your work environment?");
        question1.setText("A 1 means not at all and a 5 means very much.");

        List<SurveyQuestionOption> options1 = new ArrayList<>();
        options1.add(new SurveyQuestionOption(null,question1, "subtext", true));
        options1.add(new SurveyQuestionOption(null,question1,"comment", false));
        options1.add(new SurveyQuestionOption(null,question1,"IncludeManager", false));
        question1.setOptions(options1);

        List<SurveyQuestionSetting> setting1 = new ArrayList<>();
        setting1.add(new SurveyQuestionSetting(null,question1,"Bmin", "1"));
        setting1.add(new SurveyQuestionSetting(null,question1,"Bmax", "5"));
        setting1.add(new SurveyQuestionSetting(null,question1,"Step", "1"));
        setting1.add(new SurveyQuestionSetting(null,question1,"CategorieId", "2"));
        question1.setSettings(setting1);

        question.add(question1);
        survey.setQuestions(question);

        ResultRequest.resultList resultList = new ResultRequest.resultList();
        resultList.setCategoryId(2);
        resultList.setScore(5);
        resultList.setQuestionId(0);

        ResultRequest result = new ResultRequest();
        result.setSurveyId(5);
        result.setUserId(1);
        result.setTotalResult(4);

        List<ResultRequest.resultList> scoreList = new ArrayList<>();
        scoreList.add(resultList);

        result.setScoreList(scoreList);

        when(surveyRepository.findById(5L)).thenReturn(Optional.of(survey));

        resultService.createResult(result);

        // Verify that save method was called with the correct arguments
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void testEditResult() {
        Result result = new Result();
        result.setId(1L);
        result.setSurveyId(1);
        result.setUserId(1);
        result.setTotalResult(4);
        result.setScoreList(null);

        Result result1 = new Result();
        result1.setId(1L);
        result1.setSurveyId(1);
        result1.setUserId(2);
        result1.setTotalResult(3);
        result1.setScoreList(null);

        when(resultRepository.findById(5L)).thenReturn(Optional.of(result));

        resultService.editResult(5, result1);

        verify(resultRepository, times(1)).save(result);

        assertEquals(1L, result1.getId());
        assertEquals(3, result1.getTotalResult());
        assertEquals(1, result1.getSurveyId());
        assertEquals(2, result1.getUserId());
        assertNull(result1.getScoreList());
    }

    @Test
    void testDeleteResult() {
        Result result = new Result();
        result.setId(1L);
        result.setSurveyId(1);
        result.setUserId(1);
        result.setTotalResult(4);
        result.setScoreList(null);

        resultService.deleteResult(1);

        verify(resultRepository, times(1)).deleteById(1L);
    }
}
