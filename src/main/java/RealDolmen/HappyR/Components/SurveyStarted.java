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
// Import statements for required classes and libraries

// Component class responsible for running a scheduled task to start surveys
@Component
@RequiredArgsConstructor
public class SurveyStarted {

    // Repositories and services needed for the task
    private final SurveyRepository surveyRepository;
    private final TeamRepository teamRepository;
    private final PushNotificationService pushNotificationService;
    private final UserRepository userRepository;
    private final PushNotificationRepository pushNotificationRepository;

    // Scheduled method to run at the top of every hour
    @Scheduled(cron = "0 0 * * * *")
    public void runAtMidnight() {
        // Getting the current date
        Calendar currentDate = Calendar.getInstance();

        // Retrieving surveys that have not started and have a start date before the current date
        List<Survey> surveys = surveyRepository.findByStartDateBeforeAndStartedFalse(currentDate);

        // Iterating over each survey
        for (Survey survey : surveys) {
            // Setting the survey as started
            survey.setStarted(true);
            surveyRepository.save(survey);

            // Initializing a list to store users who will receive push notifications
            List<User> users = new ArrayList<>();

            // Iterating over each team associated with the survey
            for (Long teamId : survey.getGroupList()) {
                // Retrieving the team by its ID
                Team team = teamRepository.findById(teamId).orElse(null);

                // If team exists
                if (team != null) {
                    // Retrieving managers and team users
                    List<Manager> managers = team.getManagers();
                    List<TeamUser> teamUsers = team.getTeamUsers();

                    // Adding managers' users to the list
                    for (Manager manager : managers) {
                        users.add(manager.getUser());
                    }

                    // Adding team users to the list
                    for (TeamUser teamUser : teamUsers) {
                        users.add(teamUser.getUser());
                    }
                }
            }

            // Sending push notifications to users
            for (User user : users) {
                List<PushNotification> pushNotifications = user.getPushNotifications();
                for (PushNotification pushNotification : pushNotifications) {
                    String message = "There is a new Survey!";
                    pushNotificationService.sendPushNotification(pushNotification.getToken(), message);
                }
            }
        }
    }
}
