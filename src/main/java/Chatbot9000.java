import java.util.Scanner;
import java.util.ArrayList;

public class Chatbot9000 {
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<Task> tasks = new ArrayList<>();

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
            String[] inputs = input.split(" ");

            if (inputs[0].equalsIgnoreCase("bye")) {
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
            } else {
                Task t = new Task(input);
                tasks.add(t);
                System.out.println(LINE);
                System.out.println("added: " + input);
            }

            System.out.println(LINE);
        }

        sc.close();
    }
}
