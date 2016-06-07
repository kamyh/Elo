package swiss.kamyh.elo.matchMaking;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Vincent on 07.06.2016.
 */
public class GameStatistics extends DbMongo {

    private final MongoCollection<Document> collectionKills;
    private final MongoCollection<Document> collectionGames;
    private ArrayList<String> playersUUID;
    private String randomUUID;

    public GameStatistics(String host, int port, String user, String database, String password, ArrayList<String> playersUUID) {
        super(host, port, user, database, password);

        this.playersUUID = playersUUID;

        this.collectionKills = this.db.getCollection("kills");
        this.collectionGames = this.db.getCollection("games");

        this.setRandomUUID();
    }

    public void insertKill(String killer, String killed) {

        java.util.Date date = new java.util.Date();
        Document doc = new Document("player", "kamhy")
                .append("time", (new Timestamp(date.getTime())))
                .append("game", this.getRandomUUID())
                .append("opponants", new Document("killer", killer).append("killed", killed));

        this.collectionKills.insertOne(doc);

        Document myDoc = collectionKills.find().first();
        System.out.println(myDoc.toJson());
    }

    public void insertGame() {
        java.util.Date date = new java.util.Date();

        Document participants = new Document();

        for(String uuid:this.playersUUID)
        {
            participants.append("uuid",uuid);
        }

        Document doc = new Document("id", this.getRandomUUID())
                .append("time", (new Timestamp(date.getTime())))
                .append("participants", participants);

        this.collectionKills.insertOne(doc);

        Document myDoc = collectionKills.find().first();
        System.out.println(myDoc.toJson());
    }


    public String getRandomUUID() {
        return this.randomUUID;
    }

    public String setRandomUUID() {
        this.randomUUID = UUID.randomUUID().toString();
        return this.randomUUID;
    }
}
