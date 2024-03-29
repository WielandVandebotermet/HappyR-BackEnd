package RealDolmen.HappyR.Data;

import lombok.Getter;
import lombok.Setter;

@Setter
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

}
