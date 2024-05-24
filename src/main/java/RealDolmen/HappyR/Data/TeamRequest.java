package RealDolmen.HappyR.Data;

/**
 * Data class representing a request to create a team.
 */
public class TeamRequest {
    private String GroupName; // The name of the team

    /**
     * Getter for the team name.
     *
     * @return The name of the team
     */
    public String getGroupName() {
        return GroupName;
    }

    /**
     * Setter for the team name.
     *
     * @param groupName The name of the team
     */
    public void setGroupName(String groupName) {
        GroupName = groupName;
    }
}

