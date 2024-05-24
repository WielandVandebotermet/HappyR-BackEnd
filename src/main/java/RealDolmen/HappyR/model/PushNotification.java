package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Builder;
/**
 * Model class representing a push notification.
 */
@Builder // Lombok annotation to generate builder methods
@Entity // Specifies that this class is an entity
@Table(name = "PushNotification") // Specifies the table name in the database
public class PushNotification {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the push notification

    @ManyToOne // Specifies a many-to-one relationship
    @JoinColumn(name = "user_id") // Specifies the foreign key column in the database
    private User user; // User associated with the push notification

    @Column(unique = true, nullable = false) // Specifies column constraints
    private String token; // Token associated with the push notification

    /**
     * Default constructor.
     */
    public PushNotification() {
    }

    /**
     * Parameterized constructor to initialize push notification data.
     *
     * @param id    The ID of the push notification
     * @param user  The user associated with the push notification
     * @param token The token associated with the push notification
     */
    public PushNotification(Long id, User user, String token) {
        this.id = id;
        this.user = user;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
