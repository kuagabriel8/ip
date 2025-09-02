package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import chatbot.parser.DeadlineParsers;

public class Deadline extends Task {
    protected LocalDateTime by;
    private DeadlineParsers parsers;

    public Deadline(String description, String by){
        super(description);
        this.by = parsers.parseToDateTime(by);
      //  DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
       // this.by = LocalDateTime.parse(by, inputFormat);
    }

    public static Deadline parse(String arguments){
        String[] deadlineParts = arguments.split(" /by ", 2);
        String by = (deadlineParts.length > 1) ? deadlineParts[1] : "";
        return new Deadline(deadlineParts[0], by);
    }

    @Override
    public String toString(){
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[D]" + super.toString() + " by: " + by.toString() + "";
    }
}

