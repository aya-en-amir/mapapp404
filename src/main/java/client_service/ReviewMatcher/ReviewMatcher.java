package client_service.ReviewMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
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

    public String combineReview() {

        return String.join(" ", location.getReviews());

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

    public double calculateScoreWordnet() {
        double totalScore = 0.0;

        if (location.getReviews().isEmpty()) {
            return totalScore;
        }
        List<String> lemma = StanfordCore.setStandfordCore(this.combineReview());

        WS4JConfiguration.getInstance().setMemoryDB(false);
        WS4JConfiguration.getInstance().setMFS(true);
        ILexicalDatabase db = new MITWordNet();
        WuPalmer wuPalmer = new WuPalmer(db);
        for (String word : keywords) {
            for (String review : lemma) {
                totalScore += wuPalmer.calcRelatednessOfWords(word.toLowerCase(), review.toLowerCase());
            }
        }
        return (totalScore / keywords.size()) * 100;
    }
    
}
