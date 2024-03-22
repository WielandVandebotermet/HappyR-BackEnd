package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.TemplateService;
import RealDolmen.HappyR.model.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/template")
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

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTemplate
            (@PathVariable("id") String id) {
        templateService.deleteTemplate(Integer.parseInt(id));
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditTemplate
            (@PathVariable("id") String id, @RequestBody Template template) {
        templateService.editTemplate(Integer.parseInt(id), template);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createTemplate
            (@RequestBody Template template) {
        templateService.createTemplate(template);
    }

}
