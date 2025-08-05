package entity;

import java.util.List;

/**
 * A class representing a list of recommended locations.
 */

public class Recommendation {
    private List<Location> locations;

    public Recommendation(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> getLocations() { return locations; }

}
