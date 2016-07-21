package pl.droidsonroids.charts.util;

import java.util.Iterator;
import java.util.List;
import pl.droidsonroids.charts.model.Item;

/**
 * Created by grzegorzmatyszczak on 19/07/16.
 */

public class ValueValidatorUtil {

    public static boolean isValid(String text) {
        return !text.isEmpty() && Integer.parseInt(text) <= 100;
    }

    public static boolean isValid(final List<Item> items) {
        Iterator<Item> iterator = items.iterator();
        boolean isValid = true;
        while (iterator.hasNext() && isValid) {
            isValid = isValid(iterator.next().getPercentString());
        }
        return isValid;
    }
}
