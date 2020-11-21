package dk.sdu.worldoftrash.game.gui.controllers;

import dk.sdu.worldoftrash.game.gui.SceneName;
import dk.sdu.worldoftrash.game.gui.startmenu.MenuItem;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import javafx.util.Pair;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class StartController extends BaseController implements Initializable {

    @FXML
    private Pane rootPane;

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    private double scale = 1.5;

    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Play", () -> {getSceneManager().changeScene(SceneName.GAME_SCENE);}),
            new Pair<String, Runnable>("Game Options", () -> {}),
            new Pair<String, Runnable>("Tutorial", () -> {}),
            new Pair<String, Runnable>("Credits", () -> {}),
            new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    );

    private VBox menuBox = new VBox(-5);
    private Line line;

    private void createContent() {
        Scale scale = new Scale(this.scale, this.scale);
        menuBox.getTransforms().add(scale);

        addBackground();
        
        // placement of the menu buttons X decide which side Y decide height (up and down)
        double lineX = 50;
        double lineY = HEIGHT / 3 - 30 ;

        addLine(lineX, lineY);
        addMenu(lineX + 5 * this.scale, lineY + 5 * this.scale);

        startAnimation();
    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/game_menu_background.png")));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        rootPane.getChildren().add(imageView);
    }

    private void addLine(double x, double y) {
        //length of the slider next to the title buttons.
        line = new Line(x, y, x, y + 200 * scale);
        line.setStrokeWidth(3 * scale);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        rootPane.getChildren().add(line);
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        rootPane.getChildren().add(menuBox);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createContent();
    }
    
}
