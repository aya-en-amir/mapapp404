package usecase.recommendation;

import java.util.List;

import clientservice.api.DeepSeekClient;
import clientservice.api.GoogleMapsClient;
import clientservice.recommendation.Recommender;
import entity.Location;
import entity.Recommendation;
import interfaceservice.LlmClient;
import interfaceservice.RecommenderInterface;

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

