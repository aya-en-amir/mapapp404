

import entity.Location;
import entity.Recommendation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecommendationTest {

    @Test
    public void testConstructorAndGetter() {
        List<String> spaReview = new ArrayList<>();
        spaReview.add("Very relaxing experience with soothing music and great service.");
        Location spa = new Location("Stillwater Spa", 43.668f, -79.386f, spaReview, "4 Avenue Rd, Toronto, ON M5R 0C6");

        List<String> houseofGourmetReview = new ArrayList<>();
        houseofGourmetReview.add("Cozy ambience and delicious coffee with friendly staff.");
        houseofGourmetReview.add("I love this place.");
        Location restaurant = new Location("House of Gourmet", 43.662f, -79.395f, houseofGourmetReview, "484 Dundas St W, Toronto, ON M5T 1G9");

        List<Location> locations = Arrays.asList(spa, restaurant);
        Recommendation recommendation = new Recommendation(locations);

        assertEquals(2, recommendation.getLocations().size());
        assertEquals("Stillwater Spa", recommendation.getLocations().get(0).getName());
        assertEquals("House of Gourmet", recommendation.getLocations().get(1).getName());
    }

    @Test
    public void testEmptyLocationsList() {
        Recommendation recommendation = new Recommendation(Collections.emptyList());

        assertNotNull(recommendation.getLocations());
        assertTrue(recommendation.getLocations().isEmpty());
    }

    @Test
    public void testNullLocationsList() {
        Recommendation recommendation = new Recommendation(null);

        assertNull(recommendation.getLocations());
    }
}
