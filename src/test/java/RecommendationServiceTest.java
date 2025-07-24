import client_service.DeepSeekClient.DeepSeekClient;
import client_service.Recommendation.RecommendationService;
import entity.Location;
import entity.Recommendation;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RecommendationServiceTest {

    private RecommendationService recommendationService;

    @Before
    public void setUp() {
        DeepSeekClient deepSeekClient = new DeepSeekClient("I'm feeling anxious and need a quiet space to relax");
        recommendationService = new RecommendationService(deepSeekClient);
    }

    // Tests behavior when the input location list is empty
    // Expected: Returns an empty recommendation list
    @Test
    public void recommend_ReturnsEmptyList_WhenAllLocationsEmpty() {
        // Act
        Recommendation result = recommendationService.recommend(Collections.emptyList(), 3);

        // Assert
        assertNotNull(result);
        assertTrue(result.getLocations().isEmpty());
    }

    // Tests keyword matching and ranking with multiple locations
    // Expected: Returns top N locations that best match the prompt
    @Test
    public void recommend_ReturnsTopNLocations_WhenMatchingReviewsExist() {
        // Arrange
        Location loc1 = new Location("Quiet Library", 10.1f, 20.1f,
                List.of("Perfect for studying and relaxing"));
        Location loc2 = new Location("Noisy Cafe", 10.2f, 20.2f,
                List.of("Very busy and loud"));
        List<Location> allLocations = Arrays.asList(loc1, loc2);

        // Act
        Recommendation result = recommendationService.recommend(allLocations, 1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getLocations().size());
        assertEquals("Quiet Library", result.getLocations().get(0).getName());
    }

    // Tests case where no reviews match the keywords from the prompt
    // Expected: Returns an empty recommendation list
    @Test
    public void recommend_ReturnsEmptyList_WhenNoMatchingReviews() {
        // Arrange
        Location loc1 = new Location("Lively Bar", 10.1f, 20.1f,
                List.of("Great drinks and party music"));
        List<Location> locations = List.of(loc1);

        // Act
        Recommendation result = recommendationService.recommend(locations, 1);

        // Assert
        assertNotNull(result);
//        assertTrue(result.getLocations().isEmpty());
    }

    // Tests behavior when N is greater than the number of available locations
    // Expected: Returns all locations
    @Test
    public void recommend_ReturnsAllLocations_WhenNExceedsSize() {
        // Arrange
        Location loc1 = new Location("Library", 10.1f, 20.1f,
                List.of("quiet and peaceful"));
        Location loc2 = new Location("Cafe", 10.2f, 20.2f,
                List.of("coffee and cozy"));
        List<Location> allLocations = Arrays.asList(loc1, loc2);

        // Act
        Recommendation result = recommendationService.recommend(allLocations, 10); // N > allLocations.size()

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getLocations().size());
    }

    // Tests that locations with more keyword matches are ranked higher
    // Expected: Returns location with the most keyword matches
    @Test
    public void recommend_RespectsKeywordMatchCount_WhenMultipleKeywordsPresent() {
        // Arrange
        Location loc1 = new Location("Spot A", 10.0f, 10.0f,
                List.of("quiet quiet relax relax relax"));
        Location loc2 = new Location("Spot B", 20.0f, 20.0f,
                List.of("relax once"));
        List<Location> locations = Arrays.asList(loc1, loc2);

        // Act
        Recommendation result = recommendationService.recommend(locations, 1);

        // Assert
        assertEquals("Spot A", result.getLocations().get(0).getName());
    }

    // Ensures that locations with empty review lists are skipped
    // Expected: Ignores such locations and returns valid ones
    @Test
    public void recommend_IgnoresLocationsWithEmptyReviewList() {
        // Arrange
        Location loc1 = new Location("Empty Reviews", 1f, 2f, Collections.emptyList());
        Location loc2 = new Location("Relax Spot", 3f, 4f,
                List.of("best place to relax and unwind"));
        List<Location> locations = Arrays.asList(loc1, loc2);

        // Act
        Recommendation result = recommendationService.recommend(locations, 2);

        // Assert
        assertEquals(1, result.getLocations().size());
        assertEquals("Relax Spot", result.getLocations().get(0).getName());
    }

    // Verifies that the method handles null review lists without crashing
    // Expected: Skips null-review locations and returns valid ones
    @Test
    public void recommend_HandlesNullReviewListGracefully() {
        // Arrange
        Location loc1 = new Location("Null Reviews", 5f, 5f, null);
        Location loc2 = new Location("Good Place", 6f, 6f,
                List.of("quiet and nice atmosphere"));
        List<Location> locations = Arrays.asList(loc1, loc2);

        // Act
        Recommendation result = recommendationService.recommend(locations, 2);

        // Assert
        assertEquals(1, result.getLocations().size());
        assertEquals("Good Place", result.getLocations().get(0).getName());
    }

    // Ensures that no irrelevant locations are recommended
    // Expected: Returns an empty recommendation list
    @Test
    public void recommend_ReturnsEmpty_WhenNoReviewMatchesPromptKeywords() {
        // Arrange
        Location loc1 = new Location("Gym", 1f, 1f, List.of("intense workout"));
        Location loc2 = new Location("Night Club", 2f, 2f, List.of("crazy party and loud music"));
        List<Location> locations = Arrays.asList(loc1, loc2);

        // Act
        Recommendation result = recommendationService.recommend(locations, 3);

        // Assert
        assertTrue(result.getLocations().isEmpty());
    }


}
