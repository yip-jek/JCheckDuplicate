import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Global {
    public static final String YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMISS = "yyyyMMddHHmmss";

    public static String currentDateTimeFormat(String fmt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fmt);
        return LocalDateTime.now().format(formatter);
    }

    public static String currentDateTimeString() {
        return currentDateTimeFormat(YYYY_MM_DD_HH_MI_SS);
    }
}
