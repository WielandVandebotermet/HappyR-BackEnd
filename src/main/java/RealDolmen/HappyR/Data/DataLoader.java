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
    private final TeamUserRepository teamUserRepository;
    private final ManagerRepository managerRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TemplateRepository templateRepository;
    private final SurveyRepository surveyRepository;

    private final TeamService teamService;
    private final UserService userService;


    //Loads Dummy Data and Important Template Data
    public void loadData() {
        if (userRepository.count() <= 0) {
            User user = new User();
            user.setId(1L);
            user.setFirstName("Wieland");
            user.setLastName("Vandebotermet");
            userRepository.save(user);

            User user1 = new User();
            user1.setId(2L);
            user1.setFirstName("Hugh");
            user1.setLastName("Hargraves");
            userRepository.save(user1);

            User user2 = new User();
            user2.setId(3L);
            user2.setFirstName("Jeff");
            user2.setLastName("Burrows");
            userRepository.save(user2);

            User user3 = new User();
            user3.setId(4L);
            user3.setFirstName("Tilda");
            user3.setLastName("Miles");
            userRepository.save(user3);
        }

        if (teamRepository.count() <= 0) {
            Team team = new Team();
            team.setId(1L);
            team.setGroupName("Development");
            teamRepository.save(team);

            Team team1 = new Team();
            team1.setId(2L);
            team1.setGroupName("Operations");
            teamRepository.save(team1);

            Team team2 = new Team();
            team2.setId(3L);
            team2.setGroupName("Quality Control");
            teamRepository.save(team2);
        }

        if (teamUserRepository.count() <= 0) {
            TeamUser teamUser = new TeamUser();
            teamUser.setId(1L);
            teamUser.setUser(userService.getUserById(2));
            teamUser.setTeam(teamService.getTeamById(1));
            teamUserRepository.save(teamUser);

            TeamUser teamUser1 = new TeamUser();
            teamUser1.setId(2L);
            teamUser1.setUser(userService.getUserById(3));
            teamUser1.setTeam(teamService.getTeamById(1));
            teamUserRepository.save(teamUser1);

            TeamUser teamUser2 = new TeamUser();
            teamUser2.setId(3L);
            teamUser2.setUser(userService.getUserById(2));
            teamUser2.setTeam(teamService.getTeamById(2));
            teamUserRepository.save(teamUser2);

            TeamUser teamUser3 = new TeamUser();
            teamUser3.setId(4L);
            teamUser3.setUser(userService.getUserById(3));
            teamUser3.setTeam(teamService.getTeamById(3));
            teamUserRepository.save(teamUser3);
        }

        if (managerRepository.count() <= 0) {
            Team team = teamService.getTeamById(1);
            Team team1 = teamService.getTeamById(2);
            Team team2 = teamService.getTeamById(3);
            User user = userService.getUserById(1);

            Manager manager = new Manager();
            manager.setId(1L);
            manager.setUser(user);
            manager.setTeam(team);
            managerRepository.save(manager);

            Manager manager1 = new Manager();
            manager1.setId(2L);
            manager1.setUser(user);
            manager1.setTeam(team1);
            managerRepository.save(manager1);

            Manager manager2 = new Manager();
            manager2.setId(3L);
            manager2.setUser(user);
            manager2.setTeam(team2);
            managerRepository.save(manager2);
        }

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
            option1.setSetting("subtext");
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

            Template template1 = new Template();
            template1.setId(2L);
            template1.setTemplateName("Team Question Bar");

            List<TemplateOption> options1 = new ArrayList<>();
            options1.add(new TemplateOption(null,template1, "subtext", true));
            options1.add(new TemplateOption(null,template1,"comment", false));
            options1.add(new TemplateOption(null,template1,"IncludeManager", false));
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
        }

        if (surveyRepository.count() <= 0) {
            Survey survey1 = new Survey();
            survey1.setId(null);
            survey1.setTestName("Inactive Happiness Test");
            survey1.setStartDate(Calendar.getInstance());
            survey1.setStarted(false);
            survey1.setQuestions(null);

            SurveyReoccuring reoccuring = new SurveyReoccuring();
            reoccuring.setId(null);
            reoccuring.setSurvey(survey1);
            reoccuring.setTime(4);
            reoccuring.setTimeMultiplier("W");

            survey1.setSurveyReoccuring(reoccuring);

            List<Long> groupList2 = new ArrayList<>();
            groupList2.add(1L);
            survey1.setGroupList(groupList2);

            List<SurveyQuestion> question = new ArrayList<>();

            SurveyQuestion question1 = new SurveyQuestion();
            question1.setSurvey(survey1);
            question1.setTemplateId("1");
            question1.setQuestion("How do you currently like your work environment?");
            question1.setText("A 1 means not at all and a 5 means very much.");

            SurveyQuestion question2 = new SurveyQuestion();
            question2.setSurvey(survey1);
            question2.setTemplateId("1");
            question2.setQuestion("How do you currently like your assigned project?");
            question2.setText("A 1 means not at all and a 10 means very much.");

            SurveyQuestion question3 = new SurveyQuestion();
            question3.setSurvey(survey1);
            question3.setTemplateId("1");
            question3.setQuestion("How problematic do you find your team?");
            question3.setText("A 1 means very problamatic and a 5 means there are no problems.");


            List<SurveyQuestionOption> options1 = new ArrayList<>();
            options1.add(new SurveyQuestionOption(null,question1, "subtext", true));
            options1.add(new SurveyQuestionOption(null,question1,"comment", false));
            options1.add(new SurveyQuestionOption(null,question1,"IncludeManager", false));
            question1.setOptions(options1);

            List<SurveyQuestionSetting> setting1 = new ArrayList<>();
            setting1.add(new SurveyQuestionSetting(null,question1,"Bmin", "1"));
            setting1.add(new SurveyQuestionSetting(null,question1,"Bmax", "5"));
            setting1.add(new SurveyQuestionSetting(null,question1,"Step", "1"));
            setting1.add(new SurveyQuestionSetting(null,question1,"CategorieId", "2"));
            question1.setSettings(setting1);

            List<SurveyQuestionOption> options2 = new ArrayList<>();
            options2.add(new SurveyQuestionOption(null,question2, "subtext", true));
            options2.add(new SurveyQuestionOption(null,question2,"comment", false));
            options2.add(new SurveyQuestionOption(null,question2,"IncludeManager", false));
            question2.setOptions(options2);

            List<SurveyQuestionSetting> setting2 = new ArrayList<>();
            setting2.add(new SurveyQuestionSetting(null,question2,"Bmin", "1"));
            setting2.add(new SurveyQuestionSetting(null,question2,"Bmax", "5"));
            setting2.add(new SurveyQuestionSetting(null,question2,"Step", "1"));
            setting2.add(new SurveyQuestionSetting(null,question2,"CategorieId", "3"));
            question2.setSettings(setting2);

            List<SurveyQuestionOption> options3 = new ArrayList<>();
            options3.add(new SurveyQuestionOption(null,question3, "subtext", true));
            options3.add(new SurveyQuestionOption(null,question3,"comment", false));
            options3.add(new SurveyQuestionOption(null,question3,"IncludeManager", false));
            question3.setOptions(options3);

            List<SurveyQuestionSetting> setting3 = new ArrayList<>();
            setting3.add(new SurveyQuestionSetting(null,question3,"Bmin", "1"));
            setting3.add(new SurveyQuestionSetting(null,question3,"Bmax", "5"));
            setting3.add(new SurveyQuestionSetting(null,question3,"Step", "1"));
            setting3.add(new SurveyQuestionSetting(null,question3,"CategorieId", "1"));
            question3.setSettings(setting3);

            question.add(question1);
            question.add(question2);
            question.add(question3);
            survey1.setQuestions(question);

            surveyRepository.save(survey1);

            Survey survey = new Survey();
            survey.setId(null);
            survey.setStarted(true);
            survey.setTestName("Active Happiness Test");
            survey.setStartDate(Calendar.getInstance());

            SurveyReoccuring reoccuring2 = new SurveyReoccuring();
            reoccuring.setId(null);
            reoccuring2.setSurvey(survey);
            reoccuring2.setTime(4);
            reoccuring2.setTimeMultiplier("W");

            survey.setSurveyReoccuring(reoccuring2);

            List<Long> groupList = new ArrayList<>();
            groupList.add(1L);
            groupList.add(2L);
            groupList.add(3L);
            survey.setGroupList(groupList);

            List<SurveyQuestion> questions = new ArrayList<>();

            SurveyQuestion question4 = new SurveyQuestion();
            question4.setSurvey(survey);
            question4.setTemplateId("1");
            question4.setQuestion("How do you currently like your work environment?");
            question4.setText("A 1 means not at all and a 5 means very much.");

            SurveyQuestion question5 = new SurveyQuestion();
            question5.setSurvey(survey);
            question5.setTemplateId("1");
            question5.setQuestion("How do you currently like your assigned project?");
            question5.setText("A 1 means not at all and a 10 means very much.");

            SurveyQuestion question6 = new SurveyQuestion();
            question6.setSurvey(survey);
            question6.setTemplateId("1");
            question6.setQuestion("How problematic do you find your team?");
            question6.setText("A 1 means very problamatic and a 5 means there are no problems.");


            List<SurveyQuestionOption> options4 = new ArrayList<>();
            options4.add(new SurveyQuestionOption(null,question4, "subtext", true));
            options4.add(new SurveyQuestionOption(null,question4,"comment", false));
            options4.add(new SurveyQuestionOption(null,question4,"IncludeManager", false));
            question4.setOptions(options4);

            List<SurveyQuestionSetting> setting4 = new ArrayList<>();
            setting4.add(new SurveyQuestionSetting(null,question4,"Bmin", "1"));
            setting4.add(new SurveyQuestionSetting(null,question4,"Bmax", "5"));
            setting4.add(new SurveyQuestionSetting(null,question4,"Step", "1"));
            setting4.add(new SurveyQuestionSetting(null,question4,"CategorieId", "2"));
            question4.setSettings(setting4);

            List<SurveyQuestionOption> options5 = new ArrayList<>();
            options5.add(new SurveyQuestionOption(null,question5, "subtext", true));
            options5.add(new SurveyQuestionOption(null,question5,"comment", false));
            options5.add(new SurveyQuestionOption(null,question5,"IncludeManager", false));
            question5.setOptions(options5);

            List<SurveyQuestionSetting> setting5 = new ArrayList<>();
            setting5.add(new SurveyQuestionSetting(null,question5,"Bmin", "1"));
            setting5.add(new SurveyQuestionSetting(null,question5,"Bmax", "10"));
            setting5.add(new SurveyQuestionSetting(null,question5,"Step", "1"));
            setting5.add(new SurveyQuestionSetting(null,question5,"CategorieId", "3"));
            question5.setSettings(setting5);

            List<SurveyQuestionOption> options6 = new ArrayList<>();
            options6.add(new SurveyQuestionOption(null,question6, "subtext", true));
            options6.add(new SurveyQuestionOption(null,question6,"comment", false));
            options6.add(new SurveyQuestionOption(null,question6,"IncludeManager", false));
            question6.setOptions(options6);

            List<SurveyQuestionSetting> setting6 = new ArrayList<>();
            setting6.add(new SurveyQuestionSetting(null,question6,"Bmin", "1"));
            setting6.add(new SurveyQuestionSetting(null,question6,"Bmax", "5"));
            setting6.add(new SurveyQuestionSetting(null,question6,"Step", "1"));
            setting6.add(new SurveyQuestionSetting(null,question6,"CategorieId", "1"));
            question6.setSettings(setting6);

            questions.add(question4);
            questions.add(question5);
            questions.add(question6);
            survey.setQuestions(questions);

            surveyRepository.save(survey);

            Survey survey2 = new Survey();
            survey2.setId(null);
            survey2.setStarted(true);
            survey2.setTestName("Active Satisfaction Test");
            survey2.setStartDate(Calendar.getInstance());
            survey2.setQuestions(null);
            survey2.setSurveyReoccuring(null);

            List<Long> groupList3 = new ArrayList<>();
            groupList3.add(2L);
            survey2.setGroupList(groupList3);

            surveyRepository.save(survey2);

            Survey survey3 = new Survey();
            survey3.setId(null);
            survey3.setStarted(true);
            survey3.setTestName("Active Workplace satisfaction Test");
            survey3.setStartDate(Calendar.getInstance());
            survey3.setQuestions(null);
            survey3.setSurveyReoccuring(null);

            List<Long> groupList4 = new ArrayList<>();
            groupList4.add(3L);
            survey3.setGroupList(groupList4);

            surveyRepository.save(survey3);
        }

    }
}
