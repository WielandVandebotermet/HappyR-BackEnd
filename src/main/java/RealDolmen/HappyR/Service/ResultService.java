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
/**
 * Service class for managing result-related operations.
 */
@Service
@RequiredArgsConstructor // Generates a constructor with required arguments for the final fields
public class ResultService {
    private final ResultRepository resultRepository;
    private final SurveyRepository surveyRepository;

    /**
     * Creates a new result based on the provided result request.
     *
     * @param resultRequest The result request object.
     */
    public void createResult(ResultRequest resultRequest) {
        Survey survey = surveyRepository.findById((long) resultRequest.getSurveyId()).orElse(null);

        if (survey != null && survey.getStarted()) {
            Result existingResult = resultRepository.findResultByUserIdAndSurveyId(resultRequest.getUserId(), resultRequest.getSurveyId());
            if (existingResult == null) {
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

    /**
     * Edits an existing result.
     *
     * @param id            The ID of the result to be edited.
     * @param resultRequest The updated result object.
     */
    public void editResult(int id, Result resultRequest) {
        Result result = resultRepository.findById((long) id).orElse(null);

        if (result != null) {
            result.setId(result.getId());
            result.setGroupId(resultRequest.getGroupId());
            result.setUserId(resultRequest.getUserId());
            result.setTotalResult(resultRequest.getTotalResult());
            result.setScoreList(resultRequest.getScoreList());

            resultRepository.save(result);
        }
    }

    /**
     * Deletes a result by its ID.
     *
     * @param id The ID of the result to be deleted.
     */
    public void deleteResult(int id) {
        resultRepository.deleteById((long) id);
    }

    /**
     * Retrieves all results.
     *
     * @return A list of all results.
     */
    public List<Result> getAllResults() {
        List<Result> results = resultRepository.findAll();
        return results.stream().map(this::mapToResultResponse).toList();
    }

    /**
     * Retrieves a result by its ID.
     *
     * @param id The ID of the result.
     * @return The result object if found, otherwise null.
     */
    public Result getResultById(int id) {
        return resultRepository.findById((long) id).orElse(null);
    }

    /**
     * Retrieves results by survey ID.
     *
     * @param id The ID of the survey.
     * @return A list of results for the specified survey.
     */
    public List<Result> getResultsBySurveyId(int id) {
        List<Result> results = resultRepository.findResultsBySurveyId(id);
        return results.stream().map(this::mapToResultResponse).toList();
    }

    /**
     * Retrieves results by manager ID and survey ID.
     *
     * @param surveyId The ID of the survey.
     * @param userId   The ID of the manager.
     * @return A list of results for the specified manager and survey.
     */
    public List<Result> getResultByManagerId(int surveyId, int userId) {
        return resultRepository.findDistinctBySurveyAndUser((long) surveyId, (long) userId);
    }

    /**
     * Maps a result object to a response object.
     *
     * @param result The result object to be mapped.
     * @return The mapped result response object.
     */
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
