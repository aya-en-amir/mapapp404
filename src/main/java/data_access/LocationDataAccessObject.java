package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Location;
import interface_service.LocationDataAccessInterface;

public class LocationDataAccessObject implements LocationDataAccessInterface {
    private final Map<String, Location> locations = new HashMap<>();
    private String currentLocation;

    @Override
    public void saveLocation(Location location) {
        locations.put(location.getName(), location);
    }

    @Override
    public Location getLocation(String locationName) {
        return locations.get(locationName);

    }

    @Override
    public void deleteLocation(String locationName) {
        locations.remove(locationName);
    }

    @Override
    public String getCurrentLocationName() {
        return currentLocation;
    }

    @Override
    public void setCurrentLocationName(String currentLocationName) {
        this.currentLocation = currentLocationName;
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> result = new ArrayList<>();
        for (String locationName : locations.keySet()) {
            result.add(locations.get(locationName));
        }
        return result;
    }
}
