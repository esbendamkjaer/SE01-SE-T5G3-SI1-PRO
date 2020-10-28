package dk.sdu.worldoftrash.shared;

import java.util.ArrayList;
import java.util.UUID;

public class ScoreData {

    private UUID uuid;

    private ArrayList<LevelData> levels;

    public ScoreData() {
        uuid = UUID.randomUUID();
        levels = new ArrayList<LevelData>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void addLevelData(LevelData level) {
        levels.add(level);
    }

    public LevelData getLevelDataAt(int index) {
        return levels.get(index);
    }

}
