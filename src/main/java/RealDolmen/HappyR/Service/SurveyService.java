package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.model.Survey;
import RealDolmen.HappyR.model.Question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SurveyService {
    private List<Survey> surveyList= new ArrayList<>();


    public SurveyService(QuestionService questionService) {
        Optional<List<Question>> optionalQuestions = questionService.getOptionalQuestionsBySurveyId(1);
        List<Question> questions = optionalQuestions.orElse(new ArrayList<>()); // Provide a default empty list if optional is empty
        surveyList.add(new Survey(1, new int[]{1}, "Weekly Satisfaction Survey", new Date(), true, new ArrayList<>(questions)));
    }

    public List<Survey> getSurveyList() {
        return surveyList;
    }

    public void setSurveyList(List<Survey> surveyList) {
        this.surveyList = surveyList;
    }

    public Optional<Survey> getOptionalSurveyById(int surveyId){
        return getSurveyList().stream().filter(u-> u.getId()==surveyId).findFirst();
    }
    public Survey getSurveyById(Optional<Survey> optionalSurvey){
        return optionalSurvey.orElse(null);
    }

    public Survey addSurvey(Survey newSurvey) {
        newSurvey.setId(surveyList.size()+1);
        surveyList.add(newSurvey);
        return surveyList.get(surveyList.size()-1);
    }

    public Survey updateSurveyById(Survey updateSurvey, int surveyId) {
        Optional<Survey> surveyOptional = getOptionalSurveyById(surveyId);
        if (surveyOptional.isPresent()){
            Survey survey = surveyOptional.get();
            survey.setGroupList(updateSurvey.getGroupList());
            survey.setStartDate(updateSurvey.getStartDate());
            survey.setStarted(updateSurvey.getStarted());
            survey.setTestName(updateSurvey.getTestName());
            survey.setQuestions(updateSurvey.getQuestions());
            return survey;
        }
        return null;
    }
}
