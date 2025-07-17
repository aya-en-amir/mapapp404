package client_service.ReviewMatcher;

import java.entity.Location;

import java.util.List;

public class ReviewMatcher {
    private List<String> keywords;
    private Location location;

    public ReviewMatcher(List<String> keywords, Location location) {
        this.keywords = keywords;
        this.location = location;
    }
    public double calculateScore() {
        double totalScore = 0.0;

        for (String word : keywords) {
            for (String locationReview : location.getReviews()) {
                CosineCalculator calculator = new CosineCalculator(word, locationReview);
                totalScore += calculator.calculate();
            }
        }
        return totalScore / keywords.size();
    }
}
