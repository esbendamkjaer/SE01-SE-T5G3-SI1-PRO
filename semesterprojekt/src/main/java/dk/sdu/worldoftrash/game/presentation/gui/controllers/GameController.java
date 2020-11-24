package dk.sdu.worldoftrash.game.presentation.gui.controllers;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.TextLogArea;
import dk.sdu.worldoftrash.game.presentation.gui.GameAnimationTimer;
import dk.sdu.worldoftrash.game.presentation.gui.GameCanvas;
import dk.sdu.worldoftrash.game.presentation.gui.InventoryUI;
import dk.sdu.worldoftrash.game.presentation.gui.Renderer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable {

    @FXML
    private GameCanvas gameCanvas;

    @FXML
    private TilePane inventoryPane;

    @FXML
    private TextArea textLogArea;

    @FXML
    private TextArea itemDescriptionArea;

    @FXML
    private Label scoreLabel;

    private Game game;
    private InventoryUI inventoryUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        this.game = new Game(gameCanvas.getWidth(), gameCanvas.getHeight());

        scoreLabel.textProperty().bind(game.getScoreSystem().getScoreProperty().asString());

        inventoryUI = new InventoryUI(inventoryPane, itemDescriptionArea, game);

        TextLogArea textLogArea = new TextLogArea(this.textLogArea);
        game.setTextLogArea(textLogArea);

        game.printWelcome();

        Renderer renderer = new Renderer(this.gameCanvas);

        GameAnimationTimer timer = new GameAnimationTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();

                game.update(secondsSinceLastFrame);

                renderer.render(game.getCurrentRoom(), game.getPlayer());
            }
        };

        timer.start();

    }

}
