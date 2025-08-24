public class Task {
    private boolean done;
    private final String description;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return (done? "[X] " : "[ ] ") + description;
    }

}
