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
        return false;  // DeleteCommand doesn't exit the program
    }
}
