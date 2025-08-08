package use_case.recommendation;

import client_service.Recommendation.Recommender;
import client_service.api.DeepSeekClient;
import client_service.api.GoogleMapsClient;
import entity.Location;
import entity.Recommendation;
import interface_service.LLMClient;
import interface_service.RecommenderInterface;

import java.util.List;

/**
 * The Recommendation Interactor.
 */
public class RecommendationInteractor implements RecommendationInputBoundary{
    private final RecommendationDataAccessInterface recoDataAccessObject;
    private final RecommendationOutputBoundary recoOutputBoundary;

    public RecommendationInteractor(RecommendationDataAccessInterface recoDataAccessObject,
                                    RecommendationOutputBoundary recoOutputBoundary) {
        this.recoDataAccessObject = recoDataAccessObject;
        this.recoOutputBoundary = recoOutputBoundary;
    }

    @Override
    public void execute(RecommendationInputData recoInputData) {
        final String prompt = recoInputData.getPrompt();
        final String postalCode = recoInputData.getPostalCode();

        try {
            LLMClient llmClient = new DeepSeekClient();
            final int radiusInMeters = 5000;
//            final String postalCode = "M5S 2E4";

            GoogleMapsClient client = new GoogleMapsClient(radiusInMeters);
            List<Location> locations = client.serveLocations(postalCode);

            RecommenderInterface recommender = new Recommender(prompt, locations, llmClient);
            Recommendation recommendation = recommender.recommend();

            RecommendationOutputData outputData = new RecommendationOutputData(List.of(recommendation));
            recoOutputBoundary.loadSuccessView(outputData);

        } catch (Exception e) {
            recoOutputBoundary.loadFailureView(e.getMessage());
        }
    }
}

