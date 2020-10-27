package worldofzuul;

import worldofzuul.items.Item;
import worldofzuul.items.Waste;

public class Sink extends Item {

    public Sink(String name) {
        super(name);
    }

    /**
     * Changes the clean state of a given waste to true.
     * @param waste Waste object to clean.
     * @return Returns false if the waste was already clean, otherwise true.
     */
    public boolean washItem(Waste waste) {
        if (waste.isClean()) {
            return false;
        }
        waste.setClean(true);
        return true;
    }

}
