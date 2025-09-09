package chatbot.response;

public class Response{

    private String response;
    private boolean isExit;

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