package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.Game;

public class Key extends Item implements Pickupable {

    public Key(Game game, String name) {
        super(game, name);
    }

    @Override
    public boolean pickup() {
        return true;
    }
}
