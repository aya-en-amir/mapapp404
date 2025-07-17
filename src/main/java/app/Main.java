package app;

import client_service.DeepSeekClient.DeepSeekClient;

public class Main {
    public static void main(String[] args) {
        DeepSeekClient dsclient = new DeepSeekClient("I am stressed and anxious");
        dsclient.extractKeywords();
    }
}