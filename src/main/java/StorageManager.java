import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class StorageManager {
    public static Log upload(String localStoragePath, String cloudStoragePath) {
        File localDir = new File(localStoragePath);
        File cloudDir = new File(cloudStoragePath);
        Log log;

        try {
            FileUtils.cleanDirectory(cloudDir);
            FileUtils.copyDirectory(localDir, cloudDir);
            log = new Log(LogType.SUCCES, "Uploaded to cloud storage");
        } catch (IOException e) {
            log = new Log(LogType.ERROR, "Unable to upload files");
            e.printStackTrace();
        }

        return log;
    }

    public static Log download(String localStoragePath, String cloudStoragePath) {
        File localDir = new File(localStoragePath);
        File cloudDir = new File(cloudStoragePath);
        Log log;

        try {
            FileUtils.cleanDirectory(localDir);
            FileUtils.copyDirectory(cloudDir, localDir);
            log = new Log(LogType.SUCCES, "Downloaded from cloud storage");
        } catch (IOException e) {
            log = new Log(LogType.ERROR, "Unable to download files");
            e.printStackTrace();
        }

        return log;
    }
}
