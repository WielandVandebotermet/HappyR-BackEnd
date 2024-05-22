package RealDolmen.HappyR;

import RealDolmen.HappyR.Repository.TemplateRepository;
import RealDolmen.HappyR.Service.TemplateService;
import RealDolmen.HappyR.model.Template;
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
    void testGetTemplateById_Success() {
        Template template = new Template();
        template.setId(1L);
        template.setTemplateName("Test Template");

        when(templateRepository.findById(anyLong())).thenReturn(Optional.of(template));

        Template retrievedTemplate = templateService.getTemplateById(1);

        assertEquals(template.getId(), retrievedTemplate.getId());
        assertEquals(template.getTemplateName(), retrievedTemplate.getTemplateName());
    }

    @Test
    void testGetAllTemplates_Success() {
        Template template1 = new Template();
        template1.setId(1L);
        template1.setTemplateName("Template 1");

        Template template2 = new Template();
        template2.setId(2L);
        template2.setTemplateName("Template 2");

        List<Template> templateList = new ArrayList<>();
        templateList.add(template1);
        templateList.add(template2);

        when(templateRepository.findAll()).thenReturn(templateList);

        List<Template> retrievedTemplates = templateService.getAllTemplates();

        assertEquals(templateList.size(), retrievedTemplates.size());
        for (int i = 0; i < templateList.size(); i++) {
            assertEquals(templateList.get(i).getId(), retrievedTemplates.get(i).getId());
            assertEquals(templateList.get(i).getTemplateName(), retrievedTemplates.get(i).getTemplateName());
        }
    }

    @Test
    void testCreateTemplate_Success() {
        Template templateRequest = new Template();
        templateRequest.setTemplateName("New Template");

        when(templateRepository.save(any(Template.class))).thenReturn(templateRequest);

        templateService.createTemplate(templateRequest);

        // Verify that templateRepository.save is called with any instance of Template
        verify(templateRepository, times(1)).save(any(Template.class));
    }

    @Test
    void testEditTemplate_Success() {
        Template existingTemplate = new Template();
        existingTemplate.setId(1L);
        existingTemplate.setTemplateName("Existing Template");

        Template templateRequest = new Template();
        templateRequest.setTemplateName("Edited Template");

        when(templateRepository.findById(anyLong())).thenReturn(Optional.of(existingTemplate));

        templateService.editTemplate(1, templateRequest);

        verify(templateRepository, times(1)).save(existingTemplate);
        assertEquals(templateRequest.getTemplateName(), existingTemplate.getTemplateName());
    }

    @Test
    void testDeleteTemplate_Success() {
        templateService.deleteTemplate(1);

        verify(templateRepository, times(1)).deleteById(1L);
    }
}
