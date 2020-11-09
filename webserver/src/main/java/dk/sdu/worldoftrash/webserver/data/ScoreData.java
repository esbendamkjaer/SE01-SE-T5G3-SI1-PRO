package dk.sdu.worldoftrash.webserver.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreData {

    private UUID uuid;

    private Map<String, LevelData> levels;

    public ScoreData() {
        levels = new HashMap<String, LevelData>();
    }

    public ScoreData(UUID uuid) {
        this();
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid.toString();
    }

    public void setUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public void addLevelData(String name, LevelData level) {
        levels.put(name, level);
    }

    public LevelData getLevelDataByName(String name) {
        return levels.get(name);
    }

    public Map<String, LevelData> getLevels() {
        return levels;
    }

    public void setLevels(Map<String, LevelData> levels) {
        this.levels = levels;
    }
}