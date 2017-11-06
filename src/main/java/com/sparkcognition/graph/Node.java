package com.sparkcognition.graph;

import javafx.util.Pair;

import java.util.HashSet;

/* Node Class is a facade over the All Paths as it stores the neighbours
and is it a mine or not. So if any other constraints such as
life or any feature is added this Node class can hold that information.*/

public class Node {

    public HashSet<Pair<Integer, Integer>> neighbours;
    public boolean mine;

    public Node(HashSet<Pair<Integer, Integer>> neighbours, boolean mine) {

        this.neighbours = neighbours;
        this.mine = mine;
    }

    @Override
    public String toString() {
        return "Node{" +
                ", neighbours=" + neighbours +
                ", mine=" + mine +
                '}';
    }

    public HashSet<Pair<Integer, Integer>> getNeighbours() {
        return neighbours;
    }

    public boolean isMine() {
        return mine;
    }

}
