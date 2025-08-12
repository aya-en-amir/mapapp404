package use_case.recommendation;

import entity.Location;
import entity.Recommendation;

import java.util.List;

public class RecommendationOutputData {
    private List<Recommendation> recommendations;

    public RecommendationOutputData(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public List<Recommendation> getRecoLocations() {
        return recommendations;
    }
}
