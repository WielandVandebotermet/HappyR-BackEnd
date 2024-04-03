package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Data.SurveyRequest;
import RealDolmen.HappyR.Service.SurveyService;
import RealDolmen.HappyR.model.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/FilterByUserId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Survey> FilterByUserId(@PathVariable("id") String id) {
        return surveyService.getSurveysByUserId(Integer.parseInt(id));
    }

    @GetMapping("/FilterByManagerId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Survey> FilterByManagerId(@PathVariable("id") String id) {
        return surveyService.getSurveysByManagerId(Integer.parseInt(id));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Survey getSurveyById(@PathVariable("id") String id) {
        return surveyService.getSurveyById(Integer.parseInt(id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSurvey
            (@PathVariable("id") String id) {
        surveyService.deleteSurvey(Integer.parseInt(id));
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditSurvey
            (@PathVariable("id") String id, @RequestBody SurveyRequest surveyRequest) {
        surveyService.editSurvey(Integer.parseInt(id), surveyRequest);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createSurvey (@RequestBody SurveyRequest surveyRequest) {
        surveyService.createSurvey(surveyRequest);
    }



    @DeleteMapping("question/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSurveyQuestion
            (@PathVariable("id") String id) {
        surveyService.deleteSurvey(Integer.parseInt(id));
    }

    @PutMapping("question/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditSurveyQuestion
            (@PathVariable("id") String id, @RequestBody SurveyRequest surveyRequest) {
        surveyService.editSurvey(Integer.parseInt(id), surveyRequest);
    }
    @PostMapping("question/create")
    @ResponseStatus(HttpStatus.OK)
    public void createSurveyQuestion (@RequestBody SurveyRequest surveyRequest) {
        surveyService.createSurvey(surveyRequest);
    }

    @PutMapping("question/option/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditSurveyQuestionOption
            (@PathVariable("id") String id, @RequestBody SurveyRequest surveyRequest) {
        surveyService.editSurvey(Integer.parseInt(id), surveyRequest);
    }
    @PostMapping("question/option/create")
    @ResponseStatus(HttpStatus.OK)
    public void createSurveyQuestionOption (@RequestBody SurveyRequest surveyRequest) {
        surveyService.createSurvey(surveyRequest);
    }
}
