package chatbot.command;

import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;
import java.util.ArrayList;
import java.util.List;

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
        String loweredKeyword = keyword.toLowerCase();

        // Filter matching tasks
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.toString().toLowerCase().contains(loweredKeyword))
                .toList(); // Java 16+, otherwise use Collectors.toList()

        if (matchingTasks.isEmpty()) {
            return ui.showMessage("No matching tasks found for: " + keyword);
        }

        // Format with index numbers using IntStream
        String taskListStr = java.util.stream.IntStream.range(0, matchingTasks.size())
                .mapToObj(i -> (i + 1) + ". " + matchingTasks.get(i))
                .collect(java.util.stream.Collectors.joining("\n"));

        return ui.showMessage("Here are the matching tasks in your list:\n" + taskListStr);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
