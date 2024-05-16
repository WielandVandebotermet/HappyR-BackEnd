package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
import java.util.regex.Pattern;

@Table(name = "User", uniqueConstraints = {@UniqueConstraint(columnNames = "email"),})
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String authId;
    private String FullName;
    private String FirstName;
    private String LastName;
    private String email;
    private String profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PushNotification> pushNotifications;

    public User() {
    }

    public User(Long id, String authId, String fullName, String firstName, String lastName, String email, String profileImage, List<PushNotification> pushNotifications) {
        this.id = id;
        this.authId = authId;
        FullName = fullName;
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
        this.profileImage = profileImage;
        this.pushNotifications = pushNotifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        authId = authId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public List<PushNotification> getPushNotifications() {
        return pushNotifications;
    }

    public void setPushNotifications(List<PushNotification> pushNotifications) {
        this.pushNotifications = pushNotifications;
    }
}


