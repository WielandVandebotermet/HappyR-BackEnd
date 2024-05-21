package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.Repository.*;
import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataLoader {
    private final CategoryRepository categoryRepository;
    private final TemplateRepository templateRepository;



    //Loads Dummy Data and Important Template Data
    public void loadData() {


        if (categoryRepository.count() <= 0) {
            Category category = new Category();
            category.setId(1L);
            category.setCategoryName("General happiness");
            category.setScoreImpact(100);
            categoryRepository.save(category);

            Category category1 = new Category();
            category1.setId(2L);
            category1.setCategoryName("Workplace");
            category1.setScoreImpact(80);
            categoryRepository.save(category1);

            Category category2 = new Category();
            category2.setId(3L);
            category2.setCategoryName("Project");
            category2.setScoreImpact(90);
            categoryRepository.save(category2);
        }
        if (templateRepository.count() <= 0) {
            Template template = new Template();
            template.setId(1L);
            template.setTemplateName("Question Bar");

            TemplateOption option1 = new TemplateOption();
            option1.setTemplate(template);
            option1.setSetting("bar");
            option1.setSettingValue(true);

            TemplateOption option2 = new TemplateOption();
            option2.setTemplate(template);
            option2.setSetting("comment");
            option2.setSettingValue(false);

            List<TemplateOption> options = new ArrayList<>();
            options.add(option1);
            options.add(option2);
            template.setOptions(options);

            TemplateQuestion question1 = new TemplateQuestion();
            question1.setTemplate(template); // Set the Template object
            question1.setQuestion("Title");
            question1.setText("");

            TemplateQuestion question2 = new TemplateQuestion();
            question2.setTemplate(template); // Set the Template object
            question2.setQuestion("SubText");
            question2.setText("");

            TemplateQuestion question3 = new TemplateQuestion();
            question3.setTemplate(template); // Set the Template object
            question3.setQuestion("Bmin");
            question3.setText("1");

            TemplateQuestion question4 = new TemplateQuestion();
            question4.setTemplate(template); // Set the Template object
            question4.setQuestion("Bmax");
            question4.setText("5");

            TemplateQuestion question5 = new TemplateQuestion();
            question5.setTemplate(template); // Set the Template object
            question5.setQuestion("Step");
            question5.setText("1");

            TemplateQuestion question6 = new TemplateQuestion();
            question6.setTemplate(template); // Set the Template object
            question6.setQuestion("CategorieId");
            question6.setText("");

            List<TemplateQuestion> questions = new ArrayList<>();
            questions.add(question1);
            questions.add(question2);
            questions.add(question3);
            questions.add(question4);
            questions.add(question5);
            questions.add(question6);
            template.setQuestions(questions);

            template.setExternalPeople(null);
            templateRepository.save(template);

            Template template2 = new Template();
            template2.setId(3L);
            template2.setTemplateName("Question Comment");

            List<TemplateOption> options2 = new ArrayList<>();
            options2.add(new TemplateOption(null,template2, "bar", false));
            options2.add(new TemplateOption(null,template2, "comment", true));
            template2.setOptions(options2);
            template2.setQuestions(null);

            template2.setExternalPeople(null);
            templateRepository.save(template2);
        }

    }
}
