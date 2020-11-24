package dk.sdu.worldoftrash.game.domain.items;

import dk.sdu.worldoftrash.game.domain.Game;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Item {

    private String name;
    private Game game;

    private Image image;

    private float scale;
    private double width, height;

    private Point2D position;

    public Item(Game game, String name) {
        this.game = game;
        this.name = name;
        this.position = Point2D.ZERO;
        scale = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void update(float delta) {}

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public double getWidth() {
        return width * scale;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height * scale;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setPosition(double x, double y) {
        setPosition(new Point2D(x, y));
    }

    public double getX() {
        return getPosition().getX();
    }

    public double getY() {
        return getPosition().getY();
    }

    public void setX(double x) {
        setPosition(x, getY());
    }

    public void setY(double y) {
        setPosition(getX(), y);
    }

    public void fitToImage() {
        setHeight(image.getHeight());
        setWidth(image.getWidth());
    }

    /**
     * Automatically sets scale, to fit image to boundary box.
     * Ratio is kept.
     */
    public void autoScale() {
        setScale((float) Math.min(getHeight() / (image.getHeight() * scale), getWidth() / (image.getWidth() * scale)));
    }

    public Rectangle2D getBoundaryBox() {
        return new Rectangle2D(getX(), getY(), getWidth(), getHeight());
    }

    public void moveFromMid(Point2D point) {
        setPosition(point.subtract(getWidth() / 2, getHeight() / 2));
    }

    public Point2D getMidPoint() {
        return new Point2D(getX() + getWidth() / 2, getY() + getHeight() / 2);
    }

    public String getDescription() {
        return
                "Name: " + getName();
    }
}
