package chatbot.ui;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() { return sc.nextLine().trim(); }

    //public void showLine() { System.out.println("____________________________________________________________"); }

    /*
    greets user
    */
    public String greetUser() {
        //  showLine();
        return "Hello! I'm Chatbot9000 \n What can I do for you";
        //showLine();
    }

    /*public void goodbyeUser(){ System.out.println("Bye. Hope to see you again soon"); }
    public void invalidCommand(){ System.out.println("Idk what that means bro");}
    public void mark(){ System.out.println("Nice! I've marked this task as done:");}
    public void unmark(){ System.out.println("OK, I've marked this task as not done yet:");}
    public void delete(){ System.out.println("I've deleted this task");}
    public void deadlineFormat(){ System.out.println("Format: deadline [TASK] /by [DATE]");}
    public void eventFormat(){ System.out.println("Format: event [TASK] /from [DATE] /to [DATE]");}
    public void close(){sc.close();}*/

    public String showMessage(String message) {
        //System.out.println(message);
        return message;
    }
}
