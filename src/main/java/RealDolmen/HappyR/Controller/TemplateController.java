package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.TemplateService;
import RealDolmen.HappyR.model.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Template")
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Template> getAllTemplates() {
        return templateService.getAllTemplates();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Template getTemplateById(@PathVariable("id") String id) {
        return templateService.getTemplateById(Integer.parseInt(id));
    }

    @DeleteMapping("/Delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteKoers
            (@PathVariable("id") String id) {
        templateService.deleteTemplate(Integer.parseInt(id));
    }

    @PutMapping("/Edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditKoers
            (@PathVariable("id") String id, @RequestBody Template template) {
        templateService.editTemplate(Integer.parseInt(id), template);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createKoers
            (@RequestBody Template template) {
        templateService.createTemplate(template);
    }

}
