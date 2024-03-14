package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.model.Result;
import RealDolmen.HappyR.Repository.ResultRepository;
import RealDolmen.HappyR.model.Result;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;

    public void createResult(Result resultRequest){
        Result result = Result.builder()
                .UserId(resultRequest.getUserId())
                .SurveyId(resultRequest.getSurveyId())
                .TotalResult(resultRequest.getTotalResult())
                .scoreList(resultRequest.getScoreList())
                .build();

        resultRepository.save(result);
    }

    public void editResult(int id, Result resultRequest){
        Result result = resultRepository.findById((long) id).orElse(null);

        if(result != null)
        {
            result.setId(result.getId());
            result.setUserId(resultRequest.getUserId());
            result.setSurveyId(resultRequest.getSurveyId());
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

    private Result mapToResultResponse(Result result) {
        return Result.builder()
                .id(result.getId())
                .UserId(result.getUserId())
                .SurveyId(result.getSurveyId())
                .TotalResult(result.getTotalResult())
                .scoreList(result.getScoreList())
                .build();
    }
}
