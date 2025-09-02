public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTasks().isEmpty()) {
            ui.showMessage("Your task list is empty!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are your tasks:\n");
        int index = 1;
        for (Task task : tasks.getTasks()) {
            sb.append(index).append(". ").append(task).append("\n");
            index++;
        }

        ui.showMessage(sb.toString().trim());
    }

    @Override
    public boolean isExit() {
        return false;  // Listing tasks does not exit the program
    }
}
