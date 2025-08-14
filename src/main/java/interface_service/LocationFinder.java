package interface_service;

import java.util.List;

import entity.Location;

/**
 * Service to find nearby locations.
 */

public interface LocationFinder {
    /**
     * Finds nearby locations given a postal code.
     *
     * @param postalCode the postal code of the user
     * @return a list of Location objects
     * @throws Exception if locations cannot be generated
     */
    List<Location> serveLocations(String postalCode) throws Exception;
}
