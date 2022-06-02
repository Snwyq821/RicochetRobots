package com.ngoxuyu.ricochetrobots;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Target {
    protected ArrayList<ImageView> jetons = new ArrayList<>();
    protected int luneJauneColumn;
    protected int luneJauneRow;
    protected int triangleSolaireJauneColumn;
    protected int triangleSolaireJauneRow;
    protected int hexagoneCardinalJauneColumn;
    protected int hexagoneCardinalJauneRow;
    protected int saturneJauneColumn;
    protected int saturneJauneRow;
    protected int saturneRougeColumn;
    protected int saturneRougeRow;
    protected int hexagoneCardinalRougeColumn;
    protected int hexagoneCardinalRougeRow;
    protected int luneRougeColumn;
    protected int luneRougeRow;
    protected int triangleSolaireRougeColumn;
    protected int triangleSolaireRougeRow;
    protected int triangleSolaireBleuColumn;
    protected int triangleSolaireBleuRow;
    protected int luneBleuColumn;
    protected int luneBleuRow;
    protected int saturneBleuColumn;
    protected int saturneBleuRow;
    protected int hexagoneCardinalBleuColumn;
    protected int hexagoneCardinalBleuRow;
    protected int hexagoneCardinalVertColumn;
    protected int hexagoneCardinalVertRow;
    protected int saturneVertColumn;
    protected int saturneVertRow;
    protected int triangleSolaireVertColumn;
    protected int triangleSolaireVertRow;
    protected int luneVertColumn;
    protected int luneVertRow;
    protected int trouNoirMulticolorColumn;
    protected int trouNoirMulticolorRow;


    public Target() {
        ImageView jetonLuneJaune = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonLuneJaune.png")));
        jetonLuneJaune.idProperty().setValue("jetonLuneJaune");
        ImageView jetonLuneRouge = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonLuneRouge.png")));
        jetonLuneRouge.idProperty().setValue("jetonLuneRouge");
        ImageView jetonLuneBleu = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonLuneBleu.png")));
        jetonLuneBleu.idProperty().setValue("jetonLuneBleu");
        ImageView jetonLuneVert = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonLuneVert.png")));
        jetonLuneVert.idProperty().setValue("jetonLuneVert");
        ImageView jetonHexagoneCardinalJaune = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonHexagoneCardinalJaune.png")));
        jetonHexagoneCardinalJaune.idProperty().setValue("jetonHexagoneCardinalJaune");
        ImageView jetonHexagoneCardinalRouge = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonHexagoneCardinalRouge.png")));
        jetonHexagoneCardinalRouge.idProperty().setValue("jetonHexagoneCardinalRouge");
        ImageView jetonHexagoneCardinalBleu = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonHexagoneCardinalBleu.png")));
        jetonHexagoneCardinalBleu.idProperty().setValue("jetonHexagoneCardinalBleu");
        ImageView jetonHexagoneCardinalVert = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonHexagoneCardinalVert.png")));
        jetonHexagoneCardinalVert.idProperty().setValue("jetonHexagoneCardinalVert");
        ImageView jetonTriangleSolaireJaune = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonTriangleSolaireJaune.png")));
        jetonTriangleSolaireJaune.idProperty().setValue("jetonTriangleSolaireJaune");
        ImageView jetonTriangleSolaireRouge = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonTriangleSolaireRouge.png")));
        jetonTriangleSolaireRouge.idProperty().setValue("jetonTriangleSolaireRouge");
        ImageView jetonTriangleSolaireBleu = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonTriangleSolaireBleu.png")));
        jetonTriangleSolaireBleu.idProperty().setValue("jetonTriangleSolaireBleu");
        ImageView jetonTriangleSolaireVert = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonTriangleSolaireVert.png")));
        jetonTriangleSolaireVert.idProperty().setValue("jetonTriangleSolaireVert");
        ImageView jetonSaturneJaune = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonSaturneJaune.png")));
        jetonSaturneJaune.idProperty().setValue("jetonSaturneJaune");
        ImageView jetonSaturneRouge = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonSaturneRouge.png")));
        jetonSaturneRouge.idProperty().setValue("jetonSaturneRouge");
        ImageView jetonSaturneBleu = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonSaturneBleu.png")));
        jetonSaturneBleu.idProperty().setValue("jetonSaturneBleu");
        ImageView jetonSaturneVert = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonSaturneVert.png")));
        jetonSaturneVert.idProperty().setValue("jetonSaturneVert");
        ImageView jetonTrouNoirMulticolor = new ImageView(String.valueOf(getClass().getResource("/images/pions/jetonTrouNoirMulticolor.png")));
        jetonTrouNoirMulticolor.idProperty().setValue("jetonTrouNoirMulticolor");
        this.jetons.add(jetonLuneJaune);
        this.jetons.add(jetonLuneRouge);
        this.jetons.add(jetonLuneBleu);
        this.jetons.add(jetonLuneVert);
        this.jetons.add(jetonHexagoneCardinalJaune);
        this.jetons.add(jetonHexagoneCardinalRouge);
        this.jetons.add(jetonHexagoneCardinalBleu);
        this.jetons.add(jetonHexagoneCardinalVert);
        this.jetons.add(jetonTriangleSolaireJaune);
        this.jetons.add(jetonTriangleSolaireRouge);
        this.jetons.add(jetonTriangleSolaireBleu);
        this.jetons.add(jetonTriangleSolaireVert);
        this.jetons.add(jetonSaturneJaune);
        this.jetons.add(jetonSaturneRouge);
        this.jetons.add(jetonSaturneBleu);
        this.jetons.add(jetonSaturneVert);
        this.jetons.add(jetonTrouNoirMulticolor);
        for (ImageView jeton : this.jetons) {
            jeton.setFitWidth(100.0);
            jeton.setFitHeight(100.0);
        }
        Collections.shuffle(this.jetons);

        Random random = new Random();
        this.luneJauneColumn = random.nextInt(2, 4);
        this.luneJauneRow = random.nextInt(2, 4);
        this.triangleSolaireJauneColumn = random.nextInt(9, 11);
        this.triangleSolaireJauneRow = random.nextInt(2, 4);
        this.hexagoneCardinalJauneColumn = random.nextInt(2, 4);
        this.hexagoneCardinalJauneRow = random.nextInt(9, 11);
        int saturneJauneIndex = random.nextInt(2);
        if (saturneJauneIndex == 0){
            this.saturneJauneColumn = 9;
            this.saturneJauneRow = 10;
        } else {
            this.saturneJauneColumn = 10;
            this.saturneJauneRow = 9;
        }
        this.saturneRougeColumn = random.nextInt(2, 4);
        this.saturneRougeRow = random.nextInt(5, 7);
        this.hexagoneCardinalRougeColumn = random.nextInt(12, 14);
        this.hexagoneCardinalRougeRow = random.nextInt(2, 4);
        this.luneRougeColumn = random.nextInt(5, 7);
        this.luneRougeRow = random.nextInt(12, 14);
        this.triangleSolaireRougeColumn = random.nextInt(12, 14);
        this.triangleSolaireRougeRow = random.nextInt(12, 14);
        this.triangleSolaireBleuColumn = random.nextInt(5, 7);
        this.triangleSolaireBleuRow = random.nextInt(2, 4);
        this.luneBleuColumn = random.nextInt(12, 14);
        this.luneBleuRow = random.nextInt(5, 7);
        int saturneBleuIndex = random.nextInt(2);
        if (saturneBleuIndex == 0){
            this.saturneBleuColumn = 5;
            this.saturneBleuRow = 9;
        } else {
            this.saturneBleuColumn = 6;
            this.saturneBleuRow = 10;
        }
        this.hexagoneCardinalBleuColumn = random.nextInt(12, 14);
        this.hexagoneCardinalBleuRow = random.nextInt(9, 11);
        int hexagoneCardinalVertIndex = random.nextInt(2);
        if (hexagoneCardinalVertIndex == 0){
            this.hexagoneCardinalVertColumn = 5;
            this.hexagoneCardinalVertRow = 6;
        } else {
            this.hexagoneCardinalVertColumn = 6;
            this.hexagoneCardinalVertRow = 5;
        }
        int saturneVertIndex = random.nextInt(2);
        if (saturneVertIndex == 0){
            this.saturneVertColumn = 9;
            this.saturneVertRow = 5;
        } else {
            this.saturneVertColumn = 10;
            this.saturneVertRow = 6;
        }
        this.triangleSolaireVertColumn = random.nextInt(2, 4);
        this.triangleSolaireVertRow = random.nextInt(12, 14);
        this.luneVertColumn = random.nextInt(9, 11);
        this.luneVertRow = random.nextInt(12, 14);
        int trouNoirMulticolorIndex = random.nextInt(4);
        if (trouNoirMulticolorIndex == 0) {
            if (this.hexagoneCardinalVertColumn == 5) {
                this.trouNoirMulticolorColumn = 7;
                this.trouNoirMulticolorRow = 5;
            } else {
                this.trouNoirMulticolorColumn = 5;
                this.trouNoirMulticolorRow = 7;
            }
        } else if (trouNoirMulticolorIndex == 1) {
            if (this.saturneVertColumn == 9) {
                this.trouNoirMulticolorColumn = 10;
                this.trouNoirMulticolorRow = 7;
            } else {
                this.trouNoirMulticolorColumn = 8;
                this.trouNoirMulticolorRow = 5;
            }
        } else if (trouNoirMulticolorIndex == 2) {
            if (this.saturneBleuColumn == 5) {
                this.trouNoirMulticolorColumn = 7;
                this.trouNoirMulticolorRow = 10;
            } else {
                this.trouNoirMulticolorColumn = 5;
                this.trouNoirMulticolorRow = 8;
            }
        } else {
            if (this.saturneJauneColumn == 9) {
                this.trouNoirMulticolorColumn = 10;
                this.trouNoirMulticolorRow = 8;
            } else {
                this.trouNoirMulticolorColumn = 8;
                this.trouNoirMulticolorRow = 10;
            }
        }
    }
}
