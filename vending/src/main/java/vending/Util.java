package vending;

import java.util.Arrays;
import java.util.List;

public class Util {

    public static String formatAmount(int value) {
        float floatValue = (float) value / 100;
        return String.format("%2.2f", floatValue);
    }

    @SafeVarargs()
    public static <T>  List<T> makeList(T...elements) {
        return Arrays.asList(elements);
    }
}
