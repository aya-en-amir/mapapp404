package client_service.DeepSeekClient;

import entity.Location;
import interface_service.VibeExtractorInterface;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.Arrays;
import java.util.List;

public class DeepSeekClient implements LocationGiverInterface {
    private String userPrompt;
    private String API_KEY = "";
    private String endpoint = "";
    private List<Location> location;
    String JSON_FILE_PATH = "test.json";
    int MESSAGE_INDEX = 4;
    int CONTENT_INDEX = 1;

    public DeepSeekClient(String prompt, List<Location> locations) {
        this.userPrompt = prompt;
        Dotenv dotenv = Dotenv.load();
        this.API_KEY = dotenv.get("DEEPSEEK_API_KEY");
        this.endpoint = dotenv.get("DEEPSEEK_ENDPOINT");
        this.location = locations;
    }

    public String getUserPrompt(){

        return userPrompt;
    }

    public String convertToString() {
        StringBuilder locationsStrVer = new StringBuilder();
        for (Location spot : this.location) {
            locationsStrVer.append(spot.getName() + ",");
            locationsStrVer.append(spot.getAddress() + ",");
            locationsStrVer.append(spot.getReviews() + ",");
        }
        return locationsStrVer.toString();
    }

    public String getAPI_KEY(){

        return API_KEY;
    }

    public String getEndpoint(){
        return endpoint;
    }

    @Override
    public ArrayList<String> giveLocations() {
        ArrayList<String> responseList = new ArrayList<>();
        try{
            String requestBody = "{\n" +
                    "          \"model\": \"deepseek/deepseek-chat-v3-0324:free\",\n" +
                    "          \"messages\": [\n" +
                    "            {\"role\": \"user\", " +
                    "            \"content\": " +
                    "            \"Based on this expression:" + getUserPrompt() +
                    "Find me 5 places that best represent what I'm feeling from these places" +
                    convertToString() + "Make sure to give me in this format : {address name : address}"
                    "            \"}\n" +
                    "          ]\n" +
                    "        }";
//            System.out.println("Loaded API Key: " + getAPI_KEY());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(getEndpoint()))
                    .header("Authorization", "Bearer " + getAPI_KEY())
                    .POST(BodyPublishers.ofString(requestBody))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //editing JSON file to see responses (in an organized format)
//            Files.write(Paths.get(JSON_FILE_PATH), response.body().getBytes());
//            System.out.println("test.json edited with the response - check it out!");
            // update responseList
            ArrayList<String> returnList = extractFromJSON(response.body());
            System.out.println("Response is a success! \n" + returnList);
            return returnList;
        }
        catch(IOException e){
            System.out.println("IO Exception: " + e.getMessage());
            // returns empty responseList
            return responseList;
        }

        catch(InterruptedException e){
            System.out.println("Interrupted: " + e.getMessage());
            // returns empty responseList
            return responseList;
        }

    }

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
