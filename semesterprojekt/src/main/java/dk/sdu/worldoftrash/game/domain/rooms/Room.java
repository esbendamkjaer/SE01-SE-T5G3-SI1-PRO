package dk.sdu.worldoftrash.game.domain.rooms;

import com.google.gson.Gson;
import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.items.Item;
import dk.sdu.worldoftrash.game.domain.items.Key;
import dk.sdu.worldoftrash.game.domain.items.Wall;
import dk.sdu.worldoftrash.game.domain.items.Waste;
import javafx.scene.image.Image;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Room 
{
    private String name;
    private HashMap<String, Room> exits;

    private List<Item> items;

    public Game getGame() {
        return game;
    }

    private Game game;
    private boolean locked;

    private Image background;

    public Room(Game game, String name)
    {
        this.name = name;

        this.items = new ArrayList<Item>();
        this.exits = new HashMap<String, Room>();
        this.game = game;
        this.locked = false;
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
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

    /**
     * Returns all waste objects in the room.
     * @return List of Waste objects.
     */
    public List<Waste> getWaste() {
        List<Waste> waste = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Waste) {
                waste.add((Waste) item);
            }
        }
        return waste;
    }

    /**
     * Returns all key objects in the room.
     * @return List of Key objects.
     */
    public List<Key> getKeys() {
        List<Key> keys = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Key) {
                keys.add((Key) item);
            }
        }
        return keys;
    }

    public List<Item> getItems() {
        return items;
    }

    public void update(float delta) {
        for (Item item : items) {
            item.update(delta);
        }
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

    public boolean unlock() {
        return !locked;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public void loadWalls(String path) {
        Map map = new Gson().fromJson(new InputStreamReader(getClass().getResourceAsStream(path)), Map.class);

        for(Map m : (List<Map>) map.get("objects")) {
            Wall wall = new Wall(getGame(),
                    (double) m.get("x"), (double) m.get("y"),
                    (double) m.get("width"), (double) m.get("height"));

            addItem(wall);
        }

    }

}

