package dk.sdu.worldoftrash.game.domain.scoresystem;

import dk.sdu.worldoftrash.game.data.DataAccess;
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

    private DataAccess dataAccess;

    private ScoreData scoreData;

    private LevelHandler levelHandler;

    private Game game;

    public ScoreSystem(Game game, int winningCondition) {
        this.dataAccess = new DataAccess() {
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
        LevelData levelData = scoreData.getLevelDataByName(levelHandler.getCurrentLevelName());

        if (levelData == null) {
            levelData = new LevelData();
            scoreData.addLevelData(levelHandler.getCurrentLevelName(), levelData);
        }

        return levelData;
    }

    /**
     * Is called by containers, when some waste was correctly sorted.
     * @param waste Waste object that was correctly sorted.
     */
    public void onCorrect(Waste waste) {
        addPoints(waste.getPoints());

        if (!waste.isWronglySorted()) {
            LevelData levelData = getLevelDataByName(getLevelHandler().getCurrentLevelName());
            levelData.incrementCorrect(waste.getWasteType());
        }

        sortingListeners.forEach(SortingListener::onCorrect);

        incrementWasteCount(waste.getWasteType());
    }


    /**
     * Is called by containers, when some waste was wrongly sorted.
     * @param waste Waste object that was wrongly sorted.
     */
    public void onWrong(Waste waste) {
        addPoints(-waste.getPoints());
        sortingListeners.forEach(SortingListener::onWrong);
    }

    public void onCorrectRinse() {
        sortingListeners.forEach(SortingListener::onCorrectRinse);
    }

    public void onWrongRinse() {
        sortingListeners.forEach(SortingListener::onWrongRinse);
    }

    public void onWin() {
        sortingListeners.forEach(SortingListener::onWin);
    }

    public void addSortingListener(SortingListener sortingListener) {
        this.sortingListeners.add(sortingListener);
    }

    public void removeSortingListener(SortingListener sortingListener) {
        this.sortingListeners.remove(sortingListener);
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
