package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.SurveyService;
import RealDolmen.HappyR.model.Survey;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Survey")
public class SurveyController {
    @GetMapping("/all")
    public List<Survey> getAllSurveys(SurveyService surveyService) {
        return surveyService.getSurveyList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getResultById(@PathVariable("id") int surveyId, SurveyService surveyService) {
        Optional<Survey> survey = surveyService.getOptionalSurveyById(surveyId);
        return survey.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Survey addSurvey(@RequestBody Survey newSurvey, SurveyService surveyService){
        return surveyService.addSurvey(newSurvey);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Survey> updateSurvey(@RequestBody Survey updateSurvey, @PathVariable("id") int surveyId, SurveyService surveyService){
        Survey survey = surveyService.updateSurveyById(updateSurvey, surveyId);
        if (survey==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteSurvey(@PathVariable("id") int surveyId, SurveyService surveyService){
        Optional<Survey> survey = surveyService.getOptionalSurveyById(surveyId);
        if (survey.isPresent()){
            surveyService.getSurveyList().remove(survey.get());
            return new ResponseEntity<>(surveyService.getSurveyList().size(), HttpStatus.OK);
        }
        return new ResponseEntity<>(surveyService.getSurveyList().size(), HttpStatus.NOT_FOUND);
    }
}
