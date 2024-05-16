package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.model.PushNotification;
import RealDolmen.HappyR.model.User;
import jakarta.persistence.Column;

public class SubscriptionRequest {
    private String userId;
    private String token;


    public SubscriptionRequest() {
    }

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
