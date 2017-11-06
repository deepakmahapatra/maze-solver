package com.sparkcognition.maze;

import com.sparkcognition.path.AllPaths;

import java.util.ArrayList;

/*Buliding a maze with information about all the cells*/
public class DrawMaze {

    public static AllPaths allPaths(Maze maze) {
        AllPaths allPaths;
        ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
        //UP = 1 RIGHT = 2 DOWN = 4 LEFT = 8 START = 16 END = 32 MINE = 64

        int possibleOptions[] = new int[]{1, 2, 4, 8, 16, 32, 64};
        int startCol = 0, startRow = 0, finishRow = 0, finishCol = 0;

        for (int row = 0; row < maze.getHeight(); row++) {
            ArrayList<Cell> rowCells = new ArrayList<>();

            for (int col = 0; col < maze.getWidth(); col++) {
                boolean validCodes[] = new boolean[7];
                for (int k = 0; k < possibleOptions.length; k++) {
                    validCodes[k] = ((possibleOptions[k] & maze.getMaze().get(row).get(col)) != 0) ? true : false;
                }
                if (validCodes[4] != false) {
                    startRow = row;
                    startCol = col;
                }
                if (validCodes[5] != false) {
                    finishRow = row;
                    finishCol = col;
                }
                rowCells.add(new Cell(validCodes));
            }
            cells.add(rowCells);
        }
        allPaths = new AllPaths(cells, startRow, startCol, finishRow, finishCol);
        return allPaths;
    }
}
