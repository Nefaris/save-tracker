import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class GameProfileManager {
    public static GameProfile[] getAllProfiles() throws IOException {
        List<String> rawProfiles = FileUtils.readLines(new File("./preferences.txt"), Charset.defaultCharset());
        GameProfile[] profiles = new GameProfile[rawProfiles.size()];

        for (int i = 0; i < rawProfiles.size(); i++) {
            String[] p = rawProfiles.get(i).split(";");
            String name = p[0];
            String local = p[1];
            String cloud = p[2];
            profiles[i] = new GameProfile(name, local, cloud);
        }

        return profiles;
    }
}
