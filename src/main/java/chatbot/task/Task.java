package chatbot.task;

/**
 * Represents a task in the chatbot system.
 * A task has a description and a completion status.
 * Subclasses may represent specific types of tasks like Todo, Deadline, or Event.
 */
public class Task {

    private boolean done;
    private final String description;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Returns true if the task has been marked as done.
     *
     * @return whether the task is completed
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.done = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return (done? "[X] " : "[ ] ") + description;
    }
}
