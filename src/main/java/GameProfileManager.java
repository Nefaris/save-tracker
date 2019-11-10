import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GameProfileManager {
    public static List<GameProfile> getAllProfiles() throws IOException {
        File preferencesFile = new File(Reference.PREFERENCES_PATH);
        if (!preferencesFile.exists()) {
            preferencesFile.createNewFile();
        }
        List<String> rawProfiles = FileUtils.readLines(preferencesFile, Charset.defaultCharset());
        List<GameProfile> result = new ArrayList<>();

        for (String rawProfile : rawProfiles) {
            if (!rawProfile.equals("")) {
                String[] parts = rawProfile.split(";");
                String name = parts[0];
                String local = parts[1];
                String cloud = parts[2];
                result.add(new GameProfile(name, local, cloud));
            }
        }

        return result;
    }

    public static void saveProfiles(List<GameProfile> profilesToSave) throws IOException {
        List<String> lines = new ArrayList<>();
        for (GameProfile gameProfile : profilesToSave) {
            lines.add(String.format("%s;%s;%s", gameProfile.getName(), gameProfile.getLocalPath(), gameProfile.getCloudPath()));
        }
        FileUtils.writeLines(new File(Reference.PREFERENCES_PATH), lines, false);
    }
}
