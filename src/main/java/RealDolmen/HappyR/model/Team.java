package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "Team")
@Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String GroupName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Manager> managers;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TeamUser> teamUsers;

    public Team() {
    }

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
