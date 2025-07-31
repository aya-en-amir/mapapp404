import client_service.ReviewMatcher.ReviewMatcher;
import edu.uniba.di.lacam.kdde.lexical_db.ILexicalDatabase;
import edu.uniba.di.lacam.kdde.lexical_db.MITWordNet;
import edu.uniba.di.lacam.kdde.ws4j.similarity.Path;
import edu.uniba.di.lacam.kdde.ws4j.similarity.WuPalmer;
import edu.uniba.di.lacam.kdde.ws4j.util.WS4JConfiguration;
import org.junit.Test;

import entity.Location;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ReviewMatcherTest {
    @Test
    public void testNumOne() {
        List<String> bahenReview = new ArrayList<>();
        bahenReview.addAll(Arrays.asList("It's", "very", "pale.", "The", "food", "needs", "more", "salt"));
        Location location = new Location("Bahen", 10.122f, 20.12f, bahenReview);

        List<String> keyword = new ArrayList<>();
        keyword.addAll(Arrays.asList("delicious", "yummy"));
        ReviewMatcher reviewMatcher = new ReviewMatcher(keyword, location);
        System.out.println(reviewMatcher.calculateScoreWordnet());
        System.out.println(reviewMatcher.calculateScore());
    }
    @Test
    public void testNumTwo() {
        List<String> bahenReview = new ArrayList<>();
        bahenReview.addAll(Arrays.asList("It's", "a", "depressing", "place"));
        Location location = new Location("Bahen", 10.122f, 20.12f, bahenReview);

        List<String> keyword = new ArrayList<>();
        keyword.addAll(Arrays.asList("awesome"));
        ReviewMatcher reviewMatcher = new ReviewMatcher(keyword, location);
        Double score = reviewMatcher.calculateScoreWordnet();
        System.out.println(score);
    }
    @Test
    public void testWordNet() {
        WS4JConfiguration.getInstance().setMemoryDB(false);
        WS4JConfiguration.getInstance().setMFS(true);
        ILexicalDatabase db = new MITWordNet();
        WuPalmer wuPalmer = new WuPalmer(db);

        System.out.println(wuPalmer.calcRelatednessOfWords("water", "wet"));
        System.out.println(wuPalmer.calcRelatednessOfWords("water", "drink"));
        System.out.println(wuPalmer.calcRelatednessOfWords("tasty", "delicious"));
    }

}

