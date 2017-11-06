package com.sparkcognition.graph;

import com.sparkcognition.maze.Cell;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class NodeBuilder {

    public static HashMap<Pair<Integer, Integer>, Node> graphBuilder(GraphBuilder all) {
        /*The graphBuilder returns a HashMap of the (x,y) co-ordinates in the maze as Key
        and the Node which stores the neighbours and is it a mine or not. So if any other constraints such as
        life or any feature is added this Node class can hold that information.*/

        HashMap<Pair<Integer, Integer>, Node> nodes = new HashMap<>();
        ArrayList<ArrayList<Cell>> cells = all.getCells();
        for (int i = 0; i < cells.size(); i++) {

            for (int j = 0; j < cells.get(0).size(); j++) {

                HashSet<Pair<Integer, Integer>> neighbours = new HashSet<>();

                boolean mine = cells.get(i).get(j).isMine();

                if (cells.get(i).get(j).isDown() && i < cells.size() - 1) {
                    neighbours.add(new Pair<>(i + 1, j));
                }
                if (cells.get(i).get(j).isUp()) {
                    neighbours.add(new Pair<>(i - 1, j));
                }
                if (cells.get(i).get(j).isLeft()) {
                    neighbours.add(new Pair<>(i, j - 1));
                }
                if (cells.get(i).get(j).isRight() && j < cells.get(0).size()) {
                    neighbours.add(new Pair<>(i, j + 1));
                }
                Node node = new Node(neighbours, mine);
                nodes.put(new Pair<>(i, j), node);
            }
        }

        return nodes;
    }
}
