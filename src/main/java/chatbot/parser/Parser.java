package chatbot.parser;

import chatbot.command.AddCommand;
import chatbot.command.Command;
import chatbot.command.DeleteCommand;
import chatbot.command.ExitCommand;
import chatbot.command.FindCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.SaveCommand;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Todo;

/**
 * Parses user input strings into corresponding Command objects.
 * The parser handles commands such as todo, deadline, event, delete, mark, unmark, save, list, and bye.
 */
public class Parser {

    /**
     * Enumeration of all supported chatbot commands.
     */
    public enum Commands {
        LIST,
        BYE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        SAVE;

        /*public static Commands fromString(String input) {
            try {
                for (Commands c : Commands.values()) {
                    if (c.name().equalsIgnoreCase(input)) {
                        return c;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }*/
    }

    /**
     * Parses a full command string from the user and returns a corresponding Command object.
     *
     * @param fullCommand the complete input string from the user
     * @return a Command object corresponding to the input
     * @throws Exception if the command is unrecognized or arguments are missing
     */
    public static Command parse(String fullCommand) throws Exception {

        // Precondition: fullCommand must not be null or empty
        assert fullCommand != null : "fullCommand must not be null";
        assert !fullCommand.isBlank() : "fullCommand must not be blank";

        String[] parts = fullCommand.split(" ", 2);
        Commands command = Commands.valueOf(parts[0].toUpperCase().trim());
        String arguments = (parts.length > 1) ? parts[1] : "";

        // Invariant: command should always be non-empty
        assert command != null : "command part should not be empty";

        switch (command) {
        case TODO:
            if (arguments.isEmpty()) {
                throw new Exception("The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(arguments));

        case DEADLINE:
            if (arguments.isEmpty()) {
                throw new Exception("The description of a deadline cannot be empty.");
            }
            return new AddCommand(Deadline.parse(arguments));

        case EVENT:
            if (arguments.isEmpty()) {
                throw new Exception("The description of an event cannot be empty.");
            }
            return new AddCommand(Event.parse(arguments));

        case DELETE:
            if (arguments.isEmpty()) {
                throw new Exception("Provide the index of the task to delete.");
            }
            int deleteIndex = Integer.parseInt(arguments) - 1;
            assert deleteIndex >= 0 : "The index of the task should be greater than 0.";
            return new DeleteCommand(deleteIndex);

        case MARK:
            if (arguments.isEmpty()) {
                throw new Exception("Provide the index of the task to mark.");
            }
            assert arguments != null : "arguments must not be null";
            int markIndex = Integer.parseInt(arguments) - 1;
            assert markIndex >= 0 : "mark index out of bounds";
            return new MarkCommand(markIndex, true);

        case UNMARK:
            if (arguments.isEmpty()) {
                throw new Exception("Provide the index of the task to unmark.");
            }
            assert arguments != null : "arguments must not be null";
            int unmarkIndex = Integer.parseInt(arguments) - 1;
            assert unmarkIndex >= 0 : "unmark index out of bounds";
            return new MarkCommand(unmarkIndex, false);

        case SAVE:
            return new SaveCommand();

        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case FIND:
            if (arguments.isEmpty()) {
                throw new Exception("Provide a keyword to find.");
            }
            assert arguments != null : "arguments must not be null";
            return new FindCommand(arguments);

        default:
            throw new Exception("I'm sorry, but I don't know what that means.");
        }
    }
}
