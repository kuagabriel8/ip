package chatbot;

import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;
import chatbot.storage.Storage;
import chatbot.command.Command;
import chatbot.parser.Parser;


public class Chatbot9000 {
    private static final String LINE = "____________________________________________________________";
    private static final String PARENT_FOLDER = "data";
    private static final String FILE_PATH = "data/chatbot.Chatbot9000.txt";

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    public Chatbot9000() {
        this.storage = new Storage(PARENT_FOLDER, FILE_PATH);
        this.ui = new Ui();
        this.taskList = new TaskList(storage.loadTasks());
    }

    public void start() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

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
