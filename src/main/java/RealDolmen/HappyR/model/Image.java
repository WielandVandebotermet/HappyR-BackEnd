package RealDolmen.HappyR.model;

import jakarta.persistence.*;
/**
 * Model class representing an image.
 */
@Entity // Specifies that this class is an entity
public class Image {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the image

    @Lob // Specifies that this field should be persisted as a large object
    private byte[] imageData; // Binary data of the image

    @OneToOne // Specifies a one-to-one relationship
    @JoinColumn(name = "user_id") // Specifies the foreign key column in the database
    private User user; // User associated with the image

    /**
     * Default constructor.
     */
    public Image() {
    }

    /**
     * Parameterized constructor to initialize image data.
     *
     * @param id         The ID of the image
     * @param imageData  The binary data of the image
     * @param user       The user associated with the image
     */
    public Image(Long id, byte[] imageData, User user) {
        this.id = id;
        this.imageData = imageData;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

