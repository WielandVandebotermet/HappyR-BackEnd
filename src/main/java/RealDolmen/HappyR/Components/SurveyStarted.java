package RealDolmen.HappyR.Components;

import RealDolmen.HappyR.Repository.PushNotificationRepository;
import RealDolmen.HappyR.Repository.SurveyRepository;
import RealDolmen.HappyR.Repository.TeamRepository;
import RealDolmen.HappyR.Repository.UserRepository;
import RealDolmen.HappyR.Service.PushNotificationService;
import RealDolmen.HappyR.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SurveyStarted {

    private final SurveyRepository surveyRepository;
    private final TeamRepository teamRepository;
    private final PushNotificationService pushNotificationService;
    private final UserRepository userRepository;
    private final PushNotificationRepository pushNotificationRepository;


    @Scheduled(cron = "0 1 0 * * *") // Run at 00:00 every day
    public void runAtMidnight() {
        Calendar currentDate = Calendar.getInstance();
        List<Survey> surveys = surveyRepository.findByStartDateBeforeAndStartedFalse(currentDate);

        for (Survey survey : surveys) {
            survey.setStarted(true);
            surveyRepository.save(survey);

            List<User> users = new ArrayList<>();
            for (Long teamId : survey.getGroupList()) {
                Team team = teamRepository.findById(teamId).orElse(null);

                if (team != null) {
                    List<Manager> managers = team.getManagers();
                    List<TeamUser> teamUsers = team.getTeamUsers();

                    // Add managers' users to the list
                    for (Manager manager : managers) {
                        users.add(manager.getUser());
                    }

                    // Add team users to the list
                    for (TeamUser teamUser : teamUsers) {
                        users.add(teamUser.getUser());
                    }
                }
            }

            for (User user : users) {
                List<PushNotification> pushNotifications = user.getPushNotifications();
                for (PushNotification pushNotification : pushNotifications) {
                    String message = "There is a new Survey!";
                    pushNotificationService.sendPushNotification(pushNotification.getToken(),  message);
                }
            }
        }
    }
}
