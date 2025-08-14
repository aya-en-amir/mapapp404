package interfaceservice;

import entity.Recommendation;

public interface RecommenderInterface {

    /**
     * Finds nearby locations given a postal code.
     *
     * @return a Recommendation object
     */
    Recommendation recommend();
}
