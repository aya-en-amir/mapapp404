package client_service.ReviewMatcher;

import java.util.HashMap;
import java.util.Map;


public class ConverterMap {
    public static Map<CharSequence, Integer> converter(String input) {
        Map<CharSequence, Integer> map = new HashMap<>();
        map.put(input, input.length());
        return map;

    }
}
