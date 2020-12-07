package dk.sdu.worldoftrash.game.domain;

public interface SortingListener {

    public abstract void onCorrect();

    public abstract void onWrong();

    public abstract void onCorrectRinse();

    public abstract void onWrongRinse();

    public abstract void onWin();

}
