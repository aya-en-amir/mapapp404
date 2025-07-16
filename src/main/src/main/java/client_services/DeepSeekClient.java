package client_services;


import interface_services.VibeExtractorInterface;
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

public class DeepSeekClient implements VibeExtractorInterface {
    private String userPrompt;
    private String API_KEY = "";
    private String endpoint = "";
    String JSON_FILE_PATH = "test.json";
    int MESSAGE_INDEX = 4;
    int CONTENT_INDEX = 1;

    public DeepSeekClient(String prompt){
        this.userPrompt = prompt;
        Dotenv dotenv = Dotenv.load();
        this.API_KEY = dotenv.get("API_KEY");
        this.endpoint = dotenv.get("ENDPOINT");
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

    @Override
    public ArrayList<String> extractKeywords() {
        ArrayList<String> responseList = new ArrayList<>();
        try{
            String requestBody = "{\n" +
                    "          \"model\": \"deepseek/deepseek-chat-v3-0324:free\",\n" +
                    "          \"messages\": [\n" +
                    "            {\"role\": \"user\", " +
                    "            \"content\": " +
                    "            \"Extract the main keywords and sentiment (sentiment can only be POSITIVE or NEGATIVE) from the user prompt. User prompt: " + getUserPrompt() + ". " + "Generate JUST a list (i.e 1. <insert synonym 1> ...) of " +
                    " 15 SYNONYMS for these words IF AND ONLY IF sentiment is POSITIVE. Otherwise if the sentiment is NEGATIVE, generate JUST a list (i.e 1. <insert synonym 1> ...) of 15 ANTONYMS. Make sure the words in the list are frequently used in a Google review for a location. Structure should follow this template EXACTLY and include NOTHING ELSE so I can parse the words in the curly braces: {<generated word 1>, ... <generated word n>, }" +
                    "            \"}\n" +
                    "          ]\n" +
                    "        }";
            System.out.println("Loaded API Key: " + getAPI_KEY());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(getEndpoint()))
                    .header("Authorization", "Bearer " + getAPI_KEY())
                    .POST(BodyPublishers.ofString(requestBody))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response is a success! \n" + response.body());
            //editing JSON file to see responses (in an organized format)
//            Files.write(Paths.get(JSON_FILE_PATH), response.body().getBytes());
//            System.out.println("test.json edited with the response - check it out!");
            // update responseList
            return extractFromJSON(response.body());
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
