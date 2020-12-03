package dk.sdu.worldoftrash.game.presentation;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.items.Interactable;
import dk.sdu.worldoftrash.game.domain.items.Item;
import dk.sdu.worldoftrash.game.domain.items.Player;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.util.List;

public class InventoryUI {

    private TilePane root;
    private Game game;
    private TextArea itemDescriptionArea;
    private Player player;

    private double iconPixelSize = 64;

    public InventoryUI(TilePane inventoryTiles, TextArea itemDescriptionArea, Game game) {
        this.game = game;
        this.itemDescriptionArea = itemDescriptionArea;
        this.player = game.getPlayer();
        this.root = inventoryTiles;

        game.getPlayer().getInventory().getItems().addListener((ListChangeListener<Item>) c -> {
            onInventoryChange((Change<Item>) c);
        });
    }

    /**
     * Is called when an Item is added to the player's inventory.
     * @param event Change event object.
     */
    public void onInventoryChange(Change<Item> event) {
        root.getChildren().clear();

        ObservableList<? extends Item> items = event.getList();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            Button button = new Button();
            button.setMaxHeight(Double.MAX_VALUE);
            button.setMaxWidth(Double.MAX_VALUE);

            Image image = item.getImage();
            ImageView icon = new ImageView(image);

            double ratio = image.getWidth() / image.getHeight();
            if (image.getWidth() > image.getHeight()) {
                icon.setFitWidth(iconPixelSize);
                icon.setFitHeight(iconPixelSize / ratio);
            } else {
                icon.setFitHeight(iconPixelSize);
                icon.setFitWidth(iconPixelSize * ratio);
            }
            button.setGraphic(icon);
            button.setOnAction(e -> {onButtonEvent(e);});

            button.hoverProperty().addListener((observable, oldValue, newValue) -> {
                onButtonHoverChange(button, newValue);
            });

            root.getChildren().add(button);
        }
    }

    /**
     * Is called on mouse enter and exit hover.
     * @param button Button the mouse is hovering.
     * @param newValue True if entered.
     */
    private void onButtonHoverChange(Button button, Boolean newValue) {
        if (newValue) {
            int index = button.getParent().getChildrenUnmodifiable().indexOf(button);
            Item item = player.getInventory().getItemAt(index);

            itemDescriptionArea.setText(
                    item.getDescription()
            );
        } else {
            itemDescriptionArea.clear();
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
    }
}
