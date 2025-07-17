package entity;

import java.util.List;
import java.util.ArrayList;

/**
 * A class representing a location.
 */
public class Location {
    private String name;
    private float lat;
    private float lon;
    private List<String> reviews = new ArrayList<>();

    public Location(String name, float lat, float lon, List<String> reviews) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return lat;
    }

    public void setLatitude(float lat) {
        this.lat = lat;
    }

    public float getLongitude() {
        return lon;
    }

    public void setLongitude(float lon) {
        this.lon = lon;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}