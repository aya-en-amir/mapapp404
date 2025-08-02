import client_service.LocationGiver.LocationGiver;
import entity.Location;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationGiverTest {
    @Test
    public void LocationGiverTest() {
        String prompt = "I need a place to relax";

        List<String> bahenReview = new ArrayList<>();
        bahenReview.addAll(Arrays.asList("It's", "the", "worst", "library", "on", "earth"));
        Location location1 = new Location("Bahen", 10.122f, 20.12f, bahenReview);

        List<String> cafeReview = new ArrayList<>();
        cafeReview.addAll(Arrays.asList("The", "drinks", "are", "amazing.", "Place", "was", "very", "quiet", "and", "nice"));
        Location location2 = new Location("Cafe", 143f, 22.23f, cafeReview);

        List<String> parkReview = new ArrayList<>();
        parkReview.addAll(Arrays.asList("Peaceful", "greenery", "and", "a", "perfect", "spot", "to", "read", "or", "nap", "under", "trees"));
        Location location3 = new Location("Trinity Bellwoods Park", 43.647f, -79.403f, parkReview);

        List<String> bookstoreReview = new ArrayList<>();
        bookstoreReview.addAll(Arrays.asList("Cozy", "bookstore", "with", "soft", "music", "and", "comfortable", "chairs", "for", "reading"));
        Location location4 = new Location("Ben McNally Books", 43.650f, -79.380f, bookstoreReview);

        List<String> spaReview = new ArrayList<>();
        spaReview.addAll(Arrays.asList("Very", "relaxing", "experience", "with", "soothing", "music", "and", "great", "service"));
        Location location5 = new Location("Stillwater Spa", 43.668f, -79.386f, spaReview);

        List<String> houseofGourmet = new ArrayList<>();
        cafeReview.addAll(Arrays.asList("Cozy", "ambience", "and", "delicious", "coffee", "with", "friendly", "staff"));
        Location location6 = new Location("Sunset Cafe", 43.662f, -79.395f, houseofGourmet);


        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        locations.add(location4);
        locations.add(location5);
        locations.add(location6);

        LocationGiver locationGiver = new LocationGiver(prompt, locations);
        System.out.println(locationGiver.giveLocation());
    }
}

