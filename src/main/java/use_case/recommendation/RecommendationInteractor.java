package use_case.recommendation;

import java.util.List;

import client_service.api.DeepSeekClient;
import client_service.api.GoogleMapsClient;
import client_service.recommendation.Recommender;
import entity.Location;
import entity.Recommendation;
import interface_service.LlmClient;
import interface_service.RecommenderInterface;

/**
 * The Recommendation Interactor.
 */
public class RecommendationInteractor implements RecommendationInputBoundary {
    private final RecommendationOutputBoundary recoPresenter;

    public RecommendationInteractor(RecommendationOutputBoundary recoPresenter) {
        this.recoPresenter = recoPresenter;
    }

    @Override
    public void execute(RecommendationInputData recoInputData) throws Exception {
        final String prompt = recoInputData.getPrompt();
        final String postalCode = recoInputData.getPostalCode();

        LlmClient llmClient = new DeepSeekClient();
        final int radiusInMeters = 5000;

        GoogleMapsClient client = new GoogleMapsClient(radiusInMeters);
        List<Location> locations = client.serveLocations(postalCode);

        RecommenderInterface recommender = new Recommender(prompt, locations, llmClient);
        Recommendation recommendation = recommender.recommend();

        RecommendationOutputData outputData = new RecommendationOutputData(List.of(recommendation));
        recoPresenter.loadSuccessView(outputData);
    }
}

