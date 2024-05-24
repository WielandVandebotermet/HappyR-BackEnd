package RealDolmen.HappyR.Service;


import RealDolmen.HappyR.Data.SubscriptionRequest;
import RealDolmen.HappyR.Repository.PushNotificationRepository;
import RealDolmen.HappyR.Repository.UserRepository;
import RealDolmen.HappyR.model.PushNotification;
import RealDolmen.HappyR.model.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for managing push notification-related operations.
 */
@Service
@RequiredArgsConstructor // Generates a constructor with required arguments for the final fields
public class PushNotificationService {

    @Value("${VAPID_PRIVATE_KEY}")
    private String privateKey;

    @Value("${VAPID_PUBLIC_KEY}")
    private String publicKey;

    @Value("${fcm.server.key}")
    private String fcmServerKey;

    @Value("${projectID}")
    private String projectID;

    private final PushNotificationRepository pushNotificationRepository;
    private final UserRepository userRepository;

    /**
     * Saves the subscription information for push notifications.
     *
     * @param subscription The subscription request containing user ID and token.
     */
    public void saveSubscription(SubscriptionRequest subscription) {

        if (!subscription.getUserId().isEmpty() || !(subscription.getUserId().equals("undefined"))) {
            User user = userRepository.findById(Long.valueOf(subscription.getUserId())).orElse(null);
            PushNotification pushNotificationToken = pushNotificationRepository.findByToken(subscription.getToken());

            if (user != null && pushNotificationToken == null) {
                PushNotification pushNotification = PushNotification.builder()
                        .token(subscription.getToken())
                        .user(user)
                        .build();

                pushNotificationRepository.save(pushNotification);
                sendSuccessNotification(pushNotification);
            }
        }
    }

    /**
     * Sends a success notification for a new subscription.
     *
     * @param pushNotification The push notification object.
     */
    private void sendSuccessNotification(PushNotification pushNotification) {
        String message = "Subscription successful";
        sendPushNotification(pushNotification.getToken(), message);
    }

    /**
     * Deletes the push notification subscription for a given user.
     *
     * @param userId The ID of the user.
     */
    public void deleteSubscription(String userId) {
        pushNotificationRepository.deleteAllByUserId(Long.valueOf(userId));
    }

    /**
     * Sends a push notification to a device token with a specified message body.
     *
     * @param token The device token to send the notification to.
     * @param body  The message body of the notification.
     * @return The response from Firebase Cloud Messaging (FCM).
     */
    public String sendPushNotification(String token, String body) {
        Map<String, String> firebaseMessageBody = new HashMap<>();
        firebaseMessageBody.put("title", "HappyR Notification");
        firebaseMessageBody.put("body", body);
        try {
            Message message = Message
                    .builder()
                    .setToken(token)
                    .putAllData(firebaseMessageBody)
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println(response);
            return response;
        } catch (FirebaseMessagingException e) {
            System.out.println(e);
            return "Firebase error sending";
        }
    }

}