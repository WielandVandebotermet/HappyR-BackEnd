package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.SurveyService;
import RealDolmen.HappyR.model.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Survey")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Survey getSurveyById(@PathVariable("id") String id) {
        return surveyService.getSurveyById(Integer.parseInt(id));
    }

    @DeleteMapping("/Delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteKoers
            (@PathVariable("id") String id) {
        surveyService.deleteSurvey(Integer.parseInt(id));
    }

    @PutMapping("/Edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditKoers
            (@PathVariable("id") String id, @RequestBody Survey survey) {
        surveyService.editSurvey(Integer.parseInt(id), survey);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createKoers
            (@RequestBody Survey survey) {
        surveyService.createSurvey(survey);
    }
}
