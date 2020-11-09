package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.Game;

public class Item {

    private String name;
    private Game game;

    public Item(Game game, String name) {
        this.game = game;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
