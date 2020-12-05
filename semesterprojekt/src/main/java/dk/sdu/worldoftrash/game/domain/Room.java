package dk.sdu.worldoftrash.game.domain;

import com.google.gson.Gson;
import dk.sdu.worldoftrash.game.domain.items.Item;
import dk.sdu.worldoftrash.game.domain.items.Wall;
import dk.sdu.worldoftrash.game.domain.items.Waste;
import javafx.scene.image.Image;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Room 
{
    private String name;

    private List<Item> items;

    private Game game;

    private boolean locked;
    private Image background;

    public Room(Game game, String name)
    {
        this.name = name;
        this.items = new ArrayList<>();
        this.game = game;
        this.locked = false;
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

    public List<Item> getItems() {
        return items;
    }

    public void update(double delta) {
        for (Item item : items) {
            item.update(delta);
        }
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
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

    public Game getGame() {
        return game;
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

