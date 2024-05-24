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

// Import necessary packages and classes

// Annotation to enable Mockito extensions for JUnit 5
@ExtendWith(MockitoExtension.class)
public class PushNotificationTest {

    // Mocking the PushNotificationRepository
    @Mock
    private PushNotificationRepository pushNotificationRepository;

    // Mocking the UserRepository
    @Mock
    private UserRepository userRepository;

    // Injecting mocked PushNotificationService into test class
    @InjectMocks
    private PushNotificationService pushNotificationService;

    // Test case for saving subscription when user is not found
    @Test
    void testSaveSubscription_UserNotFound() {
        // Creating test SubscriptionRequest
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setUserId("1");
        subscriptionRequest.setToken("token123");

        // Configuring behavior of userRepository mock
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // Invoking the method under test
        pushNotificationService.saveSubscription(subscriptionRequest);

        // Verifying that save method of pushNotificationRepository is never called
        verify(pushNotificationRepository, never()).save(any(PushNotification.class));
    }

    // Test case for saving subscription when token already exists
    @Test
    void testSaveSubscription_TokenAlreadyExists() {
        // Creating test User and SubscriptionRequest
        User user = new User();
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setUserId("1");
        subscriptionRequest.setToken("token123");

        // Configuring behavior of userRepository and pushNotificationRepository mocks
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        when(pushNotificationRepository.findByToken(any(String.class))).thenReturn(new PushNotification());

        // Invoking the method under test
        pushNotificationService.saveSubscription(subscriptionRequest);

        // Verifying that save method of pushNotificationRepository is never called
        verify(pushNotificationRepository, never()).save(any(PushNotification.class));
    }
}
