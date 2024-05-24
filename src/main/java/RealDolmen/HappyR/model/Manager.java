package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Builder;
/**
 * Model class representing a manager.
 */
@Entity // Specifies that this class is an entity
@Table(name = "Manager") // Specifies the table name in the database
@Builder // Lombok annotation to generate builder methods
public class Manager {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the manager

    @ManyToOne // Specifies a many-to-one relationship
    @JoinColumn(name = "user_id") // Specifies the foreign key column in the database
    private User user; // User associated with the manager

    @ManyToOne // Specifies a many-to-one relationship
    @JoinColumn(name = "group_id") // Specifies the foreign key column in the database
    private Team team; // Team associated with the manager

    /**
     * Default constructor.
     */
    public Manager() {
    }

    /**
     * Parameterized constructor to initialize manager data.
     *
     * @param id    The ID of the manager
     * @param user  The user associated with the manager
     * @param team  The team associated with the manager
     */
    public Manager(Long id, User user, Team team) {
        this.id = id;
        this.user = user;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
