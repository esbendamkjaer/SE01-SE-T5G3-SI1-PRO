package worldofzuul;
import worldofzuul.items.Item;
import java.util.ArrayList;

public class Inventory {

    private final int max_size;
    private ArrayList<Item> items = new ArrayList<Item>();

    public Inventory(int max_size) {
        this.max_size = max_size;
    }

    /**
     * Stores a given item in inventory, if it is not full.
     * @param item
     * @return
     */
    public boolean storeItem(Item item){
        if (isFull()) return false;

        items.add(item);

        return true;
    }

    /**
     * Returns whether or not the inventory is full.
     * @return whether or not the inventory is full.
     */
    public boolean isFull() {
        return items.size() >= max_size;
    }
}
