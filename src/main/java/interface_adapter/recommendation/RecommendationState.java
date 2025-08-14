package interface_adapter.recommendation;

import java.util.ArrayList;
import java.util.List;

import entity.Recommendation;

public class RecommendationState {
    private List<Recommendation> recommendations = new ArrayList<>();
    private String errorMessage = "";

    public final List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public final String getErrorMessage() {
        return errorMessage;
    }

    public final void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public final void setRecommendation(List<Recommendation> recs) {
        this.recommendations = recs;
    }

}
