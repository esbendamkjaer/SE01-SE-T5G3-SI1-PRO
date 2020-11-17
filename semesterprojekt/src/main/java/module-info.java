module semesterprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    exports dk.sdu.worldoftrash.game.gui;
    exports dk.sdu.worldoftrash.game.gui.startmenu;
    exports dk.sdu.worldoftrash.game.gui.controllers;
    opens dk.sdu.worldoftrash.game.gui.controllers;

    exports dk.sdu.worldoftrash.game.data;
    opens dk.sdu.worldoftrash.game.data;
}