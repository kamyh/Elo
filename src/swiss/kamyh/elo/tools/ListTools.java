package swiss.kamyh.elo.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Vincent on 07.06.2016.
 */
public class ListTools {

    public static List<?> flatten(List<?> source) {
        List<?> currentSource = source;
        List<Object> flattenedList = new ArrayList<Object>();
        boolean loop = true;
        while (loop) {
            loop = false;
            for (Object item : currentSource) {
                if (item instanceof Collection<?>) {
                    flattenedList.addAll((Collection<?>) item);
                    loop = true;
                } else {
                    flattenedList.add(item);
                }
            }
            if (loop) {
                currentSource = flattenedList;
                flattenedList = new ArrayList<Object>();
            }
        }

        return flattenedList;
    }
}
