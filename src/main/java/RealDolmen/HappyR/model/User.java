package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
import java.util.regex.Pattern;
/**
 * Model class representing a user in the system.
 */
@Entity // Specifies that this class is an entity
@Table(name = "User", uniqueConstraints = {@UniqueConstraint(columnNames = "email")}) // Specifies the table name and unique constraints
@Builder // Lombok annotation for builder pattern
public class User {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the user

    @Column(unique = true, nullable = false) // Specifies unique constraint for email column
    private String authId; // Authentication ID of the user

    private String FullName; // Full name of the user
    private String FirstName; // First name of the user
    private String LastName; // Last name of the user
    private String email; // Email address of the user
    private String profileImage; // Profile image URL of the user

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // One-to-many relationship with PushNotification entity
    @JsonIgnore // Ignores this property during JSON serialization
    private List<PushNotification> pushNotifications; // List of push notifications associated with the user

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Parameterized constructor to initialize user data.
     *
     * @param id                 The ID of the user
     * @param authId             The authentication ID of the user
     * @param fullName           The full name of the user
     * @param firstName          The first name of the user
     * @param lastName           The last name of the user
     * @param email              The email address of the user
     * @param profileImage       The profile image URL of the user
     * @param pushNotifications  The list of push notifications associated with the user
     */
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


