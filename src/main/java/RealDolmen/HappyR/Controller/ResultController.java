package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Data.ResultRequest;
import RealDolmen.HappyR.Service.ResultService;
import RealDolmen.HappyR.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result getResultById(@PathVariable("id") String id) {
        return resultService.getResultById(Integer.parseInt(id));
    }

    @GetMapping("manager/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Result> getResultsByManager(@PathVariable("id") String id) {
        return resultService.getResultByManagerId(Integer.parseInt(id));
    }

    @GetMapping("survey/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result getResultBySurvey(@PathVariable("id") String id) {
        return resultService.getResultBySurveyId(Integer.parseInt(id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteResult
            (@PathVariable("id") String id) {
        resultService.deleteResult(Integer.parseInt(id));
    }

    @PostMapping("/create/")
    @ResponseStatus(HttpStatus.OK)
    public void createResult
            (@RequestBody ResultRequest resultRequest) {
        resultService.createResult(resultRequest);
    }
}
