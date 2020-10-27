package worldofzuul.items;

public class NPC extends Item {

    private String dialogue;

    public NPC(String name, String dialogue) {
        super(name);
        this.dialogue = dialogue;
    }

    public String getDialogue() {
        return dialogue;
    }
}
