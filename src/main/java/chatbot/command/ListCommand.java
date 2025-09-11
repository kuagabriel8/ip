package chatbot.command;

import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;

/**
 * Represents a command to list all tasks in the TaskList.
 * When executed, it displays all tasks with their indices in the Ui.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand:
     * - Iterates through the TaskList and builds a formatted string of tasks
     * - Displays the tasks using the Ui
     * - If the task list is empty, displays an appropriate message
     *
     * @param tasks   the TaskList to display
     * @param ui      the Ui instance to display messages
     * @param storage the Storage instance for persistence (not used)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTasks().isEmpty()) {
            return "Your task list is empty!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are your tasks:\n");
        int index = 1;
        for (Task task : tasks.getTasks()) {
            sb.append(index).append(". ").append(task).append("\n");
            index++;
        }

        return sb.toString().trim();
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because ListCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
