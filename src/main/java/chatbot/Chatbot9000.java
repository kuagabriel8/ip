package chatbot;

import chatbot.command.Command;
import chatbot.parser.Parser;
import chatbot.response.Response;
import chatbot.storage.Storage;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;


/**
 * The main class for Chatbot9000, a task management chatbot.
 * Handles reading user input, parsing commands, executing commands, and interacting with the user.
 */
public class Chatbot9000 {
    //private static final String LINE = "____________________________________________________________";
    private static final String PARENT_FOLDER = "data";
    private static final String FILE_PATH = "data/chatbot.Chatbot9000.txt";

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a Chatbot9000 instance and initializes storage, UI, and task list.
     */
    public Chatbot9000() {
        this.storage = new Storage(PARENT_FOLDER, FILE_PATH);
        this.ui = new Ui();
        this.taskList = new TaskList(storage.loadTasks());
    }

    public Response getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String msg = c.execute(taskList, ui, storage);
            return new Response(msg, c.isExit());
        } catch (Exception e) {
            return new Response(e.getMessage(), false);
        }
    }

    /**
     * Starts the chatbot by greeting the user and entering the command loop.
     * Continuously reads and executes commands until the exit command is issued.
     */
    public void start() {
        //ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                getResponse(fullCommand);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

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
        SAVE;


        public static Commands fromString(String input) {
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
        }
    }

    /**
     * The main entry point of the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Chatbot9000().start();
    }

    /*public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void checkEmptyArguments(String arguments, String message) throws chatbot.exception.EmptyArgumentException{
        if (arguments == null || arguments.isEmpty()) {
            throw new chatbot.exception.EmptyArgumentException(message);
        }
    }*/




}
