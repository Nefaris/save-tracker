import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ActivityLogger {
    public static void crateLog(Log log) {
        File logFile = new File(Reference.LOG_FILE_PATH);
        String logText = String.format("%s: %s %s\n", log.getLogType(), log.getDate(), log.getMessage());

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileUtils.write(logFile, logText, Charset.defaultCharset(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
