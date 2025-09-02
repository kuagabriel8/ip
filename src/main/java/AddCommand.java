public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

