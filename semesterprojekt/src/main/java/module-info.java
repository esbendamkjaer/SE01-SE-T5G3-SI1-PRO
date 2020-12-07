module semesterprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.net.http;
    requires com.google.gson;

    exports dk.sdu.worldoftrash.game.presentation;
    exports dk.sdu.worldoftrash.game.presentation.views;
    opens dk.sdu.worldoftrash.game.presentation.views;

    exports dk.sdu.worldoftrash.game.domain.scoresystem;
    opens dk.sdu.worldoftrash.game.domain.scoresystem;
}