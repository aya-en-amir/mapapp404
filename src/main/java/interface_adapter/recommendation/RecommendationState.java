package interface_adapter.recommendation;

import entity.Recommendation;

import java.util.ArrayList;
import java.util.List;

public class RecommendationState {
    private List<Recommendation> recommendations = new ArrayList<>();
    private String errorMessage = "";

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setRecommendation(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

}
