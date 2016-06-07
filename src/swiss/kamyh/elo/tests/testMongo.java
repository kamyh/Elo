package swiss.kamyh.elo.tests;

import swiss.kamyh.elo.matchMaking.DbMongo;

/**
 * Created by Vincent on 07.06.2016.
 */
public class TestMongo {
    public void testDummyConnection()
    {
        DbMongo dbMongo = new DbMongo(
                "192.168.99.100",
                27017,
                "elo",
                "elo",
                "elo"
        );
    }

    public static void main (String[] args){
        TestMongo testMongo = new TestMongo();
        testMongo.testDummyConnection();
    }
}
