package interface_service;

import java.util.ArrayList;

public interface RecommendLocation {
    ArrayList<String> giveLocations();

    ArrayList<String> extractFromJson(String jsonResponse);
}
