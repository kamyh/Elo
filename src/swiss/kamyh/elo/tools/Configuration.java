package swiss.kamyh.elo.tools;

/**
 * Created by Vincent on 07.06.2016.
 * TODO: config.json
 */
public class Configuration {
    public static int[] spawn_1 = new int[]{5, 5, 0};
    public static int[] spawn_2 = new int[]{-5, 5, 0};

    public static int basePartyTime = 120;
    public static int timeModification = 30;

    public static int selectionPhase = 180;

    public static int maxBlocks = 21;
    public static int maxSameBlocks = 15;
    public static int maxCompos = 5;
    public static int maxGoldenApple = 5;

    public static float speedWalk = 0.4F;
}
