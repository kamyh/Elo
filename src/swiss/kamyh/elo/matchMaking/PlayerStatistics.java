package swiss.kamyh.elo.matchMaking;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.sql.Timestamp;

/**
 * Created by Vincent on 07.06.2016.
 */
public class PlayerStatistics extends DbMongo {

    private final String playerUUID;
    private MongoCollection<Document> collectionOutcome;

    public PlayerStatistics(String host, int port, String user, String database, String password, String playerUUID) {
        super(host, port, user, database, password);

        this.playerUUID = playerUUID;
        this.collectionOutcome = this.db.getCollection("outcome");
    }

    public void insertOutcome(String gameUUID, int outcome) {

        java.util.Date date = new java.util.Date();

        Document doc = new Document("player", this.playerUUID)
                .append("time", (new Timestamp(date.getTime())))
                .append("game", gameUUID)
                .append("outcome", outcome);

        this.collectionOutcome.insertOne(doc);

        Document myDoc = this.collectionOutcome.find().first();
        System.out.println(myDoc.toJson());
    }

    public long getScore()
    {
        long win = this.collectionOutcome.count(new Document("outcome", 1));
        long loose = this.collectionOutcome.count(new Document("outcome", 0));

        return win/loose;
    }

}
