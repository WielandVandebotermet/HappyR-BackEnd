package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Auth0.AuthController;
import RealDolmen.HappyR.Data.ResultRequest;
import RealDolmen.HappyR.Service.ResultService;
import RealDolmen.HappyR.model.Result;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Controller for managing survey results
@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService; // Service for handling result-related operations

    private final Logger logger = LoggerFactory.getLogger(ResultController.class); // Logger for logging result-related actions

    // Endpoint to retrieve all survey results
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    // Endpoint to retrieve a survey result by its ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result getResultById(@PathVariable("id") String id) {
        return resultService.getResultById(Integer.parseInt(id));
    }

    // Endpoint to retrieve results by survey manager and user ID
    @GetMapping("/manager")
    @ResponseStatus(HttpStatus.OK)
    public List<Result> getResultsByManager(@RequestParam("surveyId") int surveyId, @RequestParam("userId") int userId) {
        return resultService.getResultByManagerId(surveyId, userId);
    }

    // Endpoint to retrieve results by survey ID
    @GetMapping("/survey/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Result> getResultsBySurvey(@PathVariable("id") String id) {
        return resultService.getResultsBySurveyId(Integer.parseInt(id));
    }

    // Endpoint to delete a survey result by its ID
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteResult(@PathVariable("id") String id) {
        resultService.deleteResult(Integer.parseInt(id));
    }

    // Endpoint to create a new survey result
    @PostMapping("/create/")
    @ResponseStatus(HttpStatus.OK)
    public void createResult(@RequestBody ResultRequest resultRequest) {
        resultService.createResult(resultRequest);
    }
}