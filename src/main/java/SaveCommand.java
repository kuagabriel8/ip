public class SaveCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);  // save all tasks to file
        ui.showMessage("Tasks have been successfully saved!");
    }

    @Override
    public boolean isExit() {
        return false;  // this command doesn't exit the program
    }
}

