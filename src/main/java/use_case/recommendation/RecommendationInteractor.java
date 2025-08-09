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
    private final RecommendationOutputBoundary recoPresenter;

    public RecommendationInteractor(RecommendationDataAccessInterface recoDataAccessObject,
                                    RecommendationOutputBoundary recoPresenter) {
        this.recoDataAccessObject = recoDataAccessObject;
        this.recoPresenter = recoPresenter;
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
            recoPresenter.loadSuccessView(outputData);

        } catch (Exception e) {
            recoPresenter.loadFailureView(e.getMessage());
        }
    }
}

