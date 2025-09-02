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

    @Override
    public boolean isExit(){
        return false;
    }
}
