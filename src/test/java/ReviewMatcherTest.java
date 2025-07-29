import client_service.ReviewMatcher.ReviewMatcher;
import org.junit.Test;

import entity.Location;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ReviewMatcherTest {
    @Test
    public void successTest() {
        List<String> bahenReview = new ArrayList<>();
        bahenReview.addAll(Arrays.asList("It's", "a", "tasty", "place", "The", "food", "needs", "more", "salt"));
        Location location = new Location("Bahen", 10.122f, 20.12f, bahenReview);

        List<String> keyword = new ArrayList<>();
        keyword.addAll(Arrays.asList("delicious", "yummy"));
        ReviewMatcher reviewMatcher = new ReviewMatcher(keyword, location);
        double score = reviewMatcher.calculateScoreWordnet();
        System.out.println(score);
        System.out.println(reviewMatcher.calculateScore());
    }
    @Test
    public void getTest() {
        List<String> bahenReview = new ArrayList<>();
        bahenReview.addAll(Arrays.asList("It's", "a", "depressing", "place"));
        Location location = new Location("Bahen", 10.122f, 20.12f, bahenReview);

        List<String> keyword = new ArrayList<>();
        keyword.addAll(Arrays.asList("awesome", "great"));
        ReviewMatcher reviewMatcher = new ReviewMatcher(keyword, location);
        List<Double> score = reviewMatcher.calculateScoreTest();
        System.out.println(score);
    }
}

