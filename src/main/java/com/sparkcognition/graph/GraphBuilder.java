package com.sparkcognition.graph;

import com.sparkcognition.maze.Cell;

import java.util.ArrayList;

/*Class to generate the maze with all the cells having decoded information for accessing the adjacent cell */
public class GraphBuilder {
    ArrayList<ArrayList<Cell>> cells;
    int startRow;
    int startCol;
    int finishRow;
    int finishCol;

    public GraphBuilder(ArrayList<ArrayList<Cell>> cells, int startRow, int startCol, int finishRow, int finishCol) {
        this.cells = cells;
        this.startRow = startRow;
        this.startCol = startCol;
        this.finishRow = finishRow;
        this.finishCol = finishCol;
    }

    public ArrayList<ArrayList<Cell>> getCells() {
        return cells;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getFinishRow() {
        return finishRow;
    }

    public int getFinishCol() {
        return finishCol;
    }
}
