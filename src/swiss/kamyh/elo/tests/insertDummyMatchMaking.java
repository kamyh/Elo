package swiss.kamyh.elo.tests;

import swiss.kamyh.elo.matchMaking.DbMongo;
import swiss.kamyh.elo.matchMaking.GameStatistics;
import swiss.kamyh.elo.matchMaking.PlayerStatistics;

import java.util.ArrayList;

/**
 * Created by Vincent on 07.06.2016.
 */
public class insertDummyMatchMaking {
    public void insert()
    {
        ArrayList<String> playersUUID = new ArrayList<>();

        playersUUID.add("bce5672b-7006-3dae-930d-02499be38039");
        playersUUID.add("6e38a62a-2b1a-382b-9f3d-ae45181f5d5d");

        GameStatistics gameStatistics = new GameStatistics(
                "192.168.99.100",
                27017,
                "elo",
                "elo",
                "elo",
                playersUUID
        );

        PlayerStatistics playerStatistics_1 = new PlayerStatistics(
                "192.168.99.100",
                27017,
                "elo",
                "elo",
                "elo",
                "bce5672b-7006-3dae-930d-02499be38039"
        );

        PlayerStatistics playerStatistics_2 = new PlayerStatistics(
                "192.168.99.100",
                27017,
                "elo",
                "elo",
                "elo",
                "6e38a62a-2b1a-382b-9f3d-ae45181f5d5d"
        );

        gameStatistics.insertGame();

        gameStatistics.insertKill("bce5672b-7006-3dae-930d-02499be38039","6e38a62a-2b1a-382b-9f3d-ae45181f5d5d");
        gameStatistics.insertKill("bce5672b-7006-3dae-930d-02499be38039","6e38a62a-2b1a-382b-9f3d-ae45181f5d5d");
        gameStatistics.insertKill("bce5672b-7006-3dae-930d-02499be38039","6e38a62a-2b1a-382b-9f3d-ae45181f5d5d");
        gameStatistics.insertKill("bce5672b-7006-3dae-930d-02499be38039","6e38a62a-2b1a-382b-9f3d-ae45181f5d5d");
        gameStatistics.insertKill("bce5672b-7006-3dae-930d-02499be38039","6e38a62a-2b1a-382b-9f3d-ae45181f5d5d");
        gameStatistics.insertKill("6e38a62a-2b1a-382b-9f3d-ae45181f5d5d","bce5672b-7006-3dae-930d-02499be38039");
        gameStatistics.insertKill("6e38a62a-2b1a-382b-9f3d-ae45181f5d5d","bce5672b-7006-3dae-930d-02499be38039");
        gameStatistics.insertKill("6e38a62a-2b1a-382b-9f3d-ae45181f5d5d","bce5672b-7006-3dae-930d-02499be38039");

        playerStatistics_1.insertOutcome(gameStatistics.getRandomUUID(),1);
        playerStatistics_2.insertOutcome(gameStatistics.getRandomUUID(),0);

    }

    public static void main (String[] args){
        insertDummyMatchMaking insertDummyMatchMaking = new insertDummyMatchMaking();
        insertDummyMatchMaking.insert();
    }
}
