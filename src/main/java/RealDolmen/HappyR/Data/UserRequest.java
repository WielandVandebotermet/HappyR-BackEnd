package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.model.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

public class UserRequest {

    private String userId;
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String profileImage;

    public UserRequest() {
    }

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
