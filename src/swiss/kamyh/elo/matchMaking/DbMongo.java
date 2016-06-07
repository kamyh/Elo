package swiss.kamyh.elo.matchMaking;


import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.*;

import static java.time.OffsetTime.now;

/**
 * Created by Vincent on 07.06.2016.
 */
public class DbMongo {
    private MongoClient mongoClient;
    protected MongoDatabase db;
    private String user;
    private String database;
    private String password;
    private String host;
    private int port;

    public DbMongo(String host, int port, String user, String database, String password) {
        this.user = user;
        this.database = database;
        this.password = password;
        this.port = port;
        this.host = host;

        List<ServerAddress> seeds = new ArrayList<>();
        seeds.add(new ServerAddress(this.host, this.port));
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(
                MongoCredential.createCredential(
                        this.user,
                        this.database,
                        this.password.toCharArray()
                )
        );
        mongoClient = new MongoClient(seeds, credentials);
        this.db = this.mongoClient.getDatabase(this.database);
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }



    public void displayAllFrom(String collectionName)
    {
        MongoCollection<Document> collection = this.db.getCollection(collectionName);
        for (Document cur : collection.find()) {
            System.out.println(cur.toJson());
        }
    }


}
