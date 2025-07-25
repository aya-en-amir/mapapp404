package client_service.ReviewMatcher;

import java.util.List;

import entity.Location;

public class ReviewMatcher {
    private List<String> keywords;
    private Location location;

    public ReviewMatcher(List<String> keywords, Location location) {
        this.keywords = keywords;
        this.location = location;
    }

    public double calculateScore() {
        double totalScore = 0.0;

        if (location.getReviews().isEmpty()) {
            return totalScore;
        }
        for (String word : keywords) {
            for (String locationReview : location.getReviews()) {
                CosineCalculator calculator = new CosineCalculator(word.toLowerCase(), locationReview.toLowerCase());
                totalScore += calculator.calculate();
            }
        }
        return (totalScore / keywords.size()) * 100;
    }

}
