import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.LocalDate;

public class Deadline extends Task{
    protected LocalDateTime by;
    private DeadlineParsers parsers;

    public Deadline(String description, String by){
        super(description);
        this.by = parsers.parseToDateTime(by);
      //  DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
       // this.by = LocalDateTime.parse(by, inputFormat);
    }

    @Override
    public String toString(){
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + by.toString() + ")";
    }
}

