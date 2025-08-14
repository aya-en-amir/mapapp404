package usecase.recommendation;

import java.util.List;

import entity.Location;

public interface RecommendationDataAccessInterface {
    /**
     * Save a specific location.
     * @param location the location that wanted to be saved
     */
    void saveLocation(Location location);

    /**
     * Get a specific location from the location's name.
     * @param name the name of the location we want
     * @return the Location object given the name of the location
     */
    Location getLocation(String name);

    /**
     * Delete the location's information from their name.
     * @param name the name of the location we want to delete
     */
    void deleteLocation(String name);

    /**
     * Returns the name of the current location.
     * @return the name
     */
    String getCurrentLocationName();

    /**
     * Setting the current lcoation.
     * @param location the name of the current location
     */
    void setCurrentLocationName(String location);

    /**
     * Get all locations.
     * @return the List of all Location objects
     */
    List<Location> getAllLocations();
}
