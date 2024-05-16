package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Data.SubscriptionRequest;
import RealDolmen.HappyR.Service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/PushNotification")
@RequiredArgsConstructor
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        pushNotificationService.saveSubscription(subscriptionRequest);
        return ResponseEntity.ok("Subscription successful");
    }

    @PostMapping("/unsubscribe")
    @ResponseStatus(HttpStatus.OK)
    public void unsubscribe(@RequestBody String UserId) {
        pushNotificationService.deleteSubscription(UserId);
    }
}
