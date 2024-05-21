package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Data.SurveyQuestionAndCategorieRequest;
import RealDolmen.HappyR.Data.SurveyQuestionRequest;
import RealDolmen.HappyR.Data.SurveyRequest;
import RealDolmen.HappyR.Service.SurveyService;
import RealDolmen.HappyR.model.Survey;
import RealDolmen.HappyR.model.SurveyQuestion;
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

    @GetMapping("/FilterByManagerId/{ManagerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Survey> FilterByManagerId(@PathVariable("ManagerId") String id) {
        return surveyService.getSurveysByManagerId(Integer.parseInt(id));
    }

    @GetMapping("/FilterResultsByManagerId/{ManagerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Survey> FilterResultsByManagerId(@PathVariable("ManagerId") String id) {
        return surveyService.getSurveysResultsByManagerId(Integer.parseInt(id));
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


    @GetMapping("question/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SurveyQuestion GetSurveyQuestion
            (@PathVariable("id") String id) {
        return surveyService.getSurveyQuestionById(Integer.parseInt(id));
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
            (@PathVariable("id") String id, @RequestBody SurveyQuestionRequest surveyQuestionRequest) {
        surveyService.editSurveyQuestion(Integer.parseInt(id), surveyQuestionRequest);
    }

    @PostMapping("question/create")
    @ResponseStatus(HttpStatus.OK)
    public void createSurveyQuestion (@RequestBody SurveyQuestionRequest surveyQuestionRequest) {
        surveyService.createSurveyQuestion(surveyQuestionRequest);
    }

    @PostMapping("question/create/categorie")
    @ResponseStatus(HttpStatus.OK)
    public void createSurveyQuestionAndCategory (@RequestBody SurveyQuestionAndCategorieRequest surveyQuestionAndCategorieRequest) {
        surveyService.createSurveyQuestionAndCategory(surveyQuestionAndCategorieRequest);
    }

    @PutMapping("question/edit/categorie/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditSurveyQuestionAndCategory
            (@PathVariable("id") String id, @RequestBody SurveyQuestionAndCategorieRequest surveyQuestionAndCategorieRequest) {
        surveyService.editSurveyQuestionAndCategory(Integer.parseInt(id), surveyQuestionAndCategorieRequest);
    }
}
