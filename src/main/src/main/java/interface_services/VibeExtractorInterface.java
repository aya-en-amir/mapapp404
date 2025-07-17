package interface_services;

import java.util.ArrayList;

public interface VibeExtractorInterface {
    /**
     * class that defines an interface for VibeExtractor
     */

    /**
     *  extractKeywords(String prompt): extracts keywords from the given prompt using
     *  DeepSeek API
     * @return: List of strings
     */
    ArrayList<String> extractKeywords();

    /**
     *  extractFromJSON: helper method that extracts and create a ArayList<String> from response json
     * @param: String jsonResponse
     * @return: ArrayList<String>
     */
    ArrayList<String> extractFromJSON(String jsonResponse);

}