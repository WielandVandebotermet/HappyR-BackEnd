package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Builder;
/**
 * Model class representing the association between a user and a team.
 */
@Entity // Specifies that this class is an entity
@Table(name = "GroupUser") // Specifies the table name in the database
@Builder // Provides a builder pattern for creating instances of the class
public class TeamUser {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the team user association

    @ManyToOne // Specifies a many-to-one relationship with the User entity
    @JoinColumn(name = "user_id") // Specifies the foreign key column name in the database
    private User user; // User associated with the team

    @ManyToOne // Specifies a many-to-one relationship with the Team entity
    @JoinColumn(name = "group_id") // Specifies the foreign key column name in the database
    private Team team; // Team associated with the user

    /**
     * Default constructor.
     */
    public TeamUser() {
    }

    /**
     * Parameterized constructor to initialize team user association data.
     *
     * @param id    The ID of the team user association
     * @param user  The user associated with the team
     * @param team  The team associated with the user
     */
    public TeamUser(Long id, User user, Team team) {
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
