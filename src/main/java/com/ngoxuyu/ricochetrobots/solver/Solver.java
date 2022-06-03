package com.ngoxuyu.ricochetrobots.solver;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Solver
{
    private Board board;
    private int maxDepth; // Profondeur max a chercher
    private int arreteToi = 1000; // Nombre de noeuds max a verifier -> eviter de tourner en rond
    private boolean solutionFound = false;

    Solution solution = new Solution();

    public void solverOmegalol()
    {
        int nodeVerified = 0;
        while (!solutionFound || nodeVerified > arreteToi)
        {
            // Recursion ici

            // Effectuer un déplacement d'un Pion sur une Board


        }

        if(nodeVerified > arreteToi)
        {
            // Indiquer aucune solution
        }


    }
}
