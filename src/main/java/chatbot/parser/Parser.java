package chatbot.parser;

import chatbot.command.*;
import chatbot.task.*;

/**
 * Parses user input strings into corresponding Command objects.
 * The parser handles commands such as todo, deadline, event, delete, mark, unmark, save, list, and bye.
 */
public class Parser {

    /**
     * Parses a full command string from the user and returns a corresponding Command object.
     *
     * @param fullCommand the complete input string from the user
     * @return a Command object corresponding to the input
     * @throws Exception if the command is unrecognized or arguments are missing
     */
    public static Command parse(String fullCommand) throws Exception {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        String arguments = (parts.length > 1) ? parts[1] : "";

        switch (command.toLowerCase()) {
        case "todo":
            if (arguments.isEmpty()) throw new Exception("The description of a todo cannot be empty.");
            return new AddCommand(new Todo(arguments));

        case "deadline":
            if (arguments.isEmpty()) throw new Exception("The description of a deadline cannot be empty.");
            return new AddCommand(Deadline.parse(arguments));

        case "event":
            if (arguments.isEmpty()) throw new Exception("The description of an event cannot be empty.");
            return new AddCommand(Event.parse(arguments));

        case "delete":
            if (arguments.isEmpty()) throw new Exception("Provide the index of the task to delete.");
            int deleteIndex = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(deleteIndex);

        case "mark":
            if (arguments.isEmpty()) throw new Exception("Provide the index of the task to mark.");
            int markIndex = Integer.parseInt(arguments) - 1;
            return new MarkCommand(markIndex, true);

        case "unmark":
            if (arguments.isEmpty()) throw new Exception("Provide the index of the task to unmark.");
            int unmarkIndex = Integer.parseInt(arguments) - 1;
            return new MarkCommand(unmarkIndex, false);

        case "save":
            return new SaveCommand();


        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "find":
            if (arguments.isEmpty()) throw new Exception("Provide a keyword to find.");
            return new FindCommand(arguments);

        default:
            throw new Exception("I'm sorry, but I don't know what that means.");
        }
    }
}
