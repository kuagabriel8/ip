package chatbot.command;

import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;
import chatbot.storage.Storage;
import chatbot.task.Task;

/**
 * Represents a command to add a task to the TaskList.
 * When executed, it adds the task, shows a confirmation message to the user,
 * and updates the total number of tasks in the list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task the task to be added to the TaskList
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand:
     * - Adds the task to the provided TaskList
     * - Uses Ui to show confirmation messages
     * - Optionally can save to storage (if implemented)
     *
     * @param tasks   the TaskList to add the task to
     * @param ui      the Ui instance to display messages
     * @param storage the Storage instance for persistence
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because AddCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

