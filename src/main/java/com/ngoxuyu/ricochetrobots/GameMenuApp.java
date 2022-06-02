package com.ngoxuyu.ricochetrobots;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameMenuApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/GameMenu.fxml")));
        Scene scene = new Scene(root, 1300.0, 700.0);
        stage.setMinWidth(1050.0);
        stage.setMinHeight(700.0);
        stage.setTitle("Ricochet Robots");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        stage.centerOnScreen();
    }
}
