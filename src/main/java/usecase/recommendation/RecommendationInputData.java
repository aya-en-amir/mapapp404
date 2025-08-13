package usecase.recommendation;

public class RecommendationInputData {
    private String prompt;
    private String postalCode;

    public RecommendationInputData(String prompt, String postalCode) {
        this.prompt = prompt;
        this.postalCode = postalCode;
    }

    public final String getPrompt() {
        return prompt;
    }

    public final String getPostalCode() {
        return postalCode;
    }
}
