package client_service.ReviewMatcher;


import org.apache.commons.text.similarity.CosineSimilarity;


public class CosineCalculator {
    private final String keyword;
    private final String reviewLocation;

    public CosineCalculator(String keyword, String reviewLocation) {
        this.keyword = keyword;
        this.reviewLocation = reviewLocation;
    }

    public double calculate() {
        CosineSimilarity sim = new CosineSimilarity();
        return sim.cosineSimilarity(ConverterMap.converter(keyword), ConverterMap.converter(reviewLocation));
    }
}
