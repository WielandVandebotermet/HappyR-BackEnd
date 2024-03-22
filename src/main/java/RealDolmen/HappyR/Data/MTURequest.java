package RealDolmen.HappyR.Data;

import lombok.Getter;

@Getter
public class MTURequest {
    private int teamId;
    private int userId;

    public MTURequest() {
    }

    public MTURequest(int teamId, int userId) {
        this.teamId = teamId;
        this.userId = userId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
