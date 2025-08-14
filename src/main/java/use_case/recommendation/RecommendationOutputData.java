package use_case.recommendation;

import java.util.List;

import entity.Recommendation;

public class RecommendationOutputData {
    private List<Recommendation> recommendations;

    public RecommendationOutputData(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public final List<Recommendation> getRecoLocations() {
        return recommendations;
    }
}
