package com.ngoxuyu.ricochetrobots;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Pions {
    protected ArrayList<HBox> robot = new ArrayList<>();
    protected int robot1Column;
    protected int robot1Row;
    protected int robot2Column;
    protected int robot2Row;
    protected int robot3Column;
    protected int robot3Row;
    protected int robot4Column;
    protected int robot4Row;

    protected Pions() {
        ImageView jaune = new ImageView(String.valueOf(getClass().getResource("/images/pions/robotJaune.png")));
        jaune.setFitWidth(16.0);
        jaune.setFitHeight(26.0);
        HBox robotJaune = new HBox();
        robotJaune.getChildren().add(jaune);
        robotJaune.setAlignment(Pos.CENTER);
        robotJaune.idProperty().setValue("pionJaune");
        ImageView rouge = new ImageView(String.valueOf(getClass().getResource("/images/pions/robotRouge.png")));
        rouge.setFitWidth(16.0);
        rouge.setFitHeight(26.0);
        HBox robotRouge = new HBox();
        robotRouge.getChildren().add(rouge);
        robotRouge.setAlignment(Pos.CENTER);
        robotRouge.idProperty().setValue("pionRouge");
        ImageView bleu = new ImageView(String.valueOf(getClass().getResource("/images/pions/robotBleu.png")));
        bleu.setFitWidth(16.0);
        bleu.setFitHeight(26.0);
        HBox robotBleu = new HBox();
        robotBleu.getChildren().add(bleu);
        robotBleu.setAlignment(Pos.CENTER);
        robotBleu.idProperty().setValue("pionBleu");
        ImageView vert = new ImageView(String.valueOf(getClass().getResource("/images/pions/robotVert.png")));
        vert.setFitWidth(16.0);
        vert.setFitHeight(26.0);
        HBox robotVert = new HBox();
        robotVert.getChildren().add(vert);
        robotVert.setAlignment(Pos.CENTER);
        robotVert.idProperty().setValue("pionVert");
        this.robot.add(robotJaune);
        this.robot.add(robotRouge);
        this.robot.add(robotBleu);
        this.robot.add(robotVert);
        Collections.shuffle(this.robot);
        Random random = new Random();
        int robot1Index = random.nextInt(42);
        if (robot1Index < 16) {
            this.robot1Column = random.nextInt(2);
            this.robot1Row = random.nextInt(8);
        } else if (robot1Index < 28) {
            this.robot1Column = random.nextInt(2, 8);
            this.robot1Row = random.nextInt(2);
        } else if (robot1Index < 34) {
            this.robot1Column = 4;
            this.robot1Row = random.nextInt(2, 8);
        } else if (robot1Index < 39) {
            int robot1Position = random.nextInt(5);
            if (robot1Position < 2) {
                this.robot1Column = random.nextInt(2, 4);
            } else {
                this.robot1Column = random.nextInt(5, 8);
            }
            this.robot1Row = 4;
        } else {
            int robot1Position = random.nextInt(3);
            if (robot1Position < 2) {
                this.robot1Column = random.nextInt(6, 8);
                this.robot1Row = 6;
            } else {
                this.robot1Column = 6;
                this.robot1Row = 7;
            }
        }
        int robot2Index = random.nextInt(42);
        if (robot2Index < 16) {
            this.robot2Column = random.nextInt(14, 16);
            this.robot2Row = random.nextInt(8);
        } else if (robot2Index < 28) {
            this.robot2Column = random.nextInt(8, 14);
            this.robot2Row = random.nextInt(2);
        } else if (robot2Index < 34) {
            this.robot2Column = 11;
            this.robot2Row = random.nextInt(2, 8);
        } else if (robot2Index < 39) {
            int robot2Position = random.nextInt(5);
            if (robot2Position < 3) {
                this.robot2Column = random.nextInt(8, 11);
            } else {
                this.robot2Column = random.nextInt(12, 14);
            }
            this.robot2Row = 4;
        } else {
            int robot2Position = random.nextInt(3);
            if (robot2Position < 2) {
                this.robot2Column = random.nextInt(8, 10);
                this.robot2Row = 6;
            } else {
                this.robot2Column = 9;
                this.robot2Row = 7;
            }
        }
        int robot3Index = random.nextInt(42);
        if (robot3Index < 16) {
            this.robot3Column = random.nextInt(2);
            this.robot3Row = random.nextInt(8, 16);
        } else if (robot3Index < 28) {
            this.robot3Column = random.nextInt(2, 8);
            this.robot3Row = random.nextInt(14, 16);
        } else if (robot3Index < 34) {
            this.robot3Column = 4;
            this.robot3Row = random.nextInt(8, 14);
        } else if (robot3Index < 39) {
            int robot3Position = random.nextInt(5);
            if (robot3Position < 2) {
                this.robot3Column = random.nextInt(2, 4);
            } else {
                this.robot3Column = random.nextInt(5, 8);
            }
            this.robot3Row = 3;
        } else {
            int robot3Position = random.nextInt(3);
            if (robot3Position < 2) {
                this.robot3Column = random.nextInt(6, 8);
                this.robot3Row = 9;
            } else {
                this.robot3Column = 6;
                this.robot3Row = 8;
            }
        }
        int robot4Index = random.nextInt(42);
        if (robot4Index < 16) {
            this.robot4Column = random.nextInt(14, 16);
            this.robot4Row = random.nextInt(8, 16);
        } else if (robot4Index < 28) {
            this.robot4Column = random.nextInt(8, 14);
            this.robot4Row = random.nextInt(14, 16);
        } else if (robot4Index < 34) {
            this.robot4Column = 11;
            this.robot4Row = random.nextInt(8, 14);
        } else if (robot4Index < 39) {
            int robot4Position = random.nextInt(5);
            if (robot4Position < 3) {
                this.robot4Column = random.nextInt(8, 11);
            } else {
                this.robot4Column = random.nextInt(12, 14);
            }
            this.robot4Row = 11;
        } else {
            int robot4Position = random.nextInt(3);
            if (robot4Position < 2) {
                this.robot4Column = random.nextInt(8, 10);
                this.robot4Row = 9;
            } else {
                this.robot4Column = 9;
                this.robot4Row = 8;
            }
        }
    }
}
