package com.ngoxuyu.ricochetrobots;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameMenuController implements Initializable {
    private MediaPlayer mediaPlayer;

    @FXML
    private void start(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Main.fxml")));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 700);
        stage.setMinWidth(1050.0);
        stage.setMinHeight(700.0);
        stage.setScene(scene);
        stage.setFullScreen(true);
        this.mediaPlayer.stop();
        stage.show();
        stage.centerOnScreen();
        stage.fullScreenProperty().addListener(event -> {
            if (stage.fullScreenProperty().getValue()) {
                root.setStyle("-fx-padding: 0 0 0 40px");
            } else {
                root.setStyle("-fx-padding: 0 0 0 0");
            }
        });
    }

    @FXML
    private void exit(ActionEvent e) {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media sound = new Media(Objects.requireNonNull(getClass().getResource("/musiques/TheSonOfFlynn.mp3")).toString());
        this.mediaPlayer = new MediaPlayer(sound);
        this.mediaPlayer.play();
    }
}
