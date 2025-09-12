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

        return "Hello! I'm Chatbot9000 \n What can I do for you";

    }



}
