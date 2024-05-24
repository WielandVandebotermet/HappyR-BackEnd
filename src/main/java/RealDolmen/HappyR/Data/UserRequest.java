package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.model.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
/**
 * Data class representing a user request.
 */
public class UserRequest {

    private String userId; // The unique identifier of the user
    private String fullName; // The full name of the user
    private String firstName; // The first name of the user
    private String lastName; // The last name of the user
    private String email; // The email address of the user
    private String profileImage; // The profile image URL of the user

    /**
     * Default constructor.
     */
    public UserRequest() {
    }

    /**
     * Parameterized constructor to initialize user request data.
     *
     * @param userId       The unique identifier of the user
     * @param fullName     The full name of the user
     * @param firstName    The first name of the user
     * @param lastName     The last name of the user
     * @param email        The email address of the user
     * @param profileImage The profile image URL of the user
     */
    public UserRequest(String userId, String fullName, String firstName, String lastName, String email, String profileImage) {
        this.userId = userId;
        this.fullName = fullName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileImage = profileImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
