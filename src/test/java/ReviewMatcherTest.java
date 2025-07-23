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
        bahenReview.addAll(Arrays.asList("awesome", "great"));
        Location location = new Location("Bahen", 10.122f, 20.12f, bahenReview);

        List<String> keyword = new ArrayList<>();
        keyword.addAll(Arrays.asList("awesome", "devastating", "Great"));
        ReviewMatcher reviewMatcher = new ReviewMatcher(keyword, location);
        double score = reviewMatcher.calculateScore();
        assertTrue(score > 0);
        System.out.println(score);

    }
}

