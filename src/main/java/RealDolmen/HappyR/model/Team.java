package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
/**
 * Model class representing a team.
 */
@Entity // Specifies that this class is an entity
@Table(name = "Team") // Specifies the table name in the database
@Builder // Provides a builder pattern for creating instances of the class
public class Team {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the team

    private String GroupName; // Name of the group/team

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true) // Specifies a one-to-many relationship with Manager entities
    @JsonIgnore // Ignores this property during JSON serialization/deserialization
    private List<Manager> managers; // List of managers associated with the team

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true) // Specifies a one-to-many relationship with TeamUser entities
    @JsonIgnore // Ignores this property during JSON serialization/deserialization
    private List<TeamUser> teamUsers; // List of team users associated with the team

    /**
     * Default constructor.
     */
    public Team() {
    }

    /**
     * Parameterized constructor to initialize team data.
     *
     * @param id         The ID of the team
     * @param groupName  The name of the group/team
     * @param managers   The list of managers associated with the team
     * @param teamUsers  The list of team users associated with the team
     */
    public Team(Long id, String groupName, List<Manager> managers, List<TeamUser> teamUsers) {
        this.id = id;
        GroupName = groupName;
        this.managers = managers;
        this.teamUsers = teamUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public List<TeamUser> getTeamUsers() {
        return teamUsers;
    }
}
