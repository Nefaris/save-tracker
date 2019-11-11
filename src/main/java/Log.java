import java.util.Date;

public class Log {
    private final LogType type;
    private final String message;
    private final Date date;

    public Log(LogType type, String message) {
        this.type = type;
        this.message = message;
        this.date = new Date();
    }

    public LogType getLogType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s\n", type, date, message);
    }
}
