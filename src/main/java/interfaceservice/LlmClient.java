package interfaceservice;

public interface LlmClient {

    /**
     * Gets the LLM response for the given prompt.
     *
     * @param prompt the input text prompt
     * @return the LLM's response
     */
    String getLlmResponse(String prompt);
}
