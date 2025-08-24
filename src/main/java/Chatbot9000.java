import java.util.Scanner;
import java.util.ArrayList;

public class Chatbot9000 {
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<String> inputs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println(LINE);
        System.out.println(" Hello! I'm Chatbot9000");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(LINE);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println((i + 1) + ". " + inputs.get(i));
                }
            } else {
                inputs.add(input);
                System.out.println(LINE);
                System.out.println("added: " + input);
            }

            System.out.println(LINE);
        }

        sc.close();
    }
}
