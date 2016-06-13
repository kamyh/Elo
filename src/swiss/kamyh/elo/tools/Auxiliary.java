package swiss.kamyh.elo.tools;

import java.util.Random;

/**
 * Created by Vincent on 13.06.2016.
 */
public class Auxiliary {

    public static String[] getRandomName() {
        Random r = new Random();
        String[] str = new String[2];

        System.out.println(Auxiliary.names.length);

        int index = (int) (r.nextInt(Integer.SIZE - 1) % (Auxiliary.names.length));
        System.out.println(index);
        str[0] = Auxiliary.names[index];

        index = (int) (r.nextInt(Integer.SIZE - 1) % Auxiliary.names.length);
        str[1] = Auxiliary.names[index];

        while (str[0] == str[1]) {
            index = (int) (r.nextInt(Integer.SIZE - 1) % Auxiliary.names.length );
            str[1] = Auxiliary.names[index];
        }

        return str;
    }

    public static String[] names = {
            "Andabatae",
            "Arbelas",
            "Bestiarius",
            "Bustuarius",
            "Cestus",
            "Crupellarii",
            "Dimachaerus",
            "Equites",
            "Essedarius",
            "Hoplomachus",
            "Gladiatrix",
            "Laquearius",
            "Murmillo",
            "Parmularius",
            "Provocator",
            "Retiarius",
            "Rudiarius",
            "Sagittarius",
            "Samnite",
            "Scissor",
            "Scutarius",
            "Secutor",
            "Tertiarius",
            "Thraex",
            "Velites"
    };
}
