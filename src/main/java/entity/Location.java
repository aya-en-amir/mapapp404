package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a location.
 */
public class Location {
    private String name;
    private float lat;
    private float lon;
    private String address;
    private List<String> reviews = new ArrayList<>();

    public Location(String name, float lat, float lon, List<String> reviews, String address) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.reviews = reviews;
        this.address = address;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final float getLatitude() {
        return lat;
    }

    public final void setLatitude(float latitude) {
        this.lat = latitude;
    }

    public final float getLongitude() {
        return lon;
    }

    public final void setLongitude(float longitude) {
        this.lon = longitude;
    }

    public final void setAddress(String address) {
        this.address = address;
    }

    public final String getAddress() {
        return address;
    }

    public final List<String> getReviews() {
        return reviews;
    }

    public final void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nLat: %f\nLon: %s\nAddress: %s\nReviews: %s\n", name, lat, lon, address,
                reviews.toString());
    }
}
