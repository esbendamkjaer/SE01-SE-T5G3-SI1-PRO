package dk.sdu.worldoftrash.game.presentation.views;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.presentation.TextLogArea;
import dk.sdu.worldoftrash.game.presentation.GameAnimationTimer;
import dk.sdu.worldoftrash.game.presentation.GameCanvas;
import dk.sdu.worldoftrash.game.presentation.Renderer;
import dk.sdu.worldoftrash.game.presentation.InventoryUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameView extends BaseView implements Initializable {

    @FXML
    private StackPane gameStackPane;

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
        game.setTextPrinter(textLogArea);

        game.printWelcome();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/response.fxml"));
            gameStackPane.getChildren().add(fxmlLoader.load());
            game.getScoreSystem().addSortingListener(fxmlLoader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Renderer renderer = new Renderer(this.gameCanvas);

        GameAnimationTimer timer = new GameAnimationTimer() {
            @Override
            public void update(double secondsSinceLastFrame) {
                renderer.prepare();

                game.update(secondsSinceLastFrame);

                renderer.render(game);
            }
        };
        timer.start();
    }

}
