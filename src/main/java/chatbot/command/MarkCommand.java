package chatbot.command;

import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;


public class MarkCommand extends Command {
    private int index;
    private boolean mark;

    public MarkCommand(int index, boolean mark) {
        this.index = index;
        this.mark = mark;
    }

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
