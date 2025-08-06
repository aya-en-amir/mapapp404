package interface_service;

import entity.Location;

import java.util.List;

public interface LocationDataAccessInterface {
    /**
     * Save a specific location.
     * @param location the location that wanted to be saved
     */
    void saveLocation(Location location);

    /**
     * Get a specific location from the location's name.
     * @param name the name of the location we want
     */
    Location getLocation(String name);

    /**
     * Delete the location's information from their name.
     * @param name the name of the location we want to delete
     */
    void deleteLocation(String name);

    /**
     * Returns the name of the current location.
     */
    String getCurrentLocationName();

    /**
     * Get all locations.
     */
    List<Location> getAllLocations();
}
