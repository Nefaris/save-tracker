import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GameProfileStorage {
    private List<GameProfile> gameProfiles = new ArrayList<>();

    public void addGameProfile(GameProfile gameProfile) {
        gameProfiles.add(gameProfile);
    }

    public void removeGameProfile(int index) {
        gameProfiles.remove(index);
    }

    public void removeGameProfile(String name) {
        for (GameProfile gameProfile : gameProfiles) {
            if (gameProfile.getName().equals(name)) {
                gameProfiles.remove(gameProfile);
                return;
            }
        }
    }

    public GameProfile getGameProfile(int index) {
        if (index >= 0 && index < gameProfiles.size()) {
            return gameProfiles.get(index);
        }

        return null;
    }

    public GameProfile getGameProfile(String name) {
        for (GameProfile gameProfile : gameProfiles) {
            if (gameProfile.getName().equals(name)) {
                return gameProfile;
            }
        }

        return null;
    }

    public List<GameProfile> getAlleGameProfiles() {
        return gameProfiles;
    }

    public void saveStorage() throws IOException {
        String json = new Gson().toJson(gameProfiles);
        FileUtils.writeStringToFile(new File(Reference.PREFERENCES_PATH), json, Charset.defaultCharset());
    }

    public void loadStorage() throws IOException {
        String json = FileUtils.readFileToString(new File(Reference.PREFERENCES_PATH), Charset.defaultCharset());
        Type listType = new TypeToken<ArrayList<GameProfile>>() {
        }.getType();
        gameProfiles = new Gson().fromJson(json, listType);
    }
}
