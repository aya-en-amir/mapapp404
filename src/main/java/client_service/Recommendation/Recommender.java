package client_service.Recommendation;

import entity.Location;
import entity.Recommendation;
import interface_service.LLMClient;
import interface_service.RecommenderInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recommender implements RecommenderInterface {
    private String userPrompt;
    private LLMClient client;
    private List<Location> locations;

    public Recommender(String prompt, List<Location> locations, LLMClient client) {
        this.userPrompt = prompt;
        this.client = client;
        this.locations = locations;
    }

    public String getUserPrompt(){
        return userPrompt;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public String convertLocToString() {
        StringBuilder sb = new StringBuilder();
        for (Location location : locations) {
            sb.append(", ");
            sb.append(location.toString());
        }
        return sb.toString();
    }

    @Override
    public Recommendation recommend() {
        String prompt = "You are helping a user determine where they should go depending on their mood. Here " +
                "               is the user prompt: " + getUserPrompt() + ". " +
                "               Find 5 suitable places that matches the user's prompt out of these locations: " +
                "               {" + convertLocToString() + "}. " +
                "               The generated list of location must follow this following template " +
                "               (i.e 1. <Name attribute of location 1> \n 2. <Name attribute of location 2>)...) " +
                "               Structure should follow this template EXACTLY and include NOTHING ELSE.";
        String response = client.getLLMResponse(prompt);
        Recommendation recommendation = new Recommendation(parseLLMResponse(response));
        return recommendation;
    }

    public List<Location> parseLLMResponse(String llmResponse) {
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