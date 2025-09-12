package chatbot.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Todo;
import chatbot.tasklist.TaskList;




/**
 * Handles saving and loading tasks to and from a text file.
 * Supports Todo, Deadline, and Event tasks.
 */
public class Storage {

    private final String filepath;
    private final String parentFolder;

    /**
     * Constructs a Storage object with the specified folder and file path.
     *
     * @param parentFolder the folder to store the file in
     * @param filepath     the file path for storing tasks
     */
    public Storage(String parentFolder, String filepath) {
        this.parentFolder = parentFolder;
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file into an ArrayList of Task objects.
     *
     * @return an ArrayList of tasks read from the file
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filepath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    /**
     * Parses a single line of text from the storage file into a Task object.
     *
     * @param line a line from the storage file
     * @return the corresponding Task object, or null if the line is invalid
     */
    private Task parseTask(String line) {
        line = line.trim();

        // Assertion to check the line format
        // ^\[T|D|E\]  -> first box must be [T], [D], or [E]
        // \[( |X)\]   -> second box must be [ ] or [X]
        // .+          -> at least one character after the boxes (description)
        assert line.matches("^\\[[TDE]\\]\\[( |X)\\].+")
                : "Invalid task format: " + line;


        boolean isDone = line.contains("[X]");
        if (isDone) {
            line = line.replace("[X]", "");

        } else {
            line = line.replace("[ ]", "");

        }

        if (line.startsWith("[T]")) {
            //line = line.replace("[T]", "");
            String description = line.substring(3).trim();
            Task todo = new Todo(description);
            if (isDone) {
                todo.markDone();
            }
            return todo;

        } else if (line.startsWith("[D]")) {
            line = line.replace("[D]", "");
            String[] deadlineParts = line.split("by:");
            String byStr = (deadlineParts.length > 1) ? deadlineParts[1].trim() : "";
            LocalDateTime by = LocalDateTime.parse(byStr);
            Deadline deadline = new Deadline(deadlineParts[0].trim(), by.toString());
            if (isDone) {
                deadline.markDone();
            }
            return deadline;

        } else if (line.startsWith("[E]")) {
            line = line.replace("[E]", "").trim();
            String[] eventParts = line.split("from:");
            String eventPart1 = (eventParts.length > 1) ? eventParts[1].trim() : "";
            String[] eventParts2 = eventPart1.split("to:");
            String eventPart2 = (eventParts2.length > 1) ? eventParts2[1].trim() : "";
            Event event = new Event(eventParts[0].trim(), eventParts2[0].trim(), eventPart2);
            if (isDone) {
                event.markDone();
            }

            return event;

        }

        return null;
    }

    /**
     * Saves the tasks in the TaskList to the file.
     * Creates parent folder and file if they do not exist.
     *
     * @param taskList the TaskList containing tasks to save
     */
    public void saveTasks(TaskList taskList) {
        try {
            File folder = new File(parentFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(filepath);
            for (Task task : taskList.getTasks()) {
                //int doneFlag = (task.isDone()) ? 1 : 0;
                writer.write(task + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }





}
