package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Data.ResultRequest;
import RealDolmen.HappyR.Repository.SurveyRepository;
import RealDolmen.HappyR.model.*;
import RealDolmen.HappyR.Repository.ResultRepository;
import RealDolmen.HappyR.model.Result;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final SurveyRepository surveyRepository;

    public void createResult(ResultRequest resultRequest){
        Survey survey = surveyRepository.findById((long) resultRequest.getSurveyId()).orElse(null);

        if(survey != null && survey.getStarted()) {
            Result existingResult  = resultRepository.findResultByUserIdAndSurveyId(resultRequest.getUserId(), resultRequest.getSurveyId());
            if(existingResult  == null) {
                if (survey.getQuestions().size() == resultRequest.getScoreList().size()) {
                    Result result = Result.builder()
                            .userId(resultRequest.getUserId())
                            .groupId(resultRequest.getGroupId())
                            .survey(survey)
                            .totalResult(resultRequest.getTotalResult())
                            .build();

                    List<ResultScoreList> resultScoreLists = new ArrayList<>();

                    for (ResultRequest.resultList resultList : resultRequest.getScoreList()) {

                        ResultScoreList resultScoreList = new ResultScoreList();
                        resultScoreList.setResult(result);
                        resultScoreList.setQuestionId(resultList.getQuestionId());
                        resultScoreList.setScore(resultList.getScore());
                        resultScoreList.setCategoryId(resultList.getCategoryId());

                        resultScoreLists.add(resultScoreList);
                    }


                    result.setScoreList(resultScoreLists);
                    resultRepository.save(result);
                }
            }
        }
    }

    public void editResult(int id, Result resultRequest){
        Result result = resultRepository.findById((long) id).orElse(null);

        if(result != null)
        {
            result.setId(result.getId());
            result.setGroupId(resultRequest.getGroupId());
            result.setUserId(resultRequest.getUserId());
            result.setTotalResult(resultRequest.getTotalResult());
            result.setScoreList(resultRequest.getScoreList());

            resultRepository.save(result);
        }
    }
    public void deleteResult(int id){
        resultRepository.deleteById((long) id);
    }

    public List<Result> getAllResults() {
        List<Result> results = resultRepository.findAll();

        return results.stream().map(this::mapToResultResponse).toList();
    }

    public Result getResultById(int id){
        return resultRepository.findById((long) id).orElse(null);
    }

    public List<Result> getResultsBySurveyId(int id){
        List<Result> results = resultRepository.findResultsBySurveyId(id);
        return results.stream().map(this::mapToResultResponse).toList();
    }

    public List<Result> getResultByManagerId(int surveyId, int userId) {
        return resultRepository.findDistinctBySurveyAndUser((long) surveyId, (long) userId);
    }

    private Result mapToResultResponse(Result result) {
        return Result.builder()
                .id(result.getId())
                .userId(result.getUserId())
                .survey(result.getSurvey())
                .totalResult(result.getTotalResult())
                .scoreList(result.getScoreList())
                .groupId(result.getGroupId())
                .build();
    }
}
