package chatbot.response;


/**
 * Represents a response from the chatbot.
 * A Response contains the message to be displayed to the user
 * and a flag indicating whether the chatbot should exit after this response.
 */
public class Response{
    private String response;
    private boolean isExit;

    /**
     * Constructs a Response object with the given message and exit flag.
     *
     * @param response the message text of the response
     * @param isExit true if the chatbot should terminate after this response; false otherwise
     */
    public Response(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    public String getResponse() {
        return response;
    }

    public boolean isExit() {
        return isExit;
    }
}