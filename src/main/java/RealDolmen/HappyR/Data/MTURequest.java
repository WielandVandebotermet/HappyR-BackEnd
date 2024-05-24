package RealDolmen.HappyR.Data;

import lombok.Getter;
import lombok.Setter;
/**
 * Data class representing a request to create a relationship between a team and a user.
 */
@Setter
@Getter
public class MTURequest {
    private int teamId; // The ID of the team
    private int userId; // The ID of the user

    /**
     * Default constructor.
     */
    public MTURequest() {
    }

    /**
     * Parameterized constructor.
     *
     * @param teamId The ID of the team
     * @param userId The ID of the user
     */
    public MTURequest(int teamId, int userId) {
        this.teamId = teamId;
        this.userId = userId;
    }
}
