package swiss.kamyh.elo.matchMaking;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by Vincent on 07.06.2016.
 */
public class MatchMaking {
    private ArrayList<String> playersUUIDTeam;

    public MatchMaking(ArrayList<String> playersUUIDTeam)
    {
        this.playersUUIDTeam = playersUUIDTeam;
    }

    public ArrayList<String> getTeamOpponents()
    {
        //TODO
        throw new NotImplementedException();
    }
}
