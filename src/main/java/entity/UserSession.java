package entity;

import java.util.UUID;

/**
 * A class of UserSession
 */

public class UserSession {
    UUID sessionID;
    private User currentUser;
    private String userPrompt;
    private List<String> extractedKeywords;
    private List<Location> matchedLocations;
    private List<Recommendation> recommendations;

    public UserSession(User currentUser, String userPrompt, List<String> extractedKeywords, List<Location> matchedLocations, List<Recommendation> recommendations) {
        this.currentUser = currentUser;
        this.userPrompt = userPrompt;
        this.extractedKeywords = extractedKeywords;
        this.matchedLocations = matchedLocations;
        this.recommendations = recommendations;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getUserPrompt() {
        return userPrompt;
    }

    public void setUserPrompt(String userPrompt) {
        this.userPrompt = userPrompt;
    }

    public List<String> getExtractedKeywords() {
        return extractedKeywords;
    }

    public void setExtractedKeywords(List<String> extractedKeywords) {
        this.extractedKeywords = extractedKeywords;
    }

    public List<Location> getMatchedLocations() {
        return matchedLocations;
    }

    public void setMatchedLocations(List<Location> matchedLocations) {
        this.matchedLocations = matchedLocations;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}