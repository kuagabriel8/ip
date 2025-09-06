package chatbot.command;

import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;

/**
 * Represents a command to mark or unmark a task as done in the task list.
 * <p>
 * This command updates the status of a task (completed or not completed)
 * and provides feedback to the user via the UI.
 */
public class MarkCommand extends Command {
    private int index;
    private boolean mark;

    /**
     * Constructs a MarkCommand.
     *
     * @param index the 0-based index of the task to mark or unmark
     * @param mark  true to mark the task as done, false to unmark it
     */
    public MarkCommand(int index, boolean mark) {
        this.index = index;
        this.mark = mark;
    }

    /**
     * Executes the command by marking or unmarking the specified task.
     * Updates the user interface with the result.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the Ui to show messages to the user
     * @param storage the Storage to save the tasks (not used here)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        if (mark) {
            task.markDone();
        } else {
            task.unmarkDone();
        }
        String status = mark ? "completed" : "not completed";
        ui.showMessage("Okay, I've marked this task as " + status + ":");
        ui.showMessage(task.toString());
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because MarkCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit(){
        return false;
    }
}
