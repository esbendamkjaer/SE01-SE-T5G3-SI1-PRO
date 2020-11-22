package dk.sdu.worldoftrash.game.gui;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.items.Item;
import dk.sdu.worldoftrash.game.items.Player;
import dk.sdu.worldoftrash.game.items.npcs.Interactable;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.util.List;

public class InventoryUI {

    private TilePane root;
    private Game game;
    private Player player;
    
    public InventoryUI(TilePane root, Game game) {
        this.game = game;
        this.player = game.getPlayer();
        this.root = root;

        game.getPlayer().getInventory().getItems().addListener((ListChangeListener<Item>) c -> {
            onInventoryChange((Change<Item>) c);
        });
    }

    public void onInventoryChange(Change<Item> c) {
        root.getChildren().clear();

        ObservableList<? extends Item> items = c.getList();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Button button = new Button(item.getName());
            button.setMaxHeight(Double.MAX_VALUE);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setGraphic(new ImageView(item.getImage()));
            button.setOnAction(event -> {onButtonEvent(event);});

            root.getChildren().add(button);
            //inventoryGrid.add(button, i % 3, i / 3);
        }
    }

    /**
     * Is called when an item in the inventory is clicked.
     * @param event Event object.
     */
    private void onButtonEvent(ActionEvent event) {
        Button button = (Button) event.getSource();
        int index = button.getParent().getChildrenUnmodifiable().indexOf(button);

        List<Interactable> colliding = game.getCollisionsWithPlayer(Interactable.class);

        if (colliding.size() <= 0) {
            Item item = player.getInventory().getItemAt(index);
            player.getInventory().removeItemAt(index);
            item.moveFromMid(game.getPlayer().getMidPoint());
            game.getCurrentRoom().addItem(item);

            return;
        }


        colliding.get(0).giveItem(player.getInventory().getItemAt(index), game.getPlayer());

        System.out.println(index);
    }
}
