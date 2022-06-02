package com.ngoxuyu.ricochetrobots;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    private GridPane grid;
    @FXML
    private Pane jetonBackground;
    @FXML
    private Label time;
    @FXML
    private Label compteur;
    @FXML
    private ToggleButton robotJaune;
    @FXML
    private ToggleButton robotRouge;
    @FXML
    private ToggleButton robotBleu;
    @FXML
    private ToggleButton robotVert;

    private final Pions pions = new Pions();
    private int pionJauneIndex;
    private int pionRougeIndex;
    private int pionBleuIndex;
    private int pionVertIndex;
    private int pionJauneColumn;
    private int pionJauneRow;
    private int initialPionJauneColumn;
    private int initialPionJauneRow;
    private int pionRougeColumn;
    private int pionRougeRow;
    private int initialPionRougeColumn;
    private int initialPionRougeRow;
    private int pionBleuColumn;
    private int pionBleuRow;
    private int initialPionBleuColumn;
    private int initialPionBleuRow;
    private int pionVertColumn;
    private int pionVertRow;
    private int initialPionVertColumn;
    private int initialPionVertRow;
    private String pionMove;

    private final Target target = new Target();
    private int targetIndex = 0;
    private String targetColor;
    private int targetColumn;
    private int targetRow;

    private final boolean[][] topWall = new boolean[16][16];
    private final boolean[][] rightWall = new boolean[16][16];
    private final boolean[][] bottomWall = new boolean[16][16];
    private final boolean[][] leftWall = new boolean[16][16];
    private final boolean[][] pionPosition = new boolean[16][16];

    private final ArrayList<String> songs = new ArrayList<>();
    private int count = 0;
    private int minuteur = 90;
    private final Timer timer = new Timer();

    private final Timer timer2 = new Timer();
    private int songNumber;
    private Media media;
    private MediaPlayer mediaPlayer;
    private double currentTime;
    private double duration;

    public void start() {
        this.timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    if (minuteur > 0) {
                        minuteur--;
                    } else {
                        if (targetIndex < target.jetons.size() - 1) {
                            jetonBackground.getChildren().removeAll(target.jetons.get(targetIndex));
                            targetIndex++;
                            jetonBackground.getChildren().add(target.jetons.get(targetIndex));
                            count = 0;
                            compteur.setText("Curent number of moves : " + count);
                            initialPionJauneColumn = pionJauneColumn;
                            initialPionJauneRow = pionJauneRow;
                            initialPionRougeColumn = pionRougeColumn;
                            initialPionRougeRow = pionRougeRow;
                            initialPionBleuColumn = pionBleuColumn;
                            initialPionBleuRow = pionBleuRow;
                            initialPionVertColumn = pionVertColumn;
                            initialPionVertRow = pionVertRow;
                            minuteur = 90;
                            nextTargetId(target.jetons.get(targetIndex).idProperty().getValue());
                        } else {
                            mediaPlayer.stop();
                            timer.cancel();
                            timer2.cancel();
                            try {
                                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/EndGame.fxml")));
                                Stage stage = (Stage) grid.getScene().getWindow();
                                Scene scene = new Scene(root, 1300, 700);
                                stage.setMinWidth(1050.0);
                                stage.setMinHeight(700.0);
                                stage.setScene(scene);
                                stage.setFullScreen(true);
                                stage.show();
                                stage.centerOnScreen();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (minuteur >= 60) {
                        if (minuteur >= 70) {
                            time.setText("01:" + (minuteur - 60));
                        } else {
                            time.setText("01:0" + (minuteur - 60));
                        }
                    } else {
                        if (minuteur >= 10) {
                            time.setText("00:" + minuteur);
                        } else {
                            time.setText("00:0" + minuteur);
                        }
                    }
                    grid.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
                        timer.cancel();
                        timer2.cancel();
                    });
                });
            }
        }, 3000, 1000);
    }

    private void musique(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                duration = media.getDuration().toSeconds();
                currentTime = mediaPlayer.getCurrentTime().toSeconds();
                if (currentTime == duration) {
                    mediaPlayer.stop();
                    if (songNumber < songs.size() - 1) {
                        songNumber++;
                        media = new Media(songs.get(songNumber));
                        mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.play();
                    }
                }
            }
        };

        timer2.scheduleAtFixedRate(task, 0, 1000);
    }

    private void hit(String targetColor, int targetColumn, int targetRow){
        switch (targetColor) {
            case "multicolor":
                if ((targetColumn == this.pionJauneColumn && targetRow == this.pionJauneRow) || (targetColumn == this.pionRougeColumn && targetRow == this.pionRougeRow) || (targetColumn == this.pionBleuColumn && targetRow == this.pionBleuRow) || (targetColumn == this.pionVertColumn && targetRow == this.pionVertRow)) {
                    nextTarget();
                }
                break;
            case "jaune":
                if (targetColor.equals(this.pionMove) && targetColumn == this.pionJauneColumn && targetRow == this.pionJauneRow) {
                    nextTarget();
                }
                break;
            case "rouge":
                if (targetColor.equals(this.pionMove) && targetColumn == this.pionRougeColumn && targetRow == this.pionRougeRow) {
                    nextTarget();
                }
                break;
            case "bleu":
                if (targetColor.equals(this.pionMove) && targetColumn == this.pionBleuColumn && targetRow == this.pionBleuRow) {
                    nextTarget();
                }
                break;
            case "vert":
                if (targetColor.equals(this.pionMove) && targetColumn == this.pionVertColumn && targetRow == this.pionVertRow) {
                    nextTarget();
                }
                break;

        }
    }

    private void nextTarget() {
        if (this.targetIndex < this.target.jetons.size() - 1) {
            this.jetonBackground.getChildren().removeAll(this.target.jetons.get(this.targetIndex));
            this.targetIndex++;
            this.jetonBackground.getChildren().add(this.target.jetons.get(this.targetIndex));
            this.count = 0;
            this.compteur.setText("Curent number of moves : " + count);
            this.initialPionJauneColumn = pionJauneColumn;
            this.initialPionJauneRow = pionJauneRow;
            this.initialPionRougeColumn = pionRougeColumn;
            this.initialPionRougeRow = pionRougeRow;
            this.initialPionBleuColumn = pionBleuColumn;
            this.initialPionBleuRow = pionBleuRow;
            this.initialPionVertColumn = pionVertColumn;
            this.initialPionVertRow = pionVertRow;
            this.minuteur = 91;
            nextTargetId(this.target.jetons.get(this.targetIndex).idProperty().getValue());
        } else {
            this.minuteur = 0;
        }
    }

    private void nextTargetId(String jetonId) {
        switch (jetonId) {
            case "jetonLuneJaune" -> {
                this.targetColor = "jaune";
                this.targetColumn = this.target.luneJauneColumn;
                this.targetRow = this.target.luneJauneRow;
            }
            case "jetonLuneRouge" -> {
                this.targetColor = "rouge";
                this.targetColumn = this.target.luneRougeColumn;
                this.targetRow = this.target.luneRougeRow;
            }
            case "jetonLuneBleu" -> {
                this.targetColor = "bleu";
                this.targetColumn = this.target.luneBleuColumn;
                this.targetRow = this.target.luneBleuRow;
            }
            case "jetonLuneVert" -> {
                this.targetColor = "vert";
                this.targetColumn = this.target.luneVertColumn;
                this.targetRow = this.target.luneVertRow;
            }
            case "jetonHexagoneCardinalJaune" -> {
                this.targetColor = "jaune";
                this.targetColumn = this.target.hexagoneCardinalJauneColumn;
                this.targetRow = this.target.hexagoneCardinalJauneRow;
            }
            case "jetonHexagoneCardinalRouge" -> {
                this.targetColor = "rouge";
                this.targetColumn = this.target.hexagoneCardinalRougeColumn;
                this.targetRow = this.target.hexagoneCardinalRougeRow;
            }
            case "jetonHexagoneCardinalBleu" -> {
                this.targetColor = "bleu";
                this.targetColumn = this.target.hexagoneCardinalBleuColumn;
                this.targetRow = this.target.hexagoneCardinalBleuRow;
            }
            case "jetonHexagoneCardinalVert" -> {
                this.targetColor = "vert";
                this.targetColumn = this.target.hexagoneCardinalVertColumn;
                this.targetRow = this.target.hexagoneCardinalVertRow;
            }
            case "jetonTriangleSolaireJaune" -> {
                this.targetColor = "jaune";
                this.targetColumn = this.target.triangleSolaireJauneColumn;
                this.targetRow = this.target.triangleSolaireJauneRow;
            }
            case "jetonTriangleSolaireRouge" -> {
                this.targetColor = "rouge";
                this.targetColumn = this.target.triangleSolaireRougeColumn;
                this.targetRow = this.target.triangleSolaireRougeRow;
            }
            case "jetonTriangleSolaireBleu" -> {
                this.targetColor = "bleu";
                this.targetColumn = this.target.triangleSolaireBleuColumn;
                this.targetRow = this.target.triangleSolaireBleuRow;
            }
            case "jetonTriangleSolaireVert" -> {
                this.targetColor = "vert";
                this.targetColumn = this.target.triangleSolaireVertColumn;
                this.targetRow = this.target.triangleSolaireVertRow;
            }
            case "jetonSaturneJaune" -> {
                this.targetColor = "jaune";
                this.targetColumn = this.target.saturneJauneColumn;
                this.targetRow = this.target.saturneJauneRow;
            }
            case "jetonSaturneRouge" -> {
                this.targetColor = "rouge";
                this.targetColumn = this.target.saturneRougeColumn;
                this.targetRow = this.target.saturneRougeRow;
            }
            case "jetonSaturneBleu" -> {
                this.targetColor = "bleu";
                this.targetColumn = this.target.saturneBleuColumn;
                this.targetRow = this.target.saturneBleuRow;
            }
            case "jetonSaturneVert" -> {
                this.targetColor = "vert";
                this.targetColumn = this.target.saturneVertColumn;
                this.targetRow = this.target.saturneVertRow;
            }
            case "jetonTrouNoirMulticolor" -> {
                this.targetColor = "multicolor";
                this.targetColumn = this.target.trouNoirMulticolorColumn;
                this.targetRow = this.target.trouNoirMulticolorRow;
            }
        }
    }

    @FXML
    private void robotJaune() {
        this.robotJaune.setStyle("-fx-opacity: 1.0;");
        this.robotRouge.setStyle("-fx-opacity: 0.5;");
        this.robotBleu.setStyle("-fx-opacity: 0.5;");
        this.robotVert.setStyle("-fx-opacity: 0.5;");
        this.pionMove = "jaune";
    }

    @FXML
    private void robotRouge() {
        this.robotJaune.setStyle("-fx-opacity: 0.5;");
        this.robotRouge.setStyle("-fx-opacity: 1.0;");
        this.robotBleu.setStyle("-fx-opacity: 0.5;");
        this.robotVert.setStyle("-fx-opacity: 0.5;");
        this.pionMove = "rouge";
    }

    @FXML
    private void robotBleu() {
        this.robotJaune.setStyle("-fx-opacity: 0.5;");
        this.robotRouge.setStyle("-fx-opacity: 0.5;");
        this.robotBleu.setStyle("-fx-opacity: 1.0;");
        this.robotVert.setStyle("-fx-opacity: 0.5;");
        this.pionMove = "bleu";
    }

    @FXML
    private void robotVert() {
        this.robotJaune.setStyle("-fx-opacity: 0.5;");
        this.robotRouge.setStyle("-fx-opacity: 0.5;");
        this.robotBleu.setStyle("-fx-opacity: 0.5;");
        this.robotVert.setStyle("-fx-opacity: 1.0;");
        this.pionMove = "vert";
    }

    @FXML
    public void reset() {
        this.grid.getChildren().removeAll(this.pions.robot.get(this.pionJauneIndex), this.pions.robot.get(this.pionRougeIndex), this.pions.robot.get(this.pionBleuIndex), this.pions.robot.get(this.pionVertIndex));
        this.pionPosition[this.pionJauneRow][this.pionJauneColumn] = false;
        this.pionPosition[this.pionRougeRow][this.pionRougeColumn] = false;
        this.pionPosition[this.pionBleuRow][this.pionBleuColumn] = false;
        this.pionPosition[this.pionVertRow][this.pionVertColumn] = false;
        this.grid.add(this.pions.robot.get(this.pionJauneIndex), this.initialPionJauneColumn, this.initialPionJauneRow);
        this.pionPosition[this.initialPionJauneRow][this.initialPionJauneColumn] = true;
        this.grid.add(this.pions.robot.get(this.pionRougeIndex), this.initialPionRougeColumn, this.initialPionRougeRow);
        this.pionPosition[this.initialPionRougeRow][this.initialPionRougeColumn] = true;
        this.grid.add(this.pions.robot.get(this.pionBleuIndex), this.initialPionBleuColumn, this.initialPionBleuRow);
        this.pionPosition[this.initialPionBleuRow][this.initialPionBleuColumn] = true;
        this.grid.add(this.pions.robot.get(this.pionVertIndex), this.initialPionVertColumn, this.initialPionVertRow);
        this.pionPosition[this.initialPionVertRow][this.initialPionVertColumn] = true;
        this.pionJauneColumn = this.initialPionJauneColumn;
        this.pionJauneRow = this.initialPionJauneRow;
        this.pionRougeColumn = this.initialPionRougeColumn;
        this.pionRougeRow = this.initialPionRougeRow;
        this.pionBleuColumn = this.initialPionBleuColumn;
        this.pionBleuRow = this.initialPionBleuRow;
        this.pionVertColumn = this.initialPionVertColumn;
        this.pionVertRow = this.initialPionVertRow;
        this.count = 0;
        this.compteur.setText("Curent number of moves : " + this.count);
    }

    @FXML
    public void leftMove() {
        nextLeftMove();
        hit(this.targetColor, this.targetColumn, this.targetRow);
    }

    @FXML
    public void topMove() {
        nextUpMove();
        hit(this.targetColor, this.targetColumn, this.targetRow);
    }

    @FXML
    public void bottomMove() {
        nextDownMove();
        hit(this.targetColor, this.targetColumn, this.targetRow);
    }

    @FXML
    public void rightMove() {
        nextRightMove();
        hit(this.targetColor, this.targetColumn, this.targetRow);
    }

    private void nextLeftMove() {
        switch (this.pionMove) {
            case "jaune" -> {
                int i = this.pionJauneColumn;
                while (!this.leftWall[this.pionJauneRow][i] && !this.rightWall[this.pionJauneRow][i - 1] && !this.pionPosition[this.pionJauneRow][i - 1]) {
                    i--;
                }
                pionJauneHorizontalMove(i);
            }
            case "rouge" -> {
                int i = this.pionRougeColumn;
                while (!this.leftWall[this.pionRougeRow][i] && !this.rightWall[this.pionRougeRow][i - 1] && !this.pionPosition[this.pionRougeRow][i - 1]) {
                    i--;
                }
                pionRougeHorizontalMove(i);
            }
            case "bleu" -> {
                int i = this.pionBleuColumn;
                while (!this.leftWall[this.pionBleuRow][i] && !this.rightWall[this.pionBleuRow][i - 1] && !this.pionPosition[this.pionBleuRow][i - 1]) {
                    i--;
                }
                pionBleuHorizontalMove(i);
            }
            case "vert" -> {
                int i = this.pionVertColumn;
                while (!this.leftWall[this.pionVertRow][i] && !this.rightWall[this.pionVertRow][i - 1] && !this.pionPosition[this.pionVertRow][i - 1]) {
                    i--;
                }
                pionVertHorizontalMove(i);
            }
        }
    }

    private void nextUpMove() {
        switch (this.pionMove) {
            case "jaune" -> {
                int j = this.pionJauneRow;
                while (!this.topWall[j][this.pionJauneColumn] && !this.bottomWall[j - 1][this.pionJauneColumn] && !this.pionPosition[j - 1][this.pionJauneColumn]) {
                    j--;
                }
                pionJauneVerticalMove(j);
            }
            case "rouge" -> {
                int j = this.pionRougeRow;
                while (!this.topWall[j][this.pionRougeColumn] && !this.bottomWall[j - 1][this.pionRougeColumn] && !this.pionPosition[j - 1][this.pionRougeColumn]) {
                    j--;
                }
                pionRougeVerticalMove(j);
            }
            case "bleu" -> {
                int j = this.pionBleuRow;
                while (!this.topWall[j][this.pionBleuColumn] && !this.bottomWall[j - 1][this.pionBleuColumn] && !this.pionPosition[j - 1][this.pionBleuColumn]) {
                    j--;
                }
                pionBleuVerticalMove(j);
            }
            case "vert" -> {
                int j = this.pionVertRow;
                while (!this.topWall[j][this.pionVertColumn] && !this.bottomWall[j - 1][this.pionVertColumn] && !this.pionPosition[j - 1][this.pionVertColumn]) {
                    j--;
                }
                pionVertVerticalMove(j);
            }
        }
    }

    private void nextDownMove() {
        switch (this.pionMove) {
            case "jaune" -> {
                int j = this.pionJauneRow;
                while (!this.bottomWall[j][this.pionJauneColumn] && !this.topWall[j + 1][this.pionJauneColumn] && !this.pionPosition[j + 1][this.pionJauneColumn]) {
                    j++;
                }
                pionJauneVerticalMove(j);
            }
            case "rouge" -> {
                int j = this.pionRougeRow;
                while (!this.bottomWall[j][this.pionRougeColumn] && !this.topWall[j + 1][this.pionRougeColumn] && !this.pionPosition[j + 1][this.pionRougeColumn]) {
                    j++;
                }
                pionRougeVerticalMove(j);
            }
            case "bleu" -> {
                int j = this.pionBleuRow;
                while (!this.bottomWall[j][this.pionBleuColumn] && !this.topWall[j + 1][this.pionBleuColumn] && !this.pionPosition[j + 1][this.pionBleuColumn]) {
                    j++;
                }
                pionBleuVerticalMove(j);
            }
            case "vert" -> {
                int j = this.pionVertRow;
                while (!this.bottomWall[j][this.pionVertColumn] && !this.topWall[j + 1][this.pionVertColumn] && !this.pionPosition[j + 1][this.pionVertColumn]) {
                    j++;
                }
                pionVertVerticalMove(j);
            }
        }
    }

    private void nextRightMove() {
        switch (this.pionMove) {
            case "jaune" -> {
                int i = this.pionJauneColumn;
                while (!this.rightWall[this.pionJauneRow][i] && !this.leftWall[this.pionJauneRow][i + 1] && !this.pionPosition[this.pionJauneRow][i + 1]) {
                    i++;
                }
                pionJauneHorizontalMove(i);
            }
            case "rouge" -> {
                int i = this.pionRougeColumn;
                while (!this.rightWall[this.pionRougeRow][i] && !this.leftWall[this.pionRougeRow][i + 1] && !this.pionPosition[this.pionRougeRow][i + 1]) {
                    i++;
                }
                pionRougeHorizontalMove(i);
            }
            case "bleu" -> {
                int i = this.pionBleuColumn;
                while (!this.rightWall[this.pionBleuRow][i] && !this.leftWall[this.pionBleuRow][i + 1] && !this.pionPosition[this.pionBleuRow][i + 1]) {
                    i++;
                }
                pionBleuHorizontalMove(i);
            }
            case "vert" -> {
                int i = this.pionVertColumn;
                while (!this.rightWall[this.pionVertRow][i] && !this.leftWall[this.pionVertRow][i + 1] && !this.pionPosition[this.pionVertRow][i + 1]) {
                    i++;
                }
                pionVertHorizontalMove(i);
            }
        }
    }

    private void pionJauneVerticalMove(int j) {
        if (j != this.pionJauneRow) {
            this.grid.getChildren().removeAll(this.pions.robot.get(this.pionJauneIndex));
            this.pionPosition[this.pionJauneRow][this.pionJauneColumn] = false;
            this.grid.add(this.pions.robot.get(this.pionJauneIndex), this.pionJauneColumn, j);
            this.pionJauneRow = j;
            this.pionPosition[this.pionJauneRow][this.pionJauneColumn] = true;
            this.count++;
            this.compteur.setText("Curent number of moves : " + this.count);
        }
    }

    private void pionJauneHorizontalMove(int i) {
        if (i != this.pionJauneColumn) {
            this.grid.getChildren().removeAll(this.pions.robot.get(this.pionJauneIndex));
            this.pionPosition[this.pionJauneRow][this.pionJauneColumn] = false;
            this.grid.add(this.pions.robot.get(this.pionJauneIndex), i, this.pionJauneRow);
            this.pionJauneColumn = i;
            this.pionPosition[this.pionJauneRow][this.pionJauneColumn] = true;
            this.count++;
            this.compteur.setText("Curent number of moves : " + this.count);
        }
    }

    private void pionRougeVerticalMove(int j) {
        if (j != this.pionRougeRow) {
            this.grid.getChildren().removeAll(this.pions.robot.get(this.pionRougeIndex));
            this.pionPosition[this.pionRougeRow][this.pionRougeColumn] = false;
            this.grid.add(this.pions.robot.get(this.pionRougeIndex), this.pionRougeColumn, j);
            this.pionRougeRow = j;
            this.pionPosition[this.pionRougeRow][this.pionRougeColumn] = true;
            this.count++;
            this.compteur.setText("Curent number of moves : " + this.count);
        }
    }

    private void pionRougeHorizontalMove(int i) {
        if (i != this.pionRougeColumn) {
            this.grid.getChildren().removeAll(this.pions.robot.get(this.pionRougeIndex));
            this.pionPosition[this.pionRougeRow][this.pionRougeColumn] = false;
            this.grid.add(this.pions.robot.get(this.pionRougeIndex), i, this.pionRougeRow);
            this.pionRougeColumn = i;
            this.pionPosition[this.pionRougeRow][this.pionRougeColumn] = true;
            this.count++;
            this.compteur.setText("Curent number of moves : " + this.count);
        }
    }

    private void pionBleuVerticalMove(int j) {
        if (j != this.pionBleuRow) {
            this.grid.getChildren().removeAll(this.pions.robot.get(this.pionBleuIndex));
            this.pionPosition[this.pionBleuRow][this.pionBleuColumn] = false;
            this.grid.add(this.pions.robot.get(this.pionBleuIndex), this.pionBleuColumn, j);
            this.pionBleuRow = j;
            this.pionPosition[this.pionBleuRow][this.pionBleuColumn] = true;
            this.count++;
            this.compteur.setText("Curent number of moves : " + this.count);
        }
    }

    private void pionBleuHorizontalMove(int i) {
        if (i != this.pionBleuColumn) {
            this.grid.getChildren().removeAll(this.pions.robot.get(this.pionBleuIndex));
            this.pionPosition[this.pionBleuRow][this.pionBleuColumn] = false;
            this.grid.add(this.pions.robot.get(this.pionBleuIndex), i, this.pionBleuRow);
            this.pionBleuColumn = i;
            this.pionPosition[this.pionBleuRow][this.pionBleuColumn] = true;
            this.count++;
            this.compteur.setText("Curent number of moves : " + this.count);
        }
    }

    private void pionVertVerticalMove(int j) {
        if (j != this.pionVertRow) {
            this.grid.getChildren().removeAll(this.pions.robot.get(this.pionVertIndex));
            this.pionPosition[this.pionVertRow][this.pionVertColumn] = false;
            this.grid.add(this.pions.robot.get(this.pionVertIndex), this.pionVertColumn, j);
            this.pionVertRow = j;
            this.pionPosition[this.pionVertRow][this.pionVertColumn] = true;
            this.count++;
            this.compteur.setText("Curent number of moves : " + this.count);
        }
    }

    private void pionVertHorizontalMove(int i) {
        if (i != this.pionVertColumn) {
            this.grid.getChildren().removeAll(this.pions.robot.get(this.pionVertIndex));
            this.pionPosition[this.pionVertRow][this.pionVertColumn] = false;
            this.grid.add(this.pions.robot.get(this.pionVertIndex), i, this.pionVertRow);
            this.pionVertColumn = i;
            this.pionPosition[this.pionVertRow][this.pionVertColumn] = true;
            this.count++;
            this.compteur.setText("Curent number of moves : " + this.count);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Random random = new Random();
        int wall1 = random.nextInt(1, 7);
        int wall2 = random.nextInt(9, 15);
        int wall3 = random.nextInt(1, 7);
        int wall4 = random.nextInt(9, 15);
        int wall5 = random.nextInt(1, 7);
        int wall6 = random.nextInt(9, 15);
        int wall7 = random.nextInt(1, 7);
        int wall8 = random.nextInt(9, 15);

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                HBox center = new HBox();
                center.setAlignment(Pos.CENTER);
                Pane top = new Pane();
                Pane right = new Pane();
                Pane bottom = new Pane();
                Pane left = new Pane();
                BorderPane pane = new BorderPane(center, top, right, bottom, left);
                this.topWall[j][i] = false;
                this.leftWall[j][i] = false;
                this.bottomWall[j][i] = false;
                this.rightWall[j][i] = false;
                this.pionPosition[j][i] = false;
                if (i == 7 && j == 7) {
                    top.setPrefSize(43.75, 10.0);
                    top.setId("murHorizontal");
                    this.topWall[j][i] = true;
                    left.setPrefSize(10.0, 43.75);
                    left.setId("murVertical");
                    this.leftWall[j][i] = true;
                    this.grid.add(pane, i, j);
                } else if (i == 8 && j == 7) {
                    top.setPrefSize(43.75, 10.0);
                    top.setId("murHorizontal");
                    this.topWall[j][i] = true;
                    right.setPrefSize(10.0, 43.75);
                    right.setId("murVertical");
                    this.rightWall[j][i] = true;
                    this.grid.add(pane, i, j);
                } else if (i == 7 && j == 8) {
                    bottom.setPrefSize(43.75, 10.0);
                    bottom.setId("murHorizontal");
                    this.bottomWall[j][i] = true;
                    left.setPrefSize(10.0, 43.75);
                    left.setId("murVertical");
                    this.leftWall[j][i] = true;
                    this.grid.add(pane, i, j);
                } else if (i == 8 && j == 8) {
                    bottom.setPrefSize(43.75, 10.0);
                    bottom.setId("murHorizontal");
                    this.bottomWall[j][i] = true;
                    right.setPrefSize(10.0, 43.75);
                    right.setId("murVertical");
                    this.rightWall[j][i] = true;
                    this.grid.add(pane, i, j);
                } else {
                    if (i == 0) {
                        left.setPrefSize(10.0, 43.75);
                        left.setId("murVertical");
                        this.leftWall[j][i] = true;
                        if (j == wall1) {
                            bottom.setPrefSize(43.75, 10.0);
                            bottom.setId("murHorizontal");
                            this.bottomWall[j][i] = true;
                        } else if (j == wall2) {
                            top.setPrefSize(43.75, 10.0);
                            top.setId("murHorizontal");
                            this.topWall[j][i] = true;
                        }
                    }
                    if (j == 0) {
                        top.setPrefSize(43.75, 10.0);
                        top.setId("murHorizontal");
                        this.topWall[j][i] = true;
                        if (i == wall3) {
                            right.setPrefSize(10.0, 43.75);
                            right.setId("murVertical");
                            this.rightWall[j][i] = true;
                        } else if (i == wall4) {
                            left.setPrefSize(10.0, 43.75);
                            left.setId("murVertical");
                            this.leftWall[j][i] = true;
                        }
                    }
                    if (i == 15) {
                        right.setPrefSize(10.0, 43.75);
                        right.setId("murVertical");
                        this.rightWall[j][i] = true;
                        if (j == wall5) {
                            bottom.setPrefSize(43.75, 10.0);
                            bottom.setId("murHorizontal");
                            this.bottomWall[j][i] = true;
                        } else if (j == wall6) {
                            top.setPrefSize(43.75, 10.0);
                            top.setId("murHorizontal");
                            this.topWall[j][i] = true;
                        }
                    }
                    if (j == 15) {
                        bottom.setPrefSize(43.75, 10.0);
                        bottom.setId("murHorizontal");
                        this.bottomWall[j][i] = true;
                        if (i == wall7) {
                            right.setPrefSize(10.0, 43.75);
                            right.setId("murVertical");
                            this.rightWall[j][i] = true;
                        } else if (i == wall8) {
                            left.setPrefSize(10.0, 43.75);
                            left.setId("murVertical");
                            this.leftWall[j][i] = true;
                        }
                    }
                    if (i == this.target.luneJauneColumn && j == this.target.luneJauneRow) {
                        center.setId("luneJaune");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.saturneRougeColumn && j == this.target.saturneRougeRow) {
                        center.setId("saturneRouge");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.triangleSolaireBleuColumn && j == this.target.triangleSolaireBleuRow) {
                        center.setId("triangleSolaireBleu");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.hexagoneCardinalVertColumn && j == this.target.hexagoneCardinalVertRow) {
                        center.setId("hexagoneCardinalVert");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.triangleSolaireJauneColumn && j == this.target.triangleSolaireJauneRow) {
                        center.setId("triangleSolaireJaune");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.hexagoneCardinalRougeColumn && j == this.target.hexagoneCardinalRougeRow) {
                        center.setId("hexagoneCardinalRouge");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.luneBleuColumn && j == this.target.luneBleuRow) {
                        center.setId("luneBleu");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.saturneVertColumn && j == this.target.saturneVertRow) {
                        center.setId("saturneVert");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.hexagoneCardinalJauneColumn && j == this.target.hexagoneCardinalJauneRow) {
                        center.setId("hexagoneCardinalJaune");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.luneRougeColumn && j == this.target.luneRougeRow) {
                        center.setId("luneRouge");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.saturneBleuColumn && j == this.target.saturneBleuRow) {
                        center.setId("saturneBleu");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.triangleSolaireVertColumn && j == this.target.triangleSolaireVertRow) {
                        center.setId("triangleSolaireVert");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.saturneJauneColumn && j == this.target.saturneJauneRow) {
                        center.setId("saturneJaune");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.triangleSolaireRougeColumn && j == this.target.triangleSolaireRougeRow) {
                        center.setId("triangleSolaireRouge");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.hexagoneCardinalBleuColumn && j == this.target.hexagoneCardinalBleuRow) {
                        center.setId("hexagoneCardinalBleu");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.luneVertColumn && j == this.target.luneVertRow) {
                        center.setId("luneVert");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    } else if (i == this.target.trouNoirMulticolorColumn && j == this.target.trouNoirMulticolorRow) {
                        center.setId("trouNoirMulticolor");
                        symboleMurs(random, top, right, bottom, left, i, j);
                    }
                    pane.setId("case");
                    this.grid.add(pane, i, j);
                }
            }
        }
        this.grid.add(this.pions.robot.get(0), this.pions.robot1Column, this.pions.robot1Row);
        this.grid.add(this.pions.robot.get(1), this.pions.robot2Column, this.pions.robot2Row);
        this.grid.add(this.pions.robot.get(2), this.pions.robot3Column, this.pions.robot3Row);
        this.grid.add(this.pions.robot.get(3), this.pions.robot4Column, this.pions.robot4Row);
        this.pionJauneIndex = pionIndex(this.pions.robot, "pionJaune");
        switch (this.pionJauneIndex) {
            case 0 -> {
                this.pionJauneColumn = this.pions.robot1Column;
                this.pionJauneRow = this.pions.robot1Row;
                this.initialPionJauneColumn = this.pions.robot1Column;
                this.initialPionJauneRow = this.pions.robot1Row;
            }
            case 1 -> {
                this.pionJauneColumn = this.pions.robot2Column;
                this.pionJauneRow = this.pions.robot2Row;
                this.initialPionJauneColumn = this.pions.robot2Column;
                this.initialPionJauneRow = this.pions.robot2Row;
            }
            case 2 -> {
                this.pionJauneColumn = this.pions.robot3Column;
                this.pionJauneRow = this.pions.robot3Row;
                this.initialPionJauneColumn = this.pions.robot3Column;
                this.initialPionJauneRow = this.pions.robot3Row;
            }
            case 3 -> {
                this.pionJauneColumn = this.pions.robot4Column;
                this.pionJauneRow = this.pions.robot4Row;
                this.initialPionJauneColumn = this.pions.robot4Column;
                this.initialPionJauneRow = this.pions.robot4Row;
            }
        }
        this.pionPosition[this.pionJauneRow][this.pionJauneColumn] = true;
        this.pionRougeIndex = pionIndex(this.pions.robot, "pionRouge");
        switch (this.pionRougeIndex) {
            case 0 -> {
                this.pionRougeColumn = this.pions.robot1Column;
                this.pionRougeRow = this.pions.robot1Row;
                this.initialPionRougeColumn = this.pions.robot1Column;
                this.initialPionRougeRow = this.pions.robot1Row;
            }
            case 1 -> {
                this.pionRougeColumn = this.pions.robot2Column;
                this.pionRougeRow = this.pions.robot2Row;
                this.initialPionRougeColumn = this.pions.robot2Column;
                this.initialPionRougeRow = this.pions.robot2Row;
            }
            case 2 -> {
                this.pionRougeColumn = this.pions.robot3Column;
                this.pionRougeRow = this.pions.robot3Row;
                this.initialPionRougeColumn = this.pions.robot3Column;
                this.initialPionRougeRow = this.pions.robot3Row;
            }
            case 3 -> {
                this.pionRougeColumn = this.pions.robot4Column;
                this.pionRougeRow = this.pions.robot4Row;
                this.initialPionRougeColumn = this.pions.robot4Column;
                this.initialPionRougeRow = this.pions.robot4Row;

            }
        }
        this.pionPosition[this.pionRougeRow][this.pionRougeColumn] = true;
        this.pionBleuIndex = pionIndex(this.pions.robot, "pionBleu");
        switch (this.pionBleuIndex) {
            case 0 -> {
                this.pionBleuColumn = this.pions.robot1Column;
                this.pionBleuRow = this.pions.robot1Row;
                this.initialPionBleuColumn = this.pions.robot1Column;
                this.initialPionBleuRow = this.pions.robot1Row;
            }
            case 1 -> {
                this.pionBleuColumn = this.pions.robot2Column;
                this.pionBleuRow = this.pions.robot2Row;
                this.initialPionBleuColumn = this.pions.robot2Column;
                this.initialPionBleuRow = this.pions.robot2Row;
            }
            case 2 -> {
                this.pionBleuColumn = this.pions.robot3Column;
                this.pionBleuRow = this.pions.robot3Row;
                this.initialPionBleuColumn = this.pions.robot3Column;
                this.initialPionBleuRow = this.pions.robot3Row;
            }
            case 3 -> {
                this.pionBleuColumn = this.pions.robot4Column;
                this.pionBleuRow = this.pions.robot4Row;
                this.initialPionBleuColumn = this.pions.robot4Column;
                this.initialPionBleuRow = this.pions.robot4Row;
            }
        }
        this.pionPosition[this.pionBleuRow][this.pionBleuColumn] = true;
        this.pionVertIndex = pionIndex(this.pions.robot, "pionVert");
        switch (this.pionVertIndex) {
            case 0 -> {
                this.pionVertColumn = this.pions.robot1Column;
                this.pionVertRow = this.pions.robot1Row;
                this.initialPionVertColumn = this.pions.robot1Column;
                this.initialPionVertRow = this.pions.robot1Row;
            }
            case 1 -> {
                this.pionVertColumn = this.pions.robot2Column;
                this.pionVertRow = this.pions.robot2Row;
                this.initialPionVertColumn = this.pions.robot2Column;
                this.initialPionVertRow = this.pions.robot2Row;
            }
            case 2 -> {
                this.pionVertColumn = this.pions.robot3Column;
                this.pionVertRow = this.pions.robot3Row;
                this.initialPionVertColumn = this.pions.robot3Column;
                this.initialPionVertRow = this.pions.robot3Row;
            }
            case 3 -> {
                this.pionVertColumn = this.pions.robot4Column;
                this.pionVertRow = this.pions.robot4Row;
                this.initialPionVertColumn = this.pions.robot4Column;
                this.initialPionVertRow = this.pions.robot4Row;
            }
        }
        this.pionPosition[this.pionVertRow][this.pionVertColumn] = true;

        this.jetonBackground.getChildren().add(this.target.jetons.get(this.targetIndex));
        nextTargetId(this.target.jetons.get(this.targetIndex).idProperty().getValue());
        start();
        this.songs.add(Objects.requireNonNull(getClass().getResource("/musiques/Derezzed.mp3")).toString());
        this.songs.add(Objects.requireNonNull(getClass().getResource("/musiques/Recognizer.mp3")).toString());
        this.songs.add(Objects.requireNonNull(getClass().getResource("/musiques/TheGameHasChanged.mp3")).toString());
        this.songs.add(Objects.requireNonNull(getClass().getResource("/musiques/SolarSailor.mp3")).toString());
        this.songs.add(Objects.requireNonNull(getClass().getResource("/musiques/TheGrid.mp3")).toString());
        this.songs.add(Objects.requireNonNull(getClass().getResource("/musiques/EndOfLine.mp3")).toString());
        this.songs.add(Objects.requireNonNull(getClass().getResource("/musiques/TronLegacyEnd.mp3")).toString());
        this.songs.add(Objects.requireNonNull(getClass().getResource("/musiques/TheSonOfFlynn.mp3")).toString());
        this.songNumber = 0;
        this.media = new Media(this.songs.get(this.songNumber));
        this.mediaPlayer = new MediaPlayer(this.media);
        this.mediaPlayer.play();
        musique();
    }

    private void symboleMurs(Random random, Pane top, Pane right, Pane bottom, Pane left, int i, int j) {
        int murHorizontal = random.nextInt(2);
        int murVertical = random.nextInt(2);
        if (murHorizontal == 0) {
            top.setPrefSize(43.75, 10.0);
            top.setId("murHorizontal");
            this.topWall[j][i] = true;
        } else {
            bottom.setPrefSize(43.75, 10.0);
            bottom.setId("murHorizontal");
            this.bottomWall[j][i] = true;
        }
        if (murVertical == 0) {
            right.setPrefSize(10.0, 43.75);
            right.setId("murVertical");
            this.rightWall[j][i] = true;
        } else {
            left.setPrefSize(10.0, 43.75);
            left.setId("murVertical");
            this.leftWall[j][i] = true;
        }
    }

    private int pionIndex(ArrayList<HBox> pions, String pionColor) {
        int i = 0;
        while(!pions.get(i).idProperty().getValue().equals(pionColor)){
            i++;
        }
        return i;
    }
}

