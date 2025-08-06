package data_access;

import entity.Location;
import entity.User;
import interface_service.LocationDataAccessInterface;
import use_case.login.login.LoginUserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDataAccessObject implements LoginUserDataAccessInterface, LocationDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Location> locations = new HashMap<>();
    private String currentUsername;
    private String currentLocation;

    @Override
    public void saveUser(User user) {
        users.put(user.getUserName(), user);
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void deleteUser(String username) {
        users.remove(username);
    }

    @Override
    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

    @Override
    public boolean existsByName(String username) {
        return users.containsKey(username);
    }

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
    public void setCurrentLocationName(String postalCode) {
        this.currentLocation = postalCode;
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> result = new ArrayList<>();
        for (Location location : locations.values()) {
            result.add(location);
        }
        return result;
    }
}
