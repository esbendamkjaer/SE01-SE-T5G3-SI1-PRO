package dk.sdu.worldoftrash.game;
import dk.sdu.worldoftrash.game.items.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Inventory {

    private final int MAX_SIZE;
    private ObservableList<Item> items;

    public Inventory(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
        items = FXCollections.observableArrayList();
    }

    /**
     * Stores a given item in inventory, if it is not full.
     * @param item Item to store in inventory.
     * @return Whether or not the item could fit in the inventory.
     */
    public boolean storeItem(Item item){
        if (isFull()) return false;

        items.add(item);

        return true;
    }

    /**
     * Removes given item object from inventory.
     * @param item Item to remove from inventory.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Removed item at given item.
     * @param index Index of item in inventory.
     */
    public void removeItemAt(int index) {
        items.remove(index);
    }


    /**
     * Searches inventory for item with given name and returns true if found.
     * @param name Name of item to search for.
     * @return True of inventory contains item with given name, otherwise false.
     */
    public boolean hasItemWithName(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches inventory for given items
     * @param items Items to search for
     * @return Whether or not the inventory contains the given items
     */
    public boolean hasItems(Item... items) {
        return this.items.containsAll(List.of(items));
    }

    /**
     * Get an item from the inventory specified by given name.
     * @param name Name of item to retrieve.
     * @return Returns the Item object. If none found in the inventory, it returns null.
     */
    public Item getItemByName(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Returns whether or not the inventory is full.
     * @return whether or not the inventory is full.
     */
    public boolean isFull() {
        return items.size() >= MAX_SIZE;
    }

    public Item getItemAt(int index) {
        return items.get(index);
    }

    public ObservableList<Item> getItems() {
        return items;
    }
}
