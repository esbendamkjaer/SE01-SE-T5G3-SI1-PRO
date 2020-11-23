package dk.sdu.worldoftrash.game.gui.startmenu;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class WotTittle extends Pane {
    private Text text;

    public WotTittle(String name) {
        String spread = "";
        for (char c : name.toCharArray()) {
            spread += c + " ";
        }

        text = new Text(spread);
        //text.setFont(Font.loadFont(ChronApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 48));
        text.setFill(Color.WHITE);
        text.setEffect(new DropShadow(30, Color.BLACK));

        getChildren().addAll(text);
    }

    public double getTitleWidth() {
        return text.getLayoutBounds().getWidth();
    }

    public double getTitleHeight() {
        return text.getLayoutBounds().getHeight();
    }
}
