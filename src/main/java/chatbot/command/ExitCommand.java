package chatbot.command;

import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;
import chatbot.storage.Storage;

/**
 * Represents a command that terminates the chatbot session.
 * When executed, it displays a farewell message and signals the chatbot to exit.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand:
     * - Shows a farewell message to the user
     *
     * @param tasks   the TaskList (not used in ExitCommand)
     * @param ui      the Ui instance to display messages
     * @param storage the Storage instance for persistence (not used)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye! Hope to see you again soon.");
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return true because ExitCommand terminates the chatbot
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
