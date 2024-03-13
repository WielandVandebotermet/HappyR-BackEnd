package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.model.Result;
import RealDolmen.HappyR.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResultService {
    private List<Result> resultList= new ArrayList<>();

    public ResultService() {}

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public Optional<Result> getOptionalResultById(int resultId){
        return getResultList().stream().filter(r-> r.getId()==resultId).findFirst();
    }
    public Result getResultById(Optional<Result> optionalResult){
        return optionalResult.orElse(null);
    }

    public Result addResult(Result newResult) {
        newResult.setId(resultList.size()+1);
        resultList.add(newResult);
        return resultList.get(resultList.size()-1);
    }

    public Result updateResultById(Result updateResult, int resultId) {
        Optional<Result> userOptional = getOptionalResultById(resultId);
        if (userOptional.isPresent()){
            Result result = userOptional.get();
            result.setSurveyId(updateResult.getSurveyId());
            result.setUserId(updateResult.getUserId());
            result.setTotalResult(updateResult.getTotalResult());
            result.setScoreList(updateResult.getScoreList());
            return result;
        }
        return null;
    }
}
