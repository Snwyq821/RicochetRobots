package com.ngoxuyu.ricochetrobots.solver;

import java.util.List;
import java.util.Stack;

public class Solution
{
    private Board board;
    private Stack<Move> moveList = new Stack<Move>();

    public void addMove(Move newMove)
    {
        this.moveList.push(newMove);
    }
    public void removeMove()
    {
        this.moveList.pop();
    }
}
