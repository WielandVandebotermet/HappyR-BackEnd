package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Data.SubscriptionRequest;
import RealDolmen.HappyR.Service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// Controller for managing push notification subscriptions
@RestController
@RequestMapping("/PushNotification")
@RequiredArgsConstructor
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;

    // Endpoint for subscribing to push notifications
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        pushNotificationService.saveSubscription(subscriptionRequest);
        return ResponseEntity.ok("Subscription successful");
    }

    // Endpoint for unsubscribing from push notifications
    @PostMapping("/unsubscribe")
    @ResponseStatus(HttpStatus.OK)
    public void unsubscribe(@RequestBody String UserId) {
        pushNotificationService.deleteSubscription(UserId);
    }
}
