package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Repository.TemplateRepository;
import RealDolmen.HappyR.model.Template;
import RealDolmen.HappyR.model.TemplateOption;
import RealDolmen.HappyR.model.TemplateQuestion;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;

    public void createTemplate(Template templateRequest){
        Template template = Template.builder()
                .templateName(templateRequest.getTemplateName())
                .options(templateRequest.getOptions())
                .questions(templateRequest.getQuestions())
                .externalPeople(templateRequest.getExternalPeople())
                .build();

        templateRepository.save(template);
    }

    public void editTemplate(int id, Template templateRequest){
        Template template = templateRepository.findById((long) id).orElse(null);

        if(template != null)
        {
            template.setId(template.getId());
            template.setTemplateName(templateRequest.getTemplateName());
            template.setOptions(templateRequest.getOptions());
            template.setQuestions(templateRequest.getQuestions());
            template.setExternalPeople(templateRequest.getExternalPeople());

            templateRepository.save(template);
        }
    }
    public void deleteTemplate(int id){
        templateRepository.deleteById((long) id);
    }

    public List<Template> getAllTemplates() {
        List<Template> templates = templateRepository.findAll();

        return templates.stream().map(this::mapToTemplateResponse).toList();
    }

    public Template getTemplateById(int id){
        return templateRepository.findById((long) id).orElse(null);
    }

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
