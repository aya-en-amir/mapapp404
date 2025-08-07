package interface_adapter.recommendation;

import entity.Location;

import java.util.ArrayList;
import java.util.List;

public class RecommendationState {
    private List<Location> recoLocations = new ArrayList<>();
    private String errorMessage = "";

    public List<Location> getRecoLocations() {
        return recoLocations;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setRecoLocations(List<Location> recoLocations) {
        this.recoLocations = recoLocations;
    }

}
