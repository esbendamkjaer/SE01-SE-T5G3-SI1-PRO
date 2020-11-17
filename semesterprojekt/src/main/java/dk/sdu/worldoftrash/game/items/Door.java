package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.rooms.Room;

public class Door extends Item {

    private Room exit;

    public Door(Game game, String name, Room exit) {
        super(game, name);
        this.exit = exit;
    }
}
