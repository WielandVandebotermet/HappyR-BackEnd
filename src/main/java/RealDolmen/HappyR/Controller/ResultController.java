package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.ResultService;
import RealDolmen.HappyR.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Result")
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

    @DeleteMapping("/Delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteKoers
            (@PathVariable("id") String id) {
        resultService.deleteResult(Integer.parseInt(id));
    }

    @PutMapping("/Edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditKoers
            (@PathVariable("id") String id, @RequestBody Result result) {
        resultService.editResult(Integer.parseInt(id), result);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createKoers
            (@RequestBody Result result) {
        resultService.createResult(result);
    }
}
