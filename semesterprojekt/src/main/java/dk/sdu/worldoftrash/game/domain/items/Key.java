package dk.sdu.worldoftrash.game.domain.items;

import dk.sdu.worldoftrash.game.domain.Game;

public class Key extends Item implements Pickupable {

    public Key(Game game, String name) {
        super(game, name);
    }

    @Override
    public boolean pickup() {
        return true;
    }

    @Override
    public void interact(Player player) {
        player.pickup(this);
    }

    @Override
    public void giveItem(Item item, Player player) {

    }

    @Override
    public String getDescription() {
        return super.getDescription()
                + "\nSpecial item, not waste.";
    }
}
