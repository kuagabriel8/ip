package chatbot.command;

import chatbot.storage.Storage;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;


public class SaveCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);  // save all tasks to file
        ui.showMessage("Tasks have been successfully saved!");
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because SaveCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

