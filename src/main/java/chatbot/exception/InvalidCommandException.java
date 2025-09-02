package chatbot.exception;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String message){
        super(message);
    }
}
