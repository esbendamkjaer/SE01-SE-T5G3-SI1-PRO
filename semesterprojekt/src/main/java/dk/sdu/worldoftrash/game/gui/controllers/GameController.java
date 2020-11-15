package dk.sdu.worldoftrash.game.gui.controllers;

import dk.sdu.worldoftrash.game.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable {

    @FXML
    private Canvas gameCanvas;

    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.game = new Game();

    }

}
