package chatbot.command;

import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;
import chatbot.storage.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye! Hope to see you again soon.");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
