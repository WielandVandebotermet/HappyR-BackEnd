package RealDolmen.HappyR;

import RealDolmen.HappyR.Data.SubscriptionRequest;
import RealDolmen.HappyR.Repository.PushNotificationRepository;
import RealDolmen.HappyR.Repository.UserRepository;
import RealDolmen.HappyR.Service.PushNotificationService;
import RealDolmen.HappyR.model.PushNotification;
import RealDolmen.HappyR.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PushNotificationTest {

    @Mock
    private PushNotificationRepository pushNotificationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PushNotificationService pushNotificationService;

    @Test
    void testSaveSubscription_UserNotFound() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setUserId("1");
        subscriptionRequest.setToken("token123");

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        pushNotificationService.saveSubscription(subscriptionRequest);

        verify(pushNotificationRepository, never()).save(any(PushNotification.class));
    }

    @Test
    void testSaveSubscription_TokenAlreadyExists() {
        User user = new User();
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setUserId("1");
        subscriptionRequest.setToken("token123");

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        when(pushNotificationRepository.findByToken(any(String.class))).thenReturn(new PushNotification());

        pushNotificationService.saveSubscription(subscriptionRequest);

        verify(pushNotificationRepository, never()).save(any(PushNotification.class));
    }
}
