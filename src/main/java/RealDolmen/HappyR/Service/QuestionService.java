package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuestionService {
    private List<Question> questionList= new ArrayList<>();
    public QuestionService() {
        questionList.add(new Question(1, 1,
                new HashMap<String, Boolean>() {{
                    put("subtext", true);
                    put("comment", false);
                }},
                new HashMap<String, String>() {{
                    put("Title", "Test");
                    put("SubText", "SubTest");
                    put("Bmin", "1");
                    put("Bmax", "5");
                    put("Step", "1");
                    put("CategorieId", "1");
                }},
                new String[]{}));
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Optional<Question> getOptionalQuestionById(int questionId){
        return getQuestionList().stream().filter(u-> u.getId()==questionId).findFirst();
    }
    public Question getQuestionById(Optional<Question> optionalQuestion){
        return optionalQuestion.orElse(null);
    }

    public Optional<List<Question>> getOptionalQuestionsBySurveyId(int surveyId) {
        List<Question> matchingQuestions = questionList.stream()
                .filter(question -> question.getSurveyid() == surveyId)
                .collect(Collectors.toList());

        return Optional.of(matchingQuestions);
    }
    public Question addQuestion(Question newQuestion) {
        newQuestion.setId(questionList.size()+1);
        questionList.add(newQuestion);
        return questionList.get(questionList.size()-1);
    }

    public Question updateQuestionById(Question updateQuestion, int questionId) {
        Optional<Question> questionOptional = getOptionalQuestionById(questionId);
        if (questionOptional.isPresent()){
            Question question = questionOptional.get();
            question.setSurveyid(updateQuestion.getId());
            question.setOptions(updateQuestion.getOptions());
            question.setQ(updateQuestion.getQ());
            question.setExternalPeople(updateQuestion.getExternalPeople());
            return question;
        }
        return null;
    }

}
