package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.ResultService;
import RealDolmen.HappyR.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Result")
public class ResultController {
    @GetMapping("/all")
    public List<Result> getAllResults(ResultService resultService) {
        return resultService.getResultList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable("id") int resultId, ResultService resultService) {
        Optional<Result> result = resultService.getOptionalResultById(resultId);
        return result.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Result addResult(@RequestBody Result newResult, ResultService resultService){
        return resultService.addResult(newResult);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Result> updateResult(@RequestBody Result updateResult, @PathVariable("id") int resultId, ResultService resultService){
        Result result = resultService.updateResultById(updateResult, resultId);
        if (result==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteResult(@PathVariable("id") int resultId, ResultService resultService){
        Optional<Result> result = resultService.getOptionalResultById(resultId);
        if (result.isPresent()){
            resultService.getResultList().remove(result.get());
            return new ResponseEntity<>(resultService.getResultList().size(), HttpStatus.OK);
        }
        return new ResponseEntity<>(resultService.getResultList().size(), HttpStatus.NOT_FOUND);
    }
}
