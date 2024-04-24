package RealDolmen.HappyR.model;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.regex.Pattern;

@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),})
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String FirstName;
    private String LastName;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Image profileImage;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, String password, Image profileImage) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public String getPassword() {
        return this.password;
    }

    public static boolean isValidPassword(String password) {
        // Password must be at least 8 characters long
        // Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return Pattern.compile(passwordRegex).matcher(password).matches();
    }

    // Hash the password before storing it in the database
    public void setPassword(String password) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password format");
        }
        // Hash the password using BCryptPasswordEncoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }
}


