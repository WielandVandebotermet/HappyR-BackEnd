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

@Service
@RequiredArgsConstructor
public class PushNotificationService {

    @Value("${VAPID_PRIVATE_KEY}")
    private String privateKey;

    @Value("${VAPID_PUBLIC_KEY}")
    private String publicKey;

    @Value("${fcm.server.key}")
    private String fcmServerKey;

    @Value("${projectID}")
    private String projectID;

    private final PushNotificationRepository PushNotificationRepository;
    private final UserRepository userRepository;

    public void saveSubscription(SubscriptionRequest subscription) {

        if(!subscription.getUserId().isEmpty() || !(subscription.getUserId().equals("undefined"))) {
            User user = userRepository.findById(Long.valueOf(subscription.getUserId())).orElse(null);
            PushNotification pushNotificationToken = PushNotificationRepository.findByToken(subscription.getToken());

            if (user != null && pushNotificationToken == null ) {
                PushNotification pushNotification = PushNotification.builder()
                        .token(subscription.getToken())
                        .user(user)
                        .build();

                PushNotificationRepository.save(pushNotification);
                sendSuccessNotification(pushNotification);
            }
        }
    }

    private void sendSuccessNotification(PushNotification pushNotification) {
        String message = "Subscription successful";
        sendPushNotification(pushNotification.getToken(), message);
    }

    public void deleteSubscription(String UserId) {
        PushNotificationRepository.deleteAllByUserId(Long.valueOf(UserId));
    }

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

