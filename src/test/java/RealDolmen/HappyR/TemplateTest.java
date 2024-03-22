package RealDolmen.HappyR;

import RealDolmen.HappyR.Repository.TemplateRepository;
import RealDolmen.HappyR.Service.TemplateService;
import RealDolmen.HappyR.model.Template;
import RealDolmen.HappyR.model.TemplateOption;
import RealDolmen.HappyR.model.TemplateQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TemplateTest {
    @InjectMocks
    private TemplateService templateService;

    @Mock
    private TemplateRepository templateRepository;

    @Test
    void testGetAllTemplate() {
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
        template2.setOptions(options2);

        template2.setQuestions(null);

        template2.setExternalPeople(null);
        templateRepository.save(template2);

        List<Template> templateList = new ArrayList<>();
        templateList.add(template);
        templateList.add(template1);
        templateList.add(template2);

        when(templateRepository.findAll()).thenReturn(templateList);
        List<Template> templates = templateService.getAllTemplates();


        assertEquals(3, templates.size());

        assertEquals("Question Bar", templates.get(0).getTemplateName());
        assertEquals(6, templates.get(0).getQuestions().size());
        assertEquals(2, templates.get(0).getOptions().size());
        assertNull(templates.get(0).getExternalPeople());

        assertEquals("Team Question Bar", templates.get(1).getTemplateName());
        assertEquals(6, templates.get(1).getQuestions().size());
        assertEquals(3, templates.get(1).getOptions().size());
        assertNull(templates.get(1).getExternalPeople());


        assertEquals("Question Comment", templates.get(2).getTemplateName());
        assertNull(templates.get(2).getQuestions());
        assertEquals(1, templates.get(2).getOptions().size());
        assertNull(templates.get(2).getExternalPeople());
    }

    @Test
    void testCreateTemplate() {
        Template template = new Template();
        template.setTemplateName("TestTemplateName");
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

        templateService.createTemplate(template);

        // Verify that save method was called with the correct arguments
        verify(templateRepository, times(1)).save(any(Template.class));
    }

    @Test
    void testEditTemplate() {
        Template template = new Template();
        template.setId(1L);
        template.setTemplateName("TestTemplateName");
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

        Template template1 = new Template();
        template1.setId(1L);
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

        when(templateRepository.findById(5L)).thenReturn(Optional.of(template));

        templateService.editTemplate(5, template1);

        verify(templateRepository, times(1)).save(template);

        assertEquals("Team Question Bar", template.getTemplateName());
        assertEquals(6, template.getQuestions().size());
        assertEquals(3, template.getOptions().size());
        assertNull(template.getExternalPeople());
    }

    @Test
    void testDeleteTemplate() {
        Template template = new Template();
        template.setId(1L);
        template.setTemplateName("TestTemplateName");
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

        templateService.deleteTemplate(1);

        verify(templateRepository, times(1)).deleteById(1L);
    }
}
