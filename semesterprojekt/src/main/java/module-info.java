module semesterprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.net.http;
    requires com.google.gson;

    exports dk.sdu.worldoftrash.game.presentation.gui;
    exports dk.sdu.worldoftrash.game.presentation.gui.startmenu;
    exports dk.sdu.worldoftrash.game.presentation.gui.fxcontrollers;
    opens dk.sdu.worldoftrash.game.presentation.gui.fxcontrollers;

    exports dk.sdu.worldoftrash.game.domain.scoresystem;
    opens dk.sdu.worldoftrash.game.domain.scoresystem;
}