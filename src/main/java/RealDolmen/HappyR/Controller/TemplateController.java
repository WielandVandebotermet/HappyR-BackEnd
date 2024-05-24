package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.TemplateService;
import RealDolmen.HappyR.model.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Controller for managing templates
@RestController
@RequestMapping("/template")
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService; // Service for handling template-related operations

    // Endpoint to retrieve all templates
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Template> getAllTemplates() {
        return templateService.getAllTemplates();
    }

    // Endpoint to retrieve a template by its ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Template getTemplateById(@PathVariable("id") String id) {
        return templateService.getTemplateById(Integer.parseInt(id));
    }

    // Endpoint to delete a template by its ID
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTemplate(@PathVariable("id") String id) {
        templateService.deleteTemplate(Integer.parseInt(id));
    }

    // Endpoint to edit a template by its ID
    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditTemplate(@PathVariable("id") String id, @RequestBody Template template) {
        templateService.editTemplate(Integer.parseInt(id), template);
    }

    // Endpoint to create a new template
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createTemplate(@RequestBody Template template) {
        templateService.createTemplate(template);
    }
}
