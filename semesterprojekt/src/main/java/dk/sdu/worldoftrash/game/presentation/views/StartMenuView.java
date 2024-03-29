package dk.sdu.worldoftrash.game.presentation.views;

import dk.sdu.worldoftrash.game.presentation.MenuItem;
import dk.sdu.worldoftrash.game.presentation.SceneName;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class StartMenuView extends BaseView implements Initializable {

    @FXML
    private Pane rootPane;

    private double height = 720;
    private double scale = 1.2;

    private Map<String, Runnable> menuData = new HashMap<>() {{
        put("Play", () -> {getSceneManager().changeScene(SceneName.GAME_SCENE);});
        put("Exit to Desktop", Platform::exit);
    }};

    private HBox menuBox = new HBox(5);
    private VBox menuItemBox = new VBox(-5);
    private Line line;
    private MediaPlayer mediaPlayer;

    private void createContent() {
        Scale scale = new Scale(1, 1);
        menuBox.getTransforms().add(scale);

        rootPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            scale.setX((double) newValue / 720);
            scale.setY((double) newValue / 720);
        });

        // placement of the menu buttons X decide which side Y decide height (up and down)
        double menuX = 50;
        double menuY = 0;
        addLine(0, 0, 67);
        addMenu(menuX, menuY);

        startAnimation();
    }

    /**
     * Add the line to the start menu screen.
     * @param x x-coordinate of line start.
     * @param y y-coordinate of line start.
     * @param height length of line.
     */
    private void addLine(double x, double y, double height) {
        line = new Line(x, y, x, y + height);
        line.setStrokeWidth(3 * scale);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        menuBox.getChildren().add(line);
    }

    /**
     * Start start menu animations.
     */
    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuItemBox.getChildren().size(); i++) {
                Node n = menuItemBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    /**
     * Add menu and menu items to start screen.
     * @param x x-coordinate of menu.
     * @param y y-coordinate of menu.
     */
    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.translateYProperty().bind(rootPane.heightProperty().divide(3));

        menuData.forEach((k, v) -> {
            MenuItem item = new MenuItem(k);
            item.setOnAction(v);
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuItemBox.getChildren().addAll(item);
        });

        menuBox.getChildren().add(menuItemBox);
        rootPane.getChildren().add(menuBox);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createContent();

        Media media = new Media(getClass().getResource("/sounds/monkeys-spinning.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    
}
