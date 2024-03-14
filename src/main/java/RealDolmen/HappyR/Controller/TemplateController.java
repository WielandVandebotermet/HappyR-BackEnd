package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.TemplateService;
import RealDolmen.HappyR.model.Template;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Template")
public class TemplateController {
    @GetMapping("/all")
    public List<Template> getAllTemplates(TemplateService templateService) {
        return templateService.getTemplateList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Template> getResultById(@PathVariable("id") int templateId, TemplateService templateService) {
        Optional<Template> template = templateService.getOptionalTemplateById(templateId);
        return template.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Template addTemplate(@RequestBody Template newTemplate, TemplateService templateService){
        return templateService.addTemplate(newTemplate);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Template> updateTemplate(@RequestBody Template updateTemplate, @PathVariable("id") int templateId, TemplateService templateService){
        Template template = templateService.updateTemplateById(updateTemplate, templateId);
        if (template==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(template, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteTemplate(@PathVariable("id") int templateId, TemplateService templateService){
        Optional<Template> template = templateService.getOptionalTemplateById(templateId);
        if (template.isPresent()){
            templateService.getTemplateList().remove(template.get());
            return new ResponseEntity<>(templateService.getTemplateList().size(), HttpStatus.OK);
        }
        return new ResponseEntity<>(templateService.getTemplateList().size(), HttpStatus.NOT_FOUND);
    }
}
