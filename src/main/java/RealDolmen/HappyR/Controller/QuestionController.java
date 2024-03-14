package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Question")
public class QuestionController {

    @GetMapping("/all")
    public List<Question> getAllQuestions(QuestionService questionService) {
        return questionService.getQuestionList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getResultById(@PathVariable("id") int questionId, QuestionService questionService) {
        Optional<Question> question = questionService.getOptionalQuestionById(questionId);
        return question.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Question addQuestion(@RequestBody Question newQuestion, QuestionService questionService){
        return questionService.addQuestion(newQuestion);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question updateQuestion, @PathVariable("id") int questionId, QuestionService questionService){
        Question question = questionService.updateQuestionById(updateQuestion, questionId);
        if (question==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteQuestion(@PathVariable("id") int questionId, QuestionService questionService){
        Optional<Question> question = questionService.getOptionalQuestionById(questionId);
        if (question.isPresent()){
            questionService.getQuestionList().remove(question.get());
            return new ResponseEntity<>(questionService.getQuestionList().size(), HttpStatus.OK);
        }
        return new ResponseEntity<>(questionService.getQuestionList().size(), HttpStatus.NOT_FOUND);
    }
}
