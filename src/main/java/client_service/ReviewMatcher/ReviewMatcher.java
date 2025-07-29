package client_service.ReviewMatcher;

import java.util.ArrayList;
import java.util.List;

import edu.uniba.di.lacam.kdde.lexical_db.ILexicalDatabase;
import edu.uniba.di.lacam.kdde.lexical_db.MITWordNet;
import edu.uniba.di.lacam.kdde.ws4j.similarity.Path;
import edu.uniba.di.lacam.kdde.ws4j.similarity.WuPalmer;
import edu.uniba.di.lacam.kdde.ws4j.util.WS4JConfiguration;
import entity.Location;

public class ReviewMatcher {
    private List<String> keywords;
    private Location location;

    public ReviewMatcher(List<String> keywords, Location location) {
        this.keywords = keywords;
        this.location = location;
    }

    public double calculateScoreWordnet() {
        double totalScore = 0.0;

        if (location.getReviews().isEmpty()) {
            return totalScore;
        }
        WS4JConfiguration.getInstance().setMemoryDB(false);
        WS4JConfiguration.getInstance().setMFS(true);
        ILexicalDatabase db = new MITWordNet();
        WuPalmer wuPalmer = new WuPalmer(db);
        for (String word : keywords) {
            for (String locationReview : location.getReviews()) {
                totalScore += wuPalmer.calcRelatednessOfWords(word.toLowerCase(), locationReview.toLowerCase());
            }
        }
        return (totalScore / keywords.size()) * 100;
    }
    public List<Double> calculateScoreTest() {
        List<Double> scores = new ArrayList<Double>();
        WS4JConfiguration.getInstance().setMemoryDB(false);
        WS4JConfiguration.getInstance().setMFS(true);
        ILexicalDatabase db = new MITWordNet();
        WuPalmer wuPalmer = new WuPalmer(db);
        Path path = new Path(db);
        scores.add(wuPalmer.calcRelatednessOfWords("yummy", "delicious"));
        scores.add(path.calcRelatednessOfWords("yummy", "delicious"));


        return scores;
    }

    public double calculateScore() {
        double totalScore = 0.0;

        if (location.getReviews().isEmpty()) {
            return totalScore;
        }

        for (String word : keywords) {
            for (String locationReview : location.getReviews()) {
                CosineCalculator calculator = new CosineCalculator(word.toLowerCase(), locationReview.toLowerCase());
                totalScore += calculator.calculate();
            }
        }
        return (totalScore / keywords.size()) * 100;
    }
}
