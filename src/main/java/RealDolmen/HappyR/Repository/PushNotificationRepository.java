package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.PushNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for PushNotification entities, providing CRUD operations and additional query methods.
 */
@Repository // Indicates that this interface is a Spring repository component
public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {

    /**
     * Deletes all push notifications associated with a specific user ID.
     *
     * @param userId The ID of the user whose push notifications are to be deleted.
     */
    void deleteAllByUserId(Long userId);

    /**
     * Finds a push notification by its token.
     *
     * @param token The token of the push notification.
     * @return The push notification with the specified token.
     */
    PushNotification findByToken(String token);
}
