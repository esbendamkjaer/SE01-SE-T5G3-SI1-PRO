module semesterprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    exports dk.sdu.worldoftrash.game.data;
    opens dk.sdu.worldoftrash.game.data;
}