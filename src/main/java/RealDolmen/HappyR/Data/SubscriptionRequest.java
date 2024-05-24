package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.model.PushNotification;
import RealDolmen.HappyR.model.User;
import jakarta.persistence.Column;

/**
 * Data class representing a subscription request for push notifications.
 */
public class SubscriptionRequest {
    private String userId; // The ID of the user
    private String token; // The token for push notifications

    /**
     * Default constructor.
     */
    public SubscriptionRequest() {
    }

    /**
     * Parameterized constructor.
     *
     * @param userId The ID of the user
     * @param token  The token for push notifications
     */
    public SubscriptionRequest(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
