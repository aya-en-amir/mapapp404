package interface_service;

import java.util.ArrayList;

public interface LocationGiverInterface {
    ArrayList<String> giveLocation();

    ArrayList<String> extractFromJSON(String jsonResponse);
}
