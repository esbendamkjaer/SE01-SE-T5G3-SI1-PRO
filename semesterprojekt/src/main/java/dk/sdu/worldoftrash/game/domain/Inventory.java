package dk.sdu.worldoftrash.game.domain;
import dk.sdu.worldoftrash.game.domain.items.Item;
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
     * Searches inventory for given items
     * @param items Items to search for
     * @return Whether or not the inventory contains the given items
     */
    public boolean hasItems(Item... items) {
        return this.items.containsAll(List.of(items));
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
