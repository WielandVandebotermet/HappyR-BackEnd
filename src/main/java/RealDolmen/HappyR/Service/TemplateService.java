package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Repository.TemplateRepository;
import RealDolmen.HappyR.model.Template;
import RealDolmen.HappyR.model.TemplateOption;
import RealDolmen.HappyR.model.TemplateQuestion;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * Service class for managing operations related to templates.
 */
@Service
@RequiredArgsConstructor // Generates a constructor with required arguments for the final fields
public class TemplateService {
    private final TemplateRepository templateRepository;

    /**
     * Creates a new template.
     *
     * @param templateRequest The template object to be created.
     */
    public void createTemplate(Template templateRequest) {
        Template template = Template.builder()
                .templateName(templateRequest.getTemplateName())
                .options(templateRequest.getOptions())
                .questions(templateRequest.getQuestions())
                .externalPeople(templateRequest.getExternalPeople())
                .build();

        templateRepository.save(template);
    }

    /**
     * Edits an existing template.
     *
     * @param id               The ID of the template to be edited.
     * @param templateRequest The updated template object.
     */
    public void editTemplate(int id, Template templateRequest) {
        Template template = templateRepository.findById((long) id).orElse(null);

        if (template != null) {
            template.setId(template.getId());
            template.setTemplateName(templateRequest.getTemplateName());
            template.setOptions(templateRequest.getOptions());
            template.setQuestions(templateRequest.getQuestions());
            template.setExternalPeople(templateRequest.getExternalPeople());

            templateRepository.save(template);
        }
    }

    /**
     * Deletes a template by its ID.
     *
     * @param id The ID of the template to be deleted.
     */
    public void deleteTemplate(int id) {
        templateRepository.deleteById((long) id);
    }

    /**
     * Retrieves all templates.
     *
     * @return A list of all templates.
     */
    public List<Template> getAllTemplates() {
        List<Template> templates = templateRepository.findAll();

        return templates.stream().map(this::mapToTemplateResponse).toList();
    }

    /**
     * Retrieves a template by its ID.
     *
     * @param id The ID of the template.
     * @return The template object if found, otherwise null.
     */
    public Template getTemplateById(int id) {
        return templateRepository.findById((long) id).orElse(null);
    }

    /**
     * Maps a template object to a response object.
     *
     * @param template The template object to be mapped.
     * @return The mapped template response object.
     */
    private Template mapToTemplateResponse(Template template) {
        return Template.builder()
                .id(template.getId())
                .templateName(template.getTemplateName())
                .options(template.getOptions())
                .questions(template.getQuestions())
                .externalPeople(template.getExternalPeople())
                .build();
    }
}
