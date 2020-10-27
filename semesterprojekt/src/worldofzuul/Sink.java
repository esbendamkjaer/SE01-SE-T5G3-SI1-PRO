package worldofzuul;

import worldofzuul.items.Item;
import worldofzuul.items.Waste;

public class Sink extends Item {

    public Sink(String name) {
        super(name);
    }

    public boolean washItem(Waste waste) {
        if (waste.isClean()) {
            return false;
        }
        waste.setClean(true);
        return false;
    }

}
