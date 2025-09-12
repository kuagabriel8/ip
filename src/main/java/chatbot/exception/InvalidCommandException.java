package chatbot.exception;

public class InvalidCommandException extends Exception{

    public InvalidCommandException() {
        super("Invalid command. Please enter a valid command.");
    }

    /**
     * Creates an InvalidCommandException with a custom message.
     *
     * @param command the command string that caused the exception
     */
    public InvalidCommandException(String command) {
        super("Invalid command: \"" + command + "\". Please enter a valid command.");
    }


}
