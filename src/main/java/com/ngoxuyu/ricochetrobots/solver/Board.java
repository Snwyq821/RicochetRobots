package com.ngoxuyu.ricochetrobots.solver;

import java.util.List;

public class Board
{
    Square[][] squares = new Square[16][16]; // A retirer


    public static final int UP = 0;
    public static final int RIGHT  = 1;
    public static final int DOWN = 2;
    public static final int LEFT  = 3;

    private boolean[][] walls;

    private List<String> goals;
    private List<String> robots;
    private int[] currentGoal;         // cible
    private String mainRobot;   // Robot à placer sur la target

    private boolean isRobotOnTarget()
    {
        // Verifie les coordonnees de mainRobot et current Goal
        return false;
    }

    public void getCurrentBoard()
    {
        // Récupère les infos du plateau actuel
    }


}
