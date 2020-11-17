package dk.sdu.worldoftrash.game.gui.controllers;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.gui.GameAnimationTimer;
import dk.sdu.worldoftrash.game.gui.Renderer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable {

    @FXML
    public Canvas gameCanvas;
    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.game = new Game(gameCanvas.getWidth(), gameCanvas.getHeight());

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
