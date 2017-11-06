package com.sparkcognition.maze;

import java.util.ArrayList;

//representing the input files in a matrix form for further manipulation
public class Maze {
    Integer height;
    Integer width;
    ArrayList<ArrayList<Integer>> maze;

    public Maze(Integer height, Integer width, ArrayList<ArrayList<Integer>> maze) {
        this.height = height;
        this.width = width;
        this.maze = maze;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public ArrayList<ArrayList<Integer>> getMaze() {
        return maze;
    }

    public void setMaze(ArrayList<ArrayList<Integer>> maze) {
        this.maze = maze;
    }
}
