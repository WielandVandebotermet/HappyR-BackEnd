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
// Import statements for required classes and libraries

// Annotations to indicate the use of Mockito for testing
@ExtendWith(MockitoExtension.class)
public class TemplateTest {

    // Injecting service under test and mocking repository
    @InjectMocks
    private TemplateService templateService;

    @Mock
    private TemplateRepository templateRepository;

    // Testing method for retrieving a template by ID (successful case)
    @Test
    void testGetTemplateById_Success() {
        // Creating a sample template
        Template template = new Template();
        template.setId(1L);
        template.setTemplateName("Test Template");

        // Mocking behavior of template repository
        when(templateRepository.findById(anyLong())).thenReturn(Optional.of(template));

        // Invoking the method under test
        Template retrievedTemplate = templateService.getTemplateById(1);

        // Verifying that the retrieved template matches the expected one
        assertEquals(template.getId(), retrievedTemplate.getId());
        assertEquals(template.getTemplateName(), retrievedTemplate.getTemplateName());
    }

    // Testing method for retrieving all templates (successful case)
    @Test
    void testGetAllTemplates_Success() {
        // Creating sample templates
        Template template1 = new Template();
        template1.setId(1L);
        template1.setTemplateName("Template 1");

        Template template2 = new Template();
        template2.setId(2L);
        template2.setTemplateName("Template 2");

        List<Template> templateList = new ArrayList<>();
        templateList.add(template1);
        templateList.add(template2);

        // Mocking behavior of template repository
        when(templateRepository.findAll()).thenReturn(templateList);

        // Invoking the method under test
        List<Template> retrievedTemplates = templateService.getAllTemplates();

        // Verifying that the retrieved list of templates matches the expected one
        assertEquals(templateList.size(), retrievedTemplates.size());
        for (int i = 0; i < templateList.size(); i++) {
            assertEquals(templateList.get(i).getId(), retrievedTemplates.get(i).getId());
            assertEquals(templateList.get(i).getTemplateName(), retrievedTemplates.get(i).getTemplateName());
        }
    }

    // Testing method for creating a template (successful case)
    @Test
    void testCreateTemplate_Success() {
        // Creating a sample template request
        Template templateRequest = new Template();
        templateRequest.setTemplateName("New Template");

        // Mocking behavior of template repository
        when(templateRepository.save(any(Template.class))).thenReturn(templateRequest);

        // Invoking the method under test
        templateService.createTemplate(templateRequest);

        // Verifying that the save method is called on the template repository
        verify(templateRepository, times(1)).save(any(Template.class));
    }

    // Testing method for editing a template (successful case)
    @Test
    void testEditTemplate_Success() {
        // Creating a sample existing template and template request
        Template existingTemplate = new Template();
        existingTemplate.setId(1L);
        existingTemplate.setTemplateName("Existing Template");

        Template templateRequest = new Template();
        templateRequest.setTemplateName("Edited Template");

        // Mocking behavior of template repository
        when(templateRepository.findById(anyLong())).thenReturn(Optional.of(existingTemplate));

        // Invoking the method under test
        templateService.editTemplate(1, templateRequest);

        // Verifying that the save method is called on the template repository and the template name is updated correctly
        verify(templateRepository, times(1)).save(existingTemplate);
        assertEquals(templateRequest.getTemplateName(), existingTemplate.getTemplateName());
    }

    // Testing method for deleting a template (successful case)
    @Test
    void testDeleteTemplate_Success() {
        // Invoking the method under test
        templateService.deleteTemplate(1);

        // Verifying that the deleteById method is called on the template repository
        verify(templateRepository, times(1)).deleteById(1L);
    }
}
