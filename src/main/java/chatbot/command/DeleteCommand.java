package chatbot.command;

import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;
import chatbot.storage.Storage;
import chatbot.task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;  // The index of the task to delete

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToRemove = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        //ui.showTaskRemoved(taskToRemove);
    }

    @Override
    public boolean isExit() {
        return false;  // chatbot.command.DeleteCommand doesn't exit the program
    }
}
