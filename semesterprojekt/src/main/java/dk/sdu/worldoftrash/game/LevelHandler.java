package dk.sdu.worldoftrash.game;

import dk.sdu.worldoftrash.game.rooms.Room;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LevelHandler {

    private String currentLevel;
    private Map<Room, Integer> wasteConditions;

    public LevelHandler() {
        this.wasteConditions = new HashMap<>();
    }

    /**
     * Add level to level handler and an unlocking condition.
     * @param room Level ot add.
     * @param wasteCondition Waste count condition to be met.
     */
    public void addLevel(Room room, int wasteCondition) {
        if (wasteCondition <= 0) {
            currentLevel = room.getName();
        } else {
            room.setLocked(true);
            this.wasteConditions.put(room, wasteCondition);
        }
    }

    /**
     * Unlocks levels if the new waste count satisfies any level's waste condition.
     * @param wasteCount Waste count to test against conditions
     */
    public void updateCondition(int wasteCount) {
        Iterator<Entry<Room, Integer>> iterator = wasteConditions.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<Room, Integer> entry = iterator.next();
            Room level = entry.getKey();

            if (level.isLocked() && wasteCount >= entry.getValue()) {
                level.setLocked(false);
                System.out.println("You have now unlocked " + level.getName());

                setCurrentLevel(entry.getKey().getName());

                level.getGame().getScoreSystem().uploadData();

                iterator.remove();
            }
        }
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String level) {
        this.currentLevel = level;
    }
}
