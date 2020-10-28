package worldofzuul;

import worldofzuul.items.Item;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room 
{
    private String description;
    private HashMap<String, Room> exits;

    private ArrayList<Item> items;

    public Room(String description) 
    {
        this.items = new ArrayList<Item>();
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Returns a String representation of the exits in the room.
     * @return The String representation of the room exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Returns item in room by given name. If item isn't found, it returns null.
     * @param itemName Name of item to search for in room.
     * @return Returns Item object of item found in room by specified name. Returns null if not found.
     */
    public Item getItemByName(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(itemName)) {
                return items.get(i);
            }
        }

        return null;
    }

    public Room getExit(String direction) 
    {
        return this.exits.get(direction);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

}

