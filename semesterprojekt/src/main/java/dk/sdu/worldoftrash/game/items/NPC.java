package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.Game;

public class NPC extends Item {

    private String dialogue;

    public NPC(Game game, String name, String dialogue) {
        super(game, name);
        this.dialogue = dialogue;
    }

    public String getDialogue() {
        return dialogue;
    }
}
