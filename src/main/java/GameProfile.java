public class GameProfile {
    private String name;
    private String localPath;
    private String cloudPath;

    public GameProfile(String name, String localPath, String cloudPath) {
        this.name = name;
        this.localPath = localPath;
        this.cloudPath = cloudPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getCloudPath() {
        return cloudPath;
    }

    public void setCloudPath(String cloudPath) {
        this.cloudPath = cloudPath;
    }

    @Override
    public String toString() {
        return name;
    }
}
