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

    public static void main(String[] args) {
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

            if (Arrays.asList(commands).contains(command)) {

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
                    Todo t = new Todo(arguments);
                    tasks.add(t);
                    System.out.println(LINE);
                    System.out.println("Added: " + arguments);
                    System.out.println(t.toString());
                    System.out.println(tasks.size() + " tasks");
                    break;

                case "deadline":
                    String[] deadlineParts = arguments.split(" /by ", 2);
                    Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    tasks.add(deadline);
                    System.out.println("Added: " + deadlineParts[0]);
                    System.out.println(deadline.toString());
                    System.out.println(tasks.size() + " tasks");
                    break;

                case "event":
                    String[] fromSplit = arguments.split(" /from ", 2);
                    String description = fromSplit[0];  // "project meeting"
                    String from = "";
                    String to = "";

                    if (fromSplit.length > 1) {
                        String[] toSplit = fromSplit[1].split(" /to ", 2);
                        from = toSplit[0];
                        if (toSplit.length > 1) {
                            to = toSplit[1];
                        }
                    }

                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    System.out.println("Added: " + fromSplit[0]);
                    System.out.println(event.toString());
                    System.out.println(tasks.size() + " tasks");
                    break;

                default:
                    Task task = new Task(command);
                    tasks.add(task);
                    System.out.println(LINE);
                    System.out.println("added: " + input);
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
