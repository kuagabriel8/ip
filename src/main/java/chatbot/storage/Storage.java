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

public class Storage {
    private final String filepath;
    private final String parentFolder;

    public Storage(String parentFolder, String filepath) {
        this.parentFolder = parentFolder;
        this.filepath = filepath;
    }

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

    private Task parseTask(String line) {
        line = line.trim();

        boolean isDone = line.startsWith("[X]", 3);
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
            line = line.replace("[E]", "");
            String[] eventParts = line.split("from:");
            String eventParts2 = (eventParts.length > 1) ? eventParts[1] : "";
            String[] eventParts3 = line.split("to:");
            String eventParts4 = (eventParts3.length > 1) ? eventParts3[1] : "";
           // LocalDateTime from = parseDate(dates[0].trim());
            //LocalDateTime to = parseDate(dates[1].trim());
            Event event = new Event(eventParts[0], eventParts2, eventParts4);
            if (isDone) event.markDone();
            return event;
        }

        return null;
    }


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
