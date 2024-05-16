package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
@Table(name = "PushNotification")
public class PushNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(unique = true, nullable = false)
    private String token;

    public PushNotification() {
    }

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
