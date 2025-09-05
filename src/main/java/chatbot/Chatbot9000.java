package chatbot;

import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;
import chatbot.storage.Storage;
import chatbot.command.Command;
import chatbot.parser.Parser;


public class Chatbot9000 {
    private static final String LINE = "____________________________________________________________";
    private static final String parentFolder = "data";
    private static final String filepath = "data/chatbot.Chatbot9000.txt";

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    public Chatbot9000() {
        this.storage = new Storage(parentFolder, filepath);
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




    /*public static void main(String[] args) throws chatbot.exception.EmptyArgumentException{
        chatbot.Chatbot9000 c = new chatbot.Chatbot9000();
        Scanner sc = new Scanner(System.in);

        System.out.println(LINE);
        c.ui.greetUser();
        System.out.println(LINE);

        while (true) {
            String input = sc.nextLine().trim();
            String[] inputs = input.split(" ", 2);
            chatbot.command.Command command = chatbot.command.Command.fromString(inputs[0]);
            if (command == null) {
                c.ui.invalidCommand();
                System.out.println(LINE);
                continue;
            }
            String arguments = (inputs.length > 1) ? inputs[1] : "";

            switch (command) {
                case LIST:
                    System.out.println(c.taskList.toString());
                    break;
                case BYE:
                    System.out.println(LINE);
                    c.ui.goodbyeUser();
                    System.out.println(LINE);
                    sc.close();
                    return;
                case MARK:
                    try {
                        if (arguments.isEmpty()) {
                            throw new IllegalArgumentException("You must enter a number after mark");
                        } else if (!isInteger(arguments)) {
                            throw new IllegalArgumentException("You must enter a number after mark");
                        }

                        int value = Integer.parseInt(arguments) - 1;
                        c.ui.mark();
                        c.taskList.getTask(value).markDone();
                        System.out.println(c.taskList.getTask(value).toString());

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error: chatbot.task.Task number " + arguments + " does not exist.");
                    }
                    break;

                case UNMARK:
                    try {
                        if (arguments.isEmpty()) {
                            throw new IllegalArgumentException("You must enter a number after mark");
                        } else if (!isInteger(arguments)) {
                            throw new IllegalArgumentException("You must enter a number after mark");
                        }

                        int value = Integer.parseInt(arguments) - 1;
                        c.ui.unmark();
                        c.taskList.getTask(value).unmarkDone();
                        System.out.println(c.taskList.getTask(value).toString());

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(" Error: chatbot.task.Task number " + arguments + " does not exist.");

                    break;
                case DELETE:
                    try {
                        if (arguments.isEmpty()) {
                            throw new IllegalArgumentException("You must enter a number after delete");
                        } else if (!isInteger(arguments)) {
                            throw new IllegalArgumentException("You must enter a number after delete");
                        }

                        int value = Integer.parseInt(arguments) - 1;
                        c.ui.delete();
                        System.out.println(c.taskList.getTask(value).toString());
                        c.taskList.deleteTask(value);


                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error: chatbot.task.Task number " + arguments + " does not exist.");
                    }
                    break;
                case TODO:
                    try {
                        checkEmptyArguments(arguments, "Add a task after todo :(");
                    } catch (chatbot.exception.EmptyArgumentException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    chatbot.task.Todo t = new chatbot.task.Todo(arguments);
                    c.taskList.addTask(t);
                    System.out.println(LINE);
                    System.out.println("Added: " + arguments);
                    System.out.println(t.toString());
                    System.out.println(c.taskList.size() + " tasks");
                    break;

                case DEADLINE:
                    String[] deadlineParts = arguments.split(" /by ", 2);
                    String by = (deadlineParts.length > 1) ? deadlineParts[1] : "";
                    try {
                        checkEmptyArguments(by, "deadline /by task");
                    } catch (chatbot.exception.EmptyArgumentException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    chatbot.task.Deadline deadline = new chatbot.task.Deadline(deadlineParts[0], deadlineParts[1]);
                    c.taskList.addTask(deadline);
                    System.out.println("Added: " + deadlineParts[0]);
                    System.out.println(deadline.toString());
                    System.out.println(c.taskList.size() + " tasks");
                    break;

                case EVENT:
                    String[] fromSplit = arguments.split(" /from ", 2);
                    String description = fromSplit[0];

                    String from = "";
                    String to = "";

                    try {
                        if (fromSplit.length > 1) {
                            String[] toSplit = fromSplit[1].split(" /to ", 2);
                            from = toSplit[0];
                            if (toSplit.length > 1) {
                                to = toSplit[1];
                            } else {
                                throw new chatbot.exception.EmptyArgumentException("event /from date to /date");
                            }
                        } else {
                            throw new chatbot.exception.EmptyArgumentException("event /from date to /date");
                        }
                    } catch (chatbot.exception.EmptyArgumentException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    chatbot.task.Event event = new chatbot.task.Event(description, from, to);
                    c.taskList.addTask(event);
                    System.out.println("Added: " + fromSplit[0]);
                    System.out.println(event.toString());
                    System.out.println(c.taskList.size() + " tasks");
                    break;

                case SAVE:
                    c.storage.saveTasks(c.taskList);
                    break;

            }
            System.out.println(LINE);
        }
    }*/
}
