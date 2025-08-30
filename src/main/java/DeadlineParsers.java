import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

public class DeadlineParsers {

    private DeadlineParsers() {}

        // Normalize separators to '-' and collapse spaces
        private static String normalize(String raw) {
        return raw.trim().replace('/', '-').replaceAll("\\s+", " ");
    }

        private static final DateTimeFormatter YMD_DATE =
                new DateTimeFormatterBuilder().parseCaseInsensitive()
                        .appendPattern("uuuu-MM-dd")
                        .toFormatter()
                        .withResolverStyle(ResolverStyle.STRICT);

        private static final DateTimeFormatter YMD_DATETIME =
                new DateTimeFormatterBuilder().parseCaseInsensitive()
                        .appendPattern("uuuu-MM-dd HHmm")
                        .toFormatter()
                        .withResolverStyle(ResolverStyle.STRICT);

        private static final DateTimeFormatter DMY_DATE =
                new DateTimeFormatterBuilder().parseCaseInsensitive()
                        .appendPattern("dd-MM-uuuu")
                        .toFormatter()
                        .withResolverStyle(ResolverStyle.STRICT);

        private static final DateTimeFormatter DMY_DATETIME =
                new DateTimeFormatterBuilder().parseCaseInsensitive()
                        .appendPattern("dd-MM-uuuu HHmm")
                        .toFormatter()
                        .withResolverStyle(ResolverStyle.STRICT);

        // Try the 4 formats in order; return LocalDateTime (23:59 if date-only)
        public static LocalDateTime parseToDateTime(String raw) {
        String s = normalize(raw);

        List<DateTimeFormatter> order = List.of(
                YMD_DATETIME, DMY_DATETIME, YMD_DATE, DMY_DATE
        );

        for (DateTimeFormatter fmt : order) {
            try {
                if (fmt == YMD_DATE || fmt == DMY_DATE) {
                    LocalDate d = LocalDate.parse(s, fmt);
                    return d.atTime(23, 59);
                } else {
                    return LocalDateTime.now();
                }
            } catch (DateTimeParseException ignore) {}
        }

        throw new IllegalArgumentException(
                "Invalid date/time. Use one of: yyyy-mm-dd, yyyy-mm-dd HHmm, dd-mm-yyyy, dd-mm-yyyy HHmm " +
                        "(e.g., 2019-12-02 1800 or 02-12-2019 1800)."
        );
    }

}
