package client_service.api;

import com.google.gson.Gson;
import interface_service.LLMClient;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class DeepSeekClient implements LLMClient {
    private String API_KEY = "";
    private String endpoint = "";

    public DeepSeekClient(){
        Dotenv dotenv = Dotenv.load();
        this.API_KEY = dotenv.get("DEEPSEEK_API_KEY");
        this.endpoint = dotenv.get("DEEPSEEK_ENDPOINT");
    }

    public String getAPI_KEY(){
        return API_KEY;
    }

    public String getEndpoint(){
        return endpoint;
    }

    class Message {
        String role;
        String content;
        Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

    @Override
    public String getLLMResponse(String prompt) {
        String llmResponse = "";
        try{
//            String request = "{\n" +
//                    "          \"model\": \"deepseek/deepseek-chat-v3-0324:free\",\n" +
//                    "          \"messages\": [\n" +
//                    "            {\"role\": \"user\", " +
//                    "            \"content\": \"" + prompt + "\"}\n" +
//                    "          ]\n" +
//                    "        }";
//            HttpRequest httpRequest = HttpRequest.newBuilder()
//                    .uri(URI.create(getEndpoint()))
//                    .header("Authorization", "Bearer " + getAPI_KEY())
//                    .POST(BodyPublishers.ofString(request))
//                    .build();
//            HttpClient client = HttpClient.newHttpClient();
//            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> payload = new HashMap<>();
            payload.put("model", "deepseek/deepseek-chat-v3-0324:free");
            payload.put("messages", List.of(new Message("user", prompt)));

            String request = new Gson().toJson(payload);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(getEndpoint()))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + getAPI_KEY())
                    .POST(HttpRequest.BodyPublishers.ofString(request))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            llmResponse = extractFromJSON(response.body());
            System.out.println("Response is a success! \n" + llmResponse);
            return llmResponse;
        }
        catch(IOException e){
            System.out.println("IO Exception: " + e.getMessage());
            return llmResponse;
        }

        catch(InterruptedException e){
            System.out.println("Interrupted: " + e.getMessage());
            return llmResponse;
        }
    }


    public String extractFromJSON(String jsonResponse){
        JSONObject jsonObject = new JSONObject(jsonResponse);
        System.out.println("Raw LLM Response: " + jsonObject);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject message = ((JSONObject)choices.get(0)).getJSONObject("message");
        String content = message.getString("content");
        return content;

//        int startCurly = content.indexOf("{");
//        int endCurly = content.indexOf("}");
//        String response = content.substring(startCurly+1, endCurly);
//        ArrayList<String> responseList = new ArrayList<>(Arrays.asList(response.split(", ")));
//        return responseList;
    }


}