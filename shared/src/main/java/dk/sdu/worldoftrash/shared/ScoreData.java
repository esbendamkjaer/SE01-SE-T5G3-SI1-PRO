package dk.sdu.worldoftrash.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScoreData {

    private UUID uuid;

    private List<LevelData> levels;

    public ScoreData() {
        uuid = UUID.randomUUID();
        levels = new ArrayList<LevelData>();
    }

    @JsonIgnore
    public String getUuid() {
        return uuid.toString();
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
