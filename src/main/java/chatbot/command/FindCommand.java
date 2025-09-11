package chatbot.command;

import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;
import java.util.ArrayList;

public class FindCommand  extends Command {
    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword the word to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command: displays tasks that contain the keyword.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui instance to display messages
     * @param storage the Storage instance (not used in this command)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found for: " + keyword;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int index = 1;
        for (Task task : matchingTasks) {
            sb.append(index).append(".").append(task).append("\n");
            index++;
        }

        return sb.toString().trim();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
