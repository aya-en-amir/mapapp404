package main.interface_service;

import entity.Location;

import java.util.List;

/**
 * Service to find nearby locations.
 */
public interface LocationFinder {
    /**
     * Finds nearby locations given a postal code.
     *
     * @param postalCode the postal code of the user
     */
    List<Location> serveLocations(String postalCode) throws Exception;
}