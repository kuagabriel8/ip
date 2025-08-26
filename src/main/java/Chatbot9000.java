import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Chatbot9000 {
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String[] commands = {"list", "bye", "mark", "unmark", "todo", "deadline", "event"};
    private static final String[] taskCommands = {"todo", "deadline", "event"};
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

    public static void checkInvalidCommand(String arguments, String message) throws InvalidCommandException {
        if (!Arrays.asList(commands).contains(arguments)) {
            throw new InvalidCommandException(message);
        }

    }

    public static void main(String[] args) throws EmptyArgumentException{
        Scanner sc = new Scanner(System.in);

        System.out.println(LINE);
        System.out.println(" Hello! I'm Chatbot9000");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = sc.nextLine().trim();
            String[] inputs = input.split(" ", 2);
            String command = inputs[0];
            String arguments = (inputs.length > 1) ? inputs[1] : "";

            try {
                checkInvalidCommand(command, "Idk what that means bro");
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
            switch (command) {
                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;
                case "bye":
                    System.out.println(LINE);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    sc.close();

                    return;
                case "mark":
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

                case "unmark":
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
                case "todo":
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

                case "deadline":
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

                case "event":
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


            }
            System.out.println(LINE);
        }


    }

}
            /*
            if (command.equalsIgnoreCase("bye")) {
                System.out.println(LINE);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;

            } else if (inputs[0].equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (inputs[0].equalsIgnoreCase("mark")) {
                try {
                    if (isInteger(inputs[1])) {
                        int value = Integer.parseInt(inputs[1]) - 1;
                        System.out.println("Nice! I've marked this task as done:");
                        tasks.get(value).markDone();
                        System.out.println(tasks.get(value).toString());
                    }
                } catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                }


            } else if (inputs[0].equalsIgnoreCase("unmark")) {
                try {
                    if (isInteger(inputs[1])) {
                        int value = Integer.parseInt(inputs[1]) - 1;
                        System.out.println("OK, I've marked this task as not done yet:");
                        tasks.get(value).unmarkDone();
                        System.out.println(tasks.get(value).toString());
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (inputs[0].equalsIgnoreCase("todo")){

                Task t = new Task(input);
                tasks.add(t);
                System.out.println(LINE);
                System.out.println("added: " + input);
            }

             */
/*
            System.out.println(LINE);
        }

        sc.close();
    }

}*/
