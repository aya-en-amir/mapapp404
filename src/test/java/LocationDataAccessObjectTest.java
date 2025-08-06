import data_access.LocationDataAccessObject;
import entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

class LocationDataAccessObjectTest {

    private LocationDataAccessObject dao;

    @BeforeEach
    void setUp() {
        dao = new LocationDataAccessObject();
    }

    @Test
    void testSaveAndGetLocation() {
        Location cafe = new Location("Sunset Cafe", 143f, 22.23f, new ArrayList<>(), "184 Sunset Dr, St Thomas, ON N5R 3C3");

        dao.saveLocation(cafe);

        Location retrieved = dao.getLocation("Sunset Cafe");
        assertNotNull(retrieved);
        assertEquals("Sunset Cafe", retrieved.getName());
        assertEquals(cafe, retrieved);
    }

    @Test
    void testGetLocationNotFound() {
        Location result = dao.getLocation("NonExistent");
        assertNull(result);
    }

    @Test
    void testDeleteLocation() {
        Location park = new Location("Trinity Bellwoods Park", 43.647f, -79.403f, new ArrayList<>(), "790 Queen St W, Toronto, ON M6J 1G3");

        dao.saveLocation(park);
        assertNotNull(dao.getLocation("Trinity Bellwoods Park"));

        dao.deleteLocation("Trinity Bellwoods Park");
        assertNull(dao.getLocation("Trinity Bellwoods Park"));
    }

    @Test
    void testGetAllLocations() {
        Location spa = new Location("Stillwater Spa", 43.668f, -79.386f, new ArrayList<>(), "4 Avenue Rd, Toronto, ON M5R 0C6");
        Location restaurant = new Location("House of Gourmet", 43.662f, -79.395f, new ArrayList<>(), "484 Dundas St W, Toronto, ON M5T 1G9");

        dao.saveLocation(spa);
        dao.saveLocation(restaurant);

        List<Location> allLocations = dao.getAllLocations();
        assertEquals(2, allLocations.size());
        assertTrue(allLocations.contains(spa));
        assertTrue(allLocations.contains(restaurant));
    }

    @Test
    void testOverwriteLocationWithSameName() {
        Location bahen1 = new Location("Bahen", 10.122f, 20.12f, new ArrayList<>(), "40 St George St, Toronto, ON M5S 2E4");
        Location bahen2 = new Location("Bahen", 10.122f, 20.12f, new ArrayList<>(), "40 St George St, Toronto, ON M5S 2E4"); // same name, different object

        dao.saveLocation(bahen1);
        dao.saveLocation(bahen2); // should overwrite

        Location result = dao.getLocation("Bahen");
        assertEquals(bahen2, result);
    }

    @Test
    void testEmptyInitialState() {
        assertTrue(dao.getAllLocations().isEmpty());
        assertNull(dao.getCurrentLocationName());
    }
}
