package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.Repository.*;
import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        }

        if (surveyRepository.count() <= 0) {
            Survey survey = new Survey();
            survey.setId(1L);
            survey.setTestName("Satisfaction Survey");
            survey.setStartDate(new Date());
            survey.setStarted(true);
            survey.setQuestions(null);
            survey.setReoccuring(null);

            List<Long> groupList = new ArrayList<>();
            groupList.add(1L);
            survey.setGroupList(groupList);

            surveyRepository.save(survey);
        }

    }
}
