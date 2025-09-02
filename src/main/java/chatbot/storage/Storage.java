package chatbot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import chatbot.tasklist.TaskList;
import chatbot.task.*;

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
        // Remove extra spaces
        line = line.trim();
      //  System.out.println(line);

        // Check if the task is done (i.e., [X] instead of [ ])
        boolean isDone = line.startsWith("[X]", 3);
        if (isDone) {
            line = line.replace("[X]", "");
      //      System.out.println(line);// Remove the [X] and extra spaces
        } else {
            line = line.replace("[ ]", "");
       //     System.out.println(line);
        }

        // Identify task type (ToDo, chatbot.task.Deadline, chatbot.task.Event)
        if (line.startsWith("[T]")) {
            String description = line.substring(3).trim();
            Task todo = new Todo(description);
            if (isDone) todo.markDone();
         //   System.out.println(todo.toString());
            return todo;

        } else if (line.startsWith("[D]")) {
            String description = line.substring(3).split("by:")[0].trim();
            String dateStr = line.split("by:")[1].trim();
            //LocalDateTime by = parseDate(dateStr);
            Deadline deadline = new Deadline(description, dateStr);
            if (isDone) deadline.markDone();
            return deadline;

        } else if (line.startsWith("[E]")) {
            String description = line.substring(3).split("from:")[0].trim();
            String[] dates = line.split("from:")[1].split("to:");
           // LocalDateTime from = parseDate(dates[0].trim());
            //LocalDateTime to = parseDate(dates[1].trim());
            Event event = new Event(description, dates[0], dates[1]);
            if (isDone) event.markDone();
            return event;
        }

        return null;  // If the task type doesn't match, return null
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
