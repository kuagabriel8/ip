import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Chatbot9000 {
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String parentFolder = "data";
    private static final String filepath = "data/Chatbot9000.txt";
    public enum Command {
        LIST,
        BYE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        SAVE;


        public static Command fromString(String input) {
            try {
                for (Command c : Command.values()) {
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
    public static boolean isInteger(String str) {
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

    public static void checkEmptyArguments(String arguments, String message) throws EmptyArgumentException{
        if (arguments == null || arguments.isEmpty()) {
            throw new EmptyArgumentException(message);
        }
    }

    /*public static void checkInvalidCommand(String arguments, String message) throws InvalidCommandException {
        if (!Arrays.asList(commands).contains(arguments)) {
            throw new InvalidCommandException(message);
        }

    }*/

    public static void main(String[] args) throws EmptyArgumentException{
        Scanner sc = new Scanner(System.in);


        System.out.println(LINE);
        System.out.println(" Hello! I'm Chatbot9000");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = sc.nextLine().trim();
            String[] inputs = input.split(" ", 2);
            Command command = Command.fromString(inputs[0]);
            if (command == null) {
                System.out.println("Idk what that means bro");
                System.out.println(LINE);
                continue;
            }
            String arguments = (inputs.length > 1) ? inputs[1] : "";

            switch (command) {
                case LIST:
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;
                case BYE:
                    System.out.println(LINE);
                    System.out.println(" Bye. Hope to see you again soon!");
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
                        System.out.println("Nice! I've marked this task as done:");
                        tasks.get(value).markDone();
                        System.out.println(tasks.get(value).toString());

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("⚠️ Error: Task number " + arguments + " does not exist.");
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
                        System.out.println("OK, I've marked this task as not done yet:");
                        tasks.get(value).unmarkDone();
                        System.out.println(tasks.get(value).toString());

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("⚠️ Error: Task number " + arguments + " does not exist.");
                    }
                    break;
                case DELETE:
                    try {
                        if (arguments.isEmpty()) {
                            throw new IllegalArgumentException("You must enter a number after delete");
                        } else if (!isInteger(arguments)) {
                            throw new IllegalArgumentException("You must enter a number after delete");
                        }

                        int value = Integer.parseInt(arguments) - 1;
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(tasks.get(value).toString());
                        tasks.remove(value);


                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("⚠️ Error: Task number " + arguments + " does not exist.");
                    }
                    break;
                case TODO:
                    try {
                        checkEmptyArguments(arguments, "Add a task after todo :(");
                    } catch (EmptyArgumentException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    Todo t = new Todo(arguments);
                    tasks.add(t);
                    System.out.println(LINE);
                    System.out.println("Added: " + arguments);
                    System.out.println(t.toString());
                    System.out.println(tasks.size() + " tasks");
                    break;

                case DEADLINE:
                    String[] deadlineParts = arguments.split(" /by ", 2);
                    String by = (deadlineParts.length > 1) ? deadlineParts[1] : "";
                    try {
                        checkEmptyArguments(by, "Format: deadline [TASK] /by [DATE]");
                    } catch (EmptyArgumentException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    tasks.add(deadline);
                    System.out.println("Added: " + deadlineParts[0]);
                    System.out.println(deadline.toString());
                    System.out.println(tasks.size() + " tasks");
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
                                throw new EmptyArgumentException("Format: event [TASK] /from [DATE] /to [DATE]");

                            }
                        } else {
                            throw new EmptyArgumentException("Format: event [TASK] /from [DATE] /to [DATE]");
                        }
                    } catch (EmptyArgumentException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    System.out.println("Added: " + fromSplit[0]);
                    System.out.println(event.toString());
                    System.out.println(tasks.size() + " tasks");
                    break;

                case SAVE:

                    try {
                        File folder = new File(parentFolder);
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }
                        File file = new File(filepath);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileWriter writer = new FileWriter(filepath);
                        for (Task task : tasks) {
                            int doneFlag = (task.isDone()) ? 1 : 0;
                            writer.write(task + System.lineSeparator());
                        }
                        writer.close();

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }






                    break;



            }
            System.out.println(LINE);
        }
    }
}
