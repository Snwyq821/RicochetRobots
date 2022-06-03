package com.ngoxuyu.ricochetrobots.solver;

// A retirer

public class Square
{
    private int row, column; // Peut etre plutot utiliser les coordonées de Board.squares
    private boolean wallUp, wallRight, wallDown, wallLeft;
    private String color;
    private boolean target;
    private boolean occupied;

    public int getRow(){ return this.row; }
    public int getColumn(){ return this.column; }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }

    public boolean isTarget(){ return this.target; }
    public boolean isOccupied(){ return this.occupied; }

    // set target
    // set robot
    // set walls

    // check robot on target

    public Square(int row, int column)
    {
        this.row = row;
        this.column = column;
    }
}
