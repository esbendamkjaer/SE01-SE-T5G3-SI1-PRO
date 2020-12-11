package dk.sdu.worldoftrash.game.presentation.views;

import dk.sdu.worldoftrash.game.domain.Img;
import dk.sdu.worldoftrash.game.domain.SortingListener;
import dk.sdu.worldoftrash.game.presentation.Sound;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ResponsePopup extends BaseView implements SortingListener, Initializable {

    private Image thumbsUp, thumbsDown, crown;

    private ScaleTransition scaleTransition;

    @FXML
    private StackPane responseRoot;

    @FXML
    private ImageView responseImage;

    @Override
    public void onCorrect() {
        responseImage.setImage(thumbsUp);
        scaleTransition();
        Sound.playSound("/sounds/ding.mp3");
    }

    @Override
    public void onWrong() {
        responseImage.setImage(thumbsDown);
        scaleTransition();
        Sound.playSound("/sounds/buzz.mp3");
    }

    @Override
    public void onWin() {
        responseImage.setImage(crown);
        scaleTransition();
        Sound.playSound("/sounds/win_sound.mp3");
    }

    @Override
    public void onCorrectRinse() {
        responseImage.setImage(thumbsUp);
        scaleTransition();
    }

    @Override
    public void onWrongRinse() {
        responseImage.setImage(thumbsDown);
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

        thumbsUp = Img.load("/images/icons/thumbs-up-solid.png");
        thumbsDown = Img.load("/images/icons/thumbs-down-solid.png");
        crown = Img.load("/images/icons/crown_solid.png");

    }
}
