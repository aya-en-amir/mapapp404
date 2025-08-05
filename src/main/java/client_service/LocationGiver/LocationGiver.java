package client_service.LocationGiver;

import entity.Location;
import interface_service.LocationGiverInterface;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationGiver implements LocationGiverInterface {
    private String userPrompt;
    private String API_KEY = "";
    private String endpoint = "";
    private List<Location> locations;

    public LocationGiver(String prompt, List<Location> locations) {
        this.userPrompt = prompt;
        Dotenv dotenv = Dotenv.load();
        this.API_KEY = dotenv.get("DEEPSEEK_API_KEY");
        this.endpoint = dotenv.get("DEEPSEEK_ENDPOINT");
        this.locations = locations;
    }

    public String getUserPrompt(){
        return userPrompt;
    }

    public String getAPI_KEY(){
        return API_KEY;
    }

    public String getEndpoint(){
        return endpoint;
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
    public ArrayList<String> giveLocation() {
        ArrayList<String> locationGiver = new ArrayList<>();
        try{
            String request = "{\n" +
                    "          \"model\": \"deepseek/deepseek-chat-v3-0324:free\",\n" +
                    "          \"messages\": [\n" +
                    "            {\"role\": \"user\", " +
                    "            \"content\": " +
                    "            \"User prompt: " + "{" + getUserPrompt() + "}" +
                    "               Find 5 suitable places that matches the user's prompt " +
                    "               from these locations: " + "{" + convertLocToString() + "}" +
                    "               The generated list of location must follow this following template " +
                    "               (i.e 1. <insert location 1> ...) " +
                    "               Structure should follow this template EXACTLY and include NOTHING ELSE so " +
                    "               I can parse the words in the curly braces: " +
                    "               {<generated word 1>, ... <generated word n>, }" +
                    "            \"}\n" +
                    "          ]\n" +
                    "        }";
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(getEndpoint()))
                    .header("Authorization", "Bearer " + getAPI_KEY())
                    .POST(BodyPublishers.ofString(request))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ArrayList<String> returnList = extractFromJSON(response.body());
            System.out.println("Response is a success! \n" + returnList);
            return returnList;
        }
        catch(IOException e){
            System.out.println("IO Exception: " + e.getMessage());
            return locationGiver;
            // returns empty responseList
        }

        catch(InterruptedException e){
            System.out.println("Interrupted: " + e.getMessage());
            return locationGiver;
            // returns empty responseList
        }

    }

    @Override
    public ArrayList<String> extractFromJSON(String jsonResponse){
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject message = ((JSONObject)choices.get(0)).getJSONObject("message");
        String content = message.getString("content");


        int startCurly = content.indexOf("{");
        int endCurly = content.indexOf("}");
        String response = content.substring(startCurly+1, endCurly);
        ArrayList<String> responseList = new ArrayList<>(Arrays.asList(response.split(", ")));
        return responseList;
    }
}