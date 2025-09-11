package chatbot.response;

public class Response{

    private String responseMessage;
    private boolean isExit;

    public Response(String responseMessage, boolean isExit) {
        this.responseMessage = responseMessage;
        this.isExit = isExit;
    }

    public String getResponse() {
        return responseMessage;
    }

    public boolean isExit() {
        return isExit;
    }
}