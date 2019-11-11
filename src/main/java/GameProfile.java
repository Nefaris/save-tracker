public class GameProfile {
    private String name;
    private String localStoragePath;
    private String cloudStoragePath;

    public GameProfile(String name, String localPath, String cloudPath) {
        this.name = name;
        this.localStoragePath = localPath;
        this.cloudStoragePath = cloudPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalStoragePath() {
        return localStoragePath;
    }

    public void setLocalStoragePath(String localStoragePath) {
        this.localStoragePath = localStoragePath;
    }

    public String getCloudStoragePath() {
        return cloudStoragePath;
    }

    public void setCloudStoragePath(String cloudStoragePath) {
        this.cloudStoragePath = cloudStoragePath;
    }

    @Override
    public String toString() {
        return name;
    }
}
