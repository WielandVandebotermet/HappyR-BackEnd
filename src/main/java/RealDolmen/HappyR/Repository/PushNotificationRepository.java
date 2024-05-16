package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.PushNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {

    void deleteAllByUserId(Long userId);

    PushNotification findByToken(String Token);
}
