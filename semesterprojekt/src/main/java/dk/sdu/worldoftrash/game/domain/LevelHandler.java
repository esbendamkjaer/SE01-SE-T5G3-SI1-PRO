package dk.sdu.worldoftrash.game.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LevelHandler {

    private Map<Room, Integer> wasteConditions;
    private Room currentLevel;

    private int winningCondition;

    private Game game;

    public LevelHandler(Game game, int winningCondition) {
        this.wasteConditions = new HashMap<>();
        this.winningCondition = winningCondition;
        this.game = game;
    }

    /**
     * Add level to level handler and an unlocking condition.
     * @param room Level ot add.
     * @param wasteCondition Waste count condition to be met.
     */
    public void addLevel(Room room, int wasteCondition) {
        if (wasteCondition <= 0) {
            this.currentLevel = room;
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
                getGame().getTextPrinter().printText("Congratulations!");
                getGame().getTextPrinter().printText("You have now sorted enough waste to unlock " + level.getName() + "!");

                setCurrentLevel(entry.getKey());

                game.getScoreSystem().saveData();

                iterator.remove();
            }
        }

        if (wasteCount >= winningCondition) {
            getGame().getTextPrinter().printText("Congratulations, you have won the game by removing and sorting all the trash. Good job!");
            getGame().getTextPrinter().printText(String.format("You ended with a score of %d points.\n", game.getScoreSystem().getScore()));
            getGame().getTextPrinter().printText("Hope you learnt something about sorting trash");
            getGame().getTextPrinter().printText("To close down the game type in the command ‘quit’");
            getGame().getScoreSystem().onWin();
            game.getScoreSystem().saveData();
        }
    }

    public Game getGame() {
        return this.game;
    }

    public int getWinningCondition() {
        return winningCondition;
    }

    public void setWinningCondition(int winningCondition) {
        this.winningCondition = winningCondition;
    }

    public String getCurrentLevelName() {
        return currentLevel.getName();
    }

    public void setCurrentLevel(Room level) {
        this.currentLevel = level;
    }
}
