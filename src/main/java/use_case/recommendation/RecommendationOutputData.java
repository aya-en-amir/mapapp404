package use_case.recommendation;

import entity.Location;

import java.util.List;

public class RecommendationOutputData {
    private List<Location> recoLocations;

    public RecommendationOutputData(List<Location> recoLocations) {
        this.recoLocations = recoLocations;
    }

    public List<Location> getRecoLocations() {
        return recoLocations;
    }
}
