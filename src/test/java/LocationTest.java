import entity.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LocationTest {

    @Test
    public void testConstructorAndGetters() {
        List<String> bahenReview = new ArrayList<>();
        bahenReview.add("It's the worst library on earth.");
        bahenReview.add("This is not even a library.");
        Location bahen = new Location("Bahen", 43.6532f, -79.3832f, bahenReview, "40 St George St, Toronto, ON M5S 2E4");

        assertEquals("Bahen", bahen.getName());
        assertEquals(43.6532f, bahen.getLatitude());
        assertEquals(-79.3832f, bahen.getLongitude());
        assertEquals("40 St George St, Toronto, ON M5S 2E4", bahen.getAddress());
        assertEquals(bahenReview, bahen.getReviews());
    }

    @Test
    public void testSetters() {
        List<String> cafeReview = new ArrayList<>();
        cafeReview.add("The drinks are amazing. Place was very quiet and nice.");
        Location cafe = new Location("Sunset Cafe", 143f, 22.23f, cafeReview, "184 Sunset Dr, St Thomas, ON N5R 3C3");

        cafe.setName("Updated Name");
        cafe.setLatitude(12.3456f);
        cafe.setLongitude(-65.4321f);
        cafe.setAddress("Updated Address");
        cafe.setReviews(Arrays.asList("Review 1", "Review 2"));

        assertEquals("Updated Name", cafe.getName());
        assertEquals(12.3456f, cafe.getLatitude());
        assertEquals(-65.4321f, cafe.getLongitude());
        assertEquals("Updated Address", cafe.getAddress());
        assertEquals(Arrays.asList("Review 1", "Review 2"), cafe.getReviews());
    }

    @Test
    public void testEmptyReviews() {
        Location park = new Location("Trinity Bellwoods Park", 43.647f, -79.403f, new ArrayList<>(), "790 Queen St W, Toronto, ON M6J 1G3");

        assertNotNull(park.getReviews());
        assertTrue(park.getReviews().isEmpty());
    }

    @Test
    public void testToStringFormat() {
        List<String> bookstoreReview = new ArrayList<>();
        bookstoreReview.add("Cozy bookstore with soft music and comfortable chairs for reading.");
        Location bookstore = new Location("Ben McNally Books", 43.650f, -79.383f, bookstoreReview, "108 Queen St E, Toronto, ON M5C 1S4");

        String toStringOutput = bookstore.toString();
        System.out.println(toStringOutput);

        assertTrue(toStringOutput.contains("Name: Ben McNally Books"));
        assertTrue(toStringOutput.contains("Lat: 43.650"));
        assertTrue(toStringOutput.contains("Lon: -79.383"));
        assertTrue(toStringOutput.contains("Address: 108 Queen St E, Toronto, ON M5C 1S4"));
        assertTrue(toStringOutput.contains("Reviews: [Cozy bookstore with soft music and comfortable chairs for reading.]"));
    }

    @Test
    public void testNullValues() {
        Location location = new Location(null, 0f, 0f, null, null);

        assertNull(location.getName());
        assertEquals(0f, location.getLatitude());
        assertEquals(0f, location.getLongitude());
        assertNull(location.getAddress());
        assertNull(location.getReviews());
    }
}
