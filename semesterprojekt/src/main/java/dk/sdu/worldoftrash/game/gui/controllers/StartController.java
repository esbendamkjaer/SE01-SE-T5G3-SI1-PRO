package dk.sdu.worldoftrash.game.gui.controllers;

import dk.sdu.worldoftrash.game.gui.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController extends BaseController {

    @FXML
    private Button startBtn;

    public void onStartBtnAction(ActionEvent actionEvent) {

        getSceneManager().changeScene(SceneName.GAME_SCENE);

    }
}
