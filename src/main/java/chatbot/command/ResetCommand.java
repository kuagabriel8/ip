package chatbot.command;

import chatbot.storage.Storage;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;

public class ResetCommand extends Command {

    /**
     * Constructs a ResetCommand.
     *
     */
    public ResetCommand() {}

    /**
     * Executes the command by marking or unmarking the specified task.
     * Updates the user interface with the result.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the Ui to show messages to the user
     * @param storage the Storage to save the tasks (not used here)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.reset();
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because ResetCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit(){
        return false;
    }
}