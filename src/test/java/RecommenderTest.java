
import client_service.recommendation.Recommender;
import client_service.api.DeepSeekClient;
import entity.Location;
import interface_service.LLMClient;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RecommenderTest {
    @Test
    public void RecommenderTest() {
        String prompt = "I need a place to relax";

        List<String> bahenReview = new ArrayList<>();
        bahenReview.add("It's the worst library on earth.");
        bahenReview.add("This is not even a library.");
        Location location1 = new Location("Bahen", 10.122f, 20.12f, bahenReview, "40 St George St, Toronto, ON M5S 2E4");

        List<String> cafeReview = new ArrayList<>();
        cafeReview.add("The drinks are amazing. Place was very quiet and nice.");
        Location location2 = new Location("Sunset Cafe", 143f, 22.23f, cafeReview, "184 Sunset Dr, St Thomas, ON N5R 3C3");

        List<String> parkReview = new ArrayList<>();
        parkReview.add("Peaceful greenery and a perfect spot to read or nap under trees.");
        Location location3 = new Location("Trinity Bellwoods Park", 43.647f, -79.403f, parkReview, "790 Queen St W, Toronto, ON M6J 1G3");

        List<String> bookstoreReview = new ArrayList<>();
        bookstoreReview.add("Cozy bookstore with soft music and comfortable chairs for reading.");
        Location location4 = new Location("Ben McNally Books", 43.650f, -79.380f, bookstoreReview, "108 Queen St E, Toronto, ON M5C 1S4");

        List<String> spaReview = new ArrayList<>();
        spaReview.add("Very relaxing experience with soothing music and great service.");
        Location location5 = new Location("Stillwater Spa", 43.668f, -79.386f, spaReview, "4 Avenue Rd, Toronto, ON M5R 0C6");

        List<String> houseofGourmetReview = new ArrayList<>();
        houseofGourmetReview.add("Cozy ambience and delicious coffee with friendly staff.");
        houseofGourmetReview.add("I love this place.");
        Location location6 = new Location("House of Gourmet", 43.662f, -79.395f, houseofGourmetReview, "484 Dundas St W, Toronto, ON M5T 1G9");


        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        locations.add(location4);
        locations.add(location5);
        locations.add(location6);

        LLMClient DeepSeekClient = new DeepSeekClient();
        Recommender locationGiver = new Recommender(prompt, locations, DeepSeekClient);
        System.out.println("Recommended location objects: " + locationGiver.recommend());
    }
}
