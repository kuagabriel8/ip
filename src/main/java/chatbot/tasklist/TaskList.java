package chatbot.tasklist;

import chatbot.task.Task;

import java.util.ArrayList;
import java.lang.StringBuilder;


public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
      //  if (tasks == null) {
            this.tasks = tasks;
   //     } else {
       //     this.tasks = new ArrayList<>();
    //    }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + ". " + tasks.get(i));

            if (i < tasks.size() - 1) {
                result.append("\n");  // Add newline except for the last task
            }
        }

        return result.toString();
    }
}
