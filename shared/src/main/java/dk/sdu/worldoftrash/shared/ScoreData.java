package dk.sdu.worldoftrash.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScoreData {

    private UUID uuid;

    private List<LevelData> levels;

    public ScoreData() {
        levels = new ArrayList<LevelData>();
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

    public void addLevelData(LevelData level) {
        levels.add(level);
    }

    public LevelData getLevelDataAt(int index) {
        return levels.get(index);
    }

    public List<LevelData> getLevels() {
        return levels;
    }

    public void setLevels(List<LevelData> levels) {
        this.levels = levels;
    }


}
