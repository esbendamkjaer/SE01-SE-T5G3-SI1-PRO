package dk.sdu.worldoftrash.game.domain.items;

import dk.sdu.worldoftrash.game.domain.Game;

public class Wall extends Item {

    public Wall(Game game, double x, double y, double width, double height) {
        super(game, "wall");
        setPosition(x, y);
        setWidth(width);
        setHeight(height);
    }

}
