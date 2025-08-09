package use_case.recommendation;

public class RecommendationInputData {
    private String prompt;
    private String postalCode;

    public RecommendationInputData(String prompt, String postalCode) {
        this.prompt = prompt;
        this.postalCode = postalCode;
    }
    public String getPrompt() {
        return prompt;
    }
    public String getPostalCode() {
        return postalCode;
    }
}
