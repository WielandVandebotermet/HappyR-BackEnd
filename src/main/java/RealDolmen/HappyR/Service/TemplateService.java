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

    @PostConstruct
    public void LoadData() {
        if (templateRepository.count() <= 0) {
            Template template = new Template();
            template.setId(1L);
            template.setTemplateName("Question Bar");

            List<TemplateOption> options = new ArrayList<>();
            options.add(new TemplateOption(null,template, "subtext", true));
            options.add(new TemplateOption(null,template,"comment", false));
            template.setOptions(options);

            // Initialize questions
            List<TemplateQuestion> questions = new ArrayList<>();
            questions.add(new TemplateQuestion(null,template,"Title", ""));
            questions.add(new TemplateQuestion(null,template,"SubText", ""));
            questions.add(new TemplateQuestion(null,template,"Bmin", "1"));
            questions.add(new TemplateQuestion(null,template,"Bmax", "5"));
            questions.add(new TemplateQuestion(null,template,"Step", "1"));
            questions.add(new TemplateQuestion(null,template,"CategorieId", ""));
            template.setQuestions(questions);

            template.setExternalPeople(null);
            templateRepository.save(template);


            Template template1 = new Template();
            template1.setId(2L);
            template1.setTemplateName("Team Question Bar");

            List<TemplateOption> options1 = new ArrayList<>();
            options1.add(new TemplateOption(null,template1, "subtext", true));
            options1.add(new TemplateOption(null,template1,"comment", false));
            options1.add(new TemplateOption(null,template1,"IncudeManager", false));
            template1.setOptions(options1);

            // Initialize questions
            List<TemplateQuestion> questions1 = new ArrayList<>();
            questions1.add(new TemplateQuestion(null,template1,"Title", ""));
            questions1.add(new TemplateQuestion(null,template1,"SubText", ""));
            questions1.add(new TemplateQuestion(null,template1,"Bmin", "1"));
            questions1.add(new TemplateQuestion(null,template1,"Bmax", "5"));
            questions1.add(new TemplateQuestion(null,template1,"Step", "1"));
            questions1.add(new TemplateQuestion(null,template1,"CategorieId", ""));
            template1.setQuestions(questions1);

            template1.setExternalPeople(null);
            templateRepository.save(template1);

            Template template2 = new Template();
            template2.setId(3L);
            template2.setTemplateName("Question Comment");

            List<TemplateOption> options2 = new ArrayList<>();
            options2.add(new TemplateOption(null,template2, "subtext", true));
            template1.setOptions(options2);

            template1.setQuestions(null);

            template1.setExternalPeople(null);
            templateRepository.save(template1);
        }
    }

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
