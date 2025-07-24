package client_service.Recommendation;

import client_service.DeepSeekClient.DeepSeekClient;
import client_service.ReviewMatcher.ReviewMatcher;
import entity.Location;
import entity.Recommendation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecommendationService {
    private DeepSeekClient deepSeekClient;

    public RecommendationService(DeepSeekClient deepSeekClient) {
        if (deepSeekClient == null) {
            throw new IllegalArgumentException("DeepSeekClient cannot be null");
        }
        this.deepSeekClient = deepSeekClient;
    }

    public Recommendation recommend (List<Location> allLocations, int topN) {
        if (allLocations == null || allLocations.isEmpty()) {
            return new Recommendation(Collections.emptyList());
        }

        // 1. Extract keywords from prompt
        List<String> keywords = deepSeekClient.extractKeywords();
        if(keywords.isEmpty()){
            System.out.println("No keywords found");
            return new Recommendation(Collections.emptyList());
        }

        // 2. Score all locations (no filtering)
        Map<Location, Double> scoredLocations = new HashMap<>();
        for (Location location : allLocations) {
            ReviewMatcher reviewMatcher = new ReviewMatcher(keywords, location);
            double score = reviewMatcher.calculateScore();
            scoredLocations.put(location, score);
        }

        // 3. Top N sorted by score
        List<Location> recommended = scoredLocations.entrySet().stream()
                .sorted(Map.Entry.<Location, Double>comparingByValue().reversed())
                .limit(topN)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return new Recommendation(recommended);
    }
}
