package com.ngoxuyu.ricochetrobots.solver;

public class Move
{
    public Board board;
    public int moveNumber;
    public String robot;
    public int[] oldPosition;
    public int[] newPosition;
    public String direction;

    public Move(Board board, String robot, int[] oldPosition, int[] newPosition, int moveNumber)
    {
        this.robot = robot;
        this.board = board;
        this.moveNumber = moveNumber;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;

        addDirection();
    }

    private void addDirection()
    {
        if (oldPosition[0] == newPosition[0]) // Déplacement en vertical
        {
            if (oldPosition[0] < newPosition[0])
            {
                direction = "DOWN";
            }
            else
            {
                direction = "UP";
            }
        }
        else
        {
            if (oldPosition[1] < newPosition[1])
            {
                direction = "RIGHT";
            }
            else
            {
                direction = "LEFT";
            }
        }

    }


}
