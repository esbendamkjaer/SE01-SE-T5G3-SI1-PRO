package worldofzuul.items;

public class NPC extends Item {

    private String dialogue;

    public NPC(String dialogue) {
        this.dialogue = dialogue;
    }

    public String getDialogue() {
        return dialogue;
    }
}
