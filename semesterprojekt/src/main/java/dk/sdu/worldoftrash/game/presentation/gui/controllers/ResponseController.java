package dk.sdu.worldoftrash.game.presentation.gui.controllers;

import dk.sdu.worldoftrash.game.dal.ImageIO;
import dk.sdu.worldoftrash.game.domain.SortingListener;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ResponseController extends BaseController implements SortingListener, Initializable {

    private Image correct, wrong;

    private ScaleTransition scaleTransition;

    @FXML
    private StackPane responseRoot;

    @FXML
    private ImageView responseImage;

    @Override
    public void onCorrect() {
        responseImage.setImage(correct);
        scaleTransition();
    }

    @Override
    public void onWrong() {
        responseImage.setImage(wrong);
        scaleTransition();
    }

    public void scaleTransition() {
        responseRoot.setScaleX(0);
        responseRoot.setScaleY(0);

        scaleTransition.setOnFinished(event -> {
            responseRoot.setVisible(false);
        });

        responseRoot.setVisible(true);
        scaleTransition.playFromStart();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        responseRoot.setVisible(false);

        scaleTransition = new ScaleTransition(Duration.seconds(2), responseRoot);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);

        correct = ImageIO.load("/images/icons/thumbs-up-solid.png");
        wrong = ImageIO.load("/images/icons/thumbs-down-solid.png");

    }
}
