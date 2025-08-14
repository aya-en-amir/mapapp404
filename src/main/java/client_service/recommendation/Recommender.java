package client_service.recommendation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Location;
import entity.Recommendation;
import interface_service.LlmClient;
import interface_service.RecommenderInterface;

public class Recommender implements RecommenderInterface {
    private String userPrompt;
    private LlmClient client;
    private List<Location> locations;

    public Recommender(String prompt, List<Location> locations, LlmClient client) {
        this.userPrompt = prompt;
        this.client = client;
        this.locations = locations;
    }

    public final String getUserPrompt() {
        return userPrompt;
    }

    public final List<Location> getLocations() {
        return locations;
    }

    /**
     * Converts all stored locations to a single string.
     *
     * @return a comma-separated string of locations
     */
    public final String convertLocToString() {
        StringBuilder sb = new StringBuilder();
        for (Location location : locations) {
            sb.append(", ");
            sb.append(location.toString());
        }
        return sb.toString();
    }

    @Override
    public Recommendation recommend() {
        String prompt = "You are helping a user determine where they should go depending on their mood. Here is the "
                + "user prompt: " + getUserPrompt() + ". Find 5 suitable places that matches the user's prompt out of "
                + "these locations: {" + convertLocToString() + "}. The generated list of location must follow this "
                + "following template (i.e 1. <Name attribute of location 1> \n 2. <Name attribute of location 2>)...)"
                + " Structure should follow this template EXACTLY and include NOTHING ELSE.";
        String response = client.getLlmResponse(prompt);
        Recommendation recommendation = new Recommendation(parseLlmResponse(response));
        return recommendation;
    }

    /**
     * Parses an LLM response to match numbered location names with stored locations.
     *
     * @param llmResponse the raw LLM response text
     * @return a list of matching {@link Location} objects
     */
    public final List<Location> parseLlmResponse(String llmResponse) {
        List<Location> result = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d+\\.\\s*(.+)");
        Matcher matcher = pattern.matcher(llmResponse);

        List<String> extractedNames = new ArrayList<>();
        while (matcher.find()) {
            extractedNames.add(matcher.group(1).trim());
        }

        for (String name : extractedNames) {
            for (Location loc : locations) {
                if (loc.getName().equalsIgnoreCase(name)) {
                    result.add(loc);
                    break;
                }
            }
        }
        return result;
    }
}
