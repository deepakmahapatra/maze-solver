package com.sparkcognition.maze;

/*Class to access information for a particular cell */
public class Cell {
    boolean up;
    boolean right;
    boolean down;
    boolean left;
    boolean start;
    boolean end;
    boolean mine;

    public Cell(boolean up, boolean right, boolean down, boolean left, boolean start, boolean end, boolean mine) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
        this.start = start;
        this.end = end;
        this.mine = mine;
    }

    public Cell(boolean stats[]) {
        this.up = stats[0];
        this.right = stats[1];
        this.down = stats[2];
        this.left = stats[3];
        this.start = stats[4];
        this.end = stats[5];
        this.mine = stats[6];
    }

    public boolean isUp() {
        return up;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isEnd() {
        return end;
    }

    public boolean isMine() {
        return mine;
    }
}
