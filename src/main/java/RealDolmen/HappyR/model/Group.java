package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "Group")
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String GroupName;

    public Group() {
    }

    public Group(Long id, String groupName) {
        this.id = id;
        GroupName = groupName;
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
}
