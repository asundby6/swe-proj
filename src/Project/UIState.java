package Project;

import java.util.HashMap;
import java.util.Map;

public class UIState {

    private static final Map<String, String> titles = new HashMap<>();

    public static String getTitle(String screen) {
        return titles.getOrDefault(screen, "");
    }

    public static void setTitle(String screen, String title) {
        titles.put(screen, title);
    }
}
