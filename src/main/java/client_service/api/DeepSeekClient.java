package client_service.api;

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

import com.google.gson.Gson;
import exceptions.ApiException;
import interface_service.LlmClient;
import io.github.cdimascio.dotenv.Dotenv;

public class DeepSeekClient implements LlmClient {
    private String apiKey = "";
    private String endpoint = "";

    public DeepSeekClient() {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("DEEPSEEK_API_KEY");
        this.endpoint = dotenv.get("DEEPSEEK_ENDPOINT");
    }

    public final String getApiKey() {
        return apiKey;
    }

    public final void setApiKeyTesting(String key) {
        this.apiKey = key;
    }

    public final String getEndpoint() {
        return endpoint;
    }

    @Override
    public String getLlmResponse(String prompt) {
        String llmResponse = "";
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("model", "deepseek/deepseek-chat-v3-0324:free");
            payload.put("messages", List.of(new Message("user", prompt)));

            String request = new Gson().toJson(payload);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(getEndpoint()))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + getApiKey())
                    .POST(HttpRequest.BodyPublishers.ofString(request))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new ApiException("Error response from DeepSeek: " + response.statusCode());
            }

            llmResponse = extractFromJSON(response.body());
            System.out.println("Response is a success! \n" + llmResponse);
        }
        catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }
        catch (ApiException ex) {
            System.out.println("API Exception: " + ex.getMessage());
        }
        catch (InterruptedException ex) {
            System.out.println("Interrupted: " + ex.getMessage());
        }
        finally {
            return llmResponse;
        }
    }

    /**
     * Extracts the message content from a JSON LLM API response.
     *
     * @param jsonResponse the raw JSON response
     * @return the extracted content string
     */
    public final String extractFromJSON(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        System.out.println("Raw LLM Response: " + jsonObject);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject message = ((JSONObject) choices.get(0)).getJSONObject("message");
        String content = message.getString("content");
        return content;
    }

    class Message {
        private String role;
        private String content;

        Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
