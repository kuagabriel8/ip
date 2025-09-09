package chatbot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import chatbot.tasklist.TaskList;
import chatbot.task.Task;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Todo;

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
            if (isDone) todo.markDone();
            return todo;

        } else if (line.startsWith("[D]")) {
            line = line.replace("[D]", "");
            String[] deadlineParts = line.split("by:");
            String by = (deadlineParts.length > 1) ? deadlineParts[1] : "";
            //LocalDateTime by = parseDate(dateStr);
            Deadline deadline = new Deadline(deadlineParts[0].trim(), by.trim());
            if (isDone) deadline.markDone();
            return deadline;

        } else if (line.startsWith("[E]")) {
            /*line = line.replace("[E]", "");
            line.trim();
            String[] eventParts = line.split("from:");
            String eventParts2 = (eventParts.length > 1) ? eventParts[1] : "";
            String[] eventParts3 = line.split("to:");
            String eventParts4 = (eventParts3.length > 1) ? eventParts3[1] : "";
            Event event = new Event(eventParts[0], eventParts2, eventParts4);
            if (isDone) event.markDone();
            return event;*/
            // Step 1: Remove the '[E]' tag at the beginning of the string and trim any extra spaces
            line = line.replace("[E]", "").trim();  // Now `line` should be: "read from: now to: later"

            // Step 2: Split on "from:" and "to:" to extract the relevant parts
            String[] eventParts = line.split("from:");  // Split by "from:"
            String eventPart1 = (eventParts.length > 1) ? eventParts[1].trim() : ""; // Get the part after "from:"

            // Step 3: Split by "to:" to extract the second part
            String[] eventParts2 = eventPart1.split("to:");  // Split by "to:"
            String eventPart2 = (eventParts2.length > 1) ? eventParts2[1].trim() : ""; // Get the part after "to:"

            // Step 4: Create the Event object with parsed parts
            Event event = new Event(eventParts[0].trim(), eventParts2[0].trim(), eventPart2);

            // Step 5: If the event is marked as done, mark it as done
            if (isDone) event.markDone();

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
