package dk.sdu.worldoftrash.game.domain.scoresystem;

import dk.sdu.worldoftrash.game.data.IDataAccess;
import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.LevelHandler;
import dk.sdu.worldoftrash.game.domain.SortingListener;
import dk.sdu.worldoftrash.game.domain.WasteType;
import dk.sdu.worldoftrash.game.domain.items.Waste;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScoreSystem {

    private List<SortingListener> sortingListeners;

    private IntegerProperty scoreProperty;

    private int wasteCount;

    private IDataAccess dataAccess;

    private ScoreData scoreData;

    private LevelHandler levelHandler;

    private Game game;

    public ScoreSystem(Game game, int winningCondition) {
        this.dataAccess = new IDataAccess() {
            @Override
            public void saveData(ScoreData data) {
                System.err.println("DataAccess is disabled.");
            }
        };

        this.game = game;

        this.scoreData = new ScoreData(UUID.randomUUID());

        this.scoreProperty = new SimpleIntegerProperty(0);

        this.levelHandler = new LevelHandler(game, winningCondition);

        sortingListeners = new ArrayList<>();
    }

    /**
     * Add points to score.
     * @param points Number of points to add.
     */
    public void addPoints(int points) {
        setScore(getScore() + points);
    }

    /**
     * Save ScoreData
     */
    public void saveData() {
        dataAccess.saveData(scoreData);
    }

    /**
     * Increment the total number of sorted waste.
     * @param wasteType WasteType of just sorted Waste.
     */
    public void incrementWasteCount(WasteType wasteType) {
        LevelData levelData = getLevelDataByName(getLevelHandler().getCurrentLevelName());
        levelData.incrementWasteCount(wasteType);

        wasteCount++;
        levelHandler.updateCondition(wasteCount);
    }

    /**
     * Get LevelData object of level by given name.
     * If a level by the given name is not found, an entry is created and returned.
     * @param name Name of level
     * @return LevelData object
     */
    public LevelData getLevelDataByName(String name) {
        LevelData levelData = scoreData.getLevelDataByName(name);

        if (levelData == null) {
            levelData = new LevelData();
            scoreData.addLevelData(levelHandler.getCurrentLevelName(), levelData);
        }

        return levelData;
    }

    /**
     * Is called by containers, when a piece of waste is sorted.
     * @param waste Waste object that was sorted.
     * @param correct Whether it was sorted correctly or not.
     */
    public void onSort(Waste waste, boolean correct) {
        if (correct) {
            addPoints(waste.getPoints());

            if (!waste.isWronglySorted()) {
                LevelData levelData = getLevelDataByName(getLevelHandler().getCurrentLevelName());
                levelData.incrementCorrect(waste.getWasteType());
            }

            sortingListeners.forEach(SortingListener::onCorrect);

            incrementWasteCount(waste.getWasteType());
        } else {
            addPoints(-waste.getPoints());
            sortingListeners.forEach(SortingListener::onWrong);
        }
    }

    public void onRinse(boolean correct) {
        if (correct) {
            sortingListeners.forEach(SortingListener::onCorrectRinse);
        } else {
            sortingListeners.forEach(SortingListener::onWrongRinse);
        }
    }

    public void onWin() {
        sortingListeners.forEach(SortingListener::onWin);
    }

    public void addSortingListener(SortingListener sortingListener) {
        this.sortingListeners.add(sortingListener);
    }

    /**
     * Returns the total number of sorted waste.
     * @return Number of sorted waste.
     */
    public int getWasteCount() {
        return wasteCount;
    }

    public int getScore() {
        return scoreProperty.getValue();
    }

    public void setScore(int score) {
        getLevelDataByName(getLevelHandler().getCurrentLevelName()).setScore(score);
        this.scoreProperty.setValue(score);
    }

    public IntegerProperty getScoreProperty() {
        return this.scoreProperty;
    }

    public LevelHandler getLevelHandler() {
        return levelHandler;
    }

}
