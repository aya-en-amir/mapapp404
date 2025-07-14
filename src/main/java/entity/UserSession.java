package entity;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

/**
 * A class representing a single session of a user's interaction with the system.
 */
public class UserSession {
    private UUID sessionID;
    private User currentUser;
    private String userPrompt;
    private List<String> extractedKeywords = new ArrayList<>();
    private List<Location> matchedLocations;
    private List<Recommendation> recommendations;

    public UserSession(User currentUser) {
        this.sessionID = UUID.randomUUID();
        this.currentUser = currentUser;
    }

    public UUID getSessionID() {
        return sessionID;
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