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
        wasteConditions = new HashMap<>();
    }

    public void addLevel(Room room, int wasteCondition) {
        if (wasteCondition <= 0) {
            currentLevel = room.getName();
        } else {
            room.setLocked(true);
            this.wasteConditions.put(room, wasteCondition);
        }
    }

    public void updateCondition(int wasteCount) {
        Iterator<Entry<Room, Integer>> iterator = wasteConditions.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<Room, Integer> entry = iterator.next();

            if (entry.getKey().isLocked() && wasteCount >= entry.getValue()) {
                entry.getKey().setLocked(false);
                System.out.println("You have now unlocked " + entry.getKey().getName());
                setCurrentLevel(entry.getKey().getName());

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
