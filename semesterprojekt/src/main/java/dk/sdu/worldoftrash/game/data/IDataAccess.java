package dk.sdu.worldoftrash.game.data;

import dk.sdu.worldoftrash.game.domain.scoresystem.ScoreData;

public interface IDataAccess {

    public abstract void saveData(ScoreData data);

}
