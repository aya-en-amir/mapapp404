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
    private String address;
    private List<String> reviews = new ArrayList<>();

    public Location(String name, float lat, float lon, String address, List<String> reviews) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
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

    public void setAddress(String address) {this.address = address;}

    public String getAddress() {return address;}

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nLat: %f\nLon: %f\nReviews: %s\n", name, lat, lon, reviews.toString());
    }
}