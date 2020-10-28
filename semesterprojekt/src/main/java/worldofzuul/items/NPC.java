package worldofzuul.items;

import worldofzuul.Game;

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
