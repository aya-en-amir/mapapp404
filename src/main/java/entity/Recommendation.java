package entity;

import java.util.List;

/**
 * A class representing a list of recommended locations.
 */

public class Recommendation {
    private Location location;
    private double score;

    public Recommendation(Location location, double score) {
        this.location = location;
        this.score = score;
    }

    public Location getLocation() {
        return location;
    }

    public double getScore() {
        return score;
    }
}
