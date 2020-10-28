package dk.sdu.worldoftrash.game;

public class PointSystem {

    private int points;

    public void addPoints(int points) {
        setPoints(getPoints() + points);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
