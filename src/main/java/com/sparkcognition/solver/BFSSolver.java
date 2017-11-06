package com.sparkcognition.solver;

import com.sparkcognition.graph.Node;
import javafx.util.Pair;

import java.util.*;

public class BFSSolver {

    public static String solver(HashMap<Pair<Integer, Integer>, Node> nodes, Pair<Integer, Integer> startLocation, Pair<Integer, Integer> endLocation, int maxlife) {
        int lifeCount = 0;
        //do BFS. while adding nodes to the queue keep count of mines.
        //Once the mine count reaches 2 do not add any more mines from the list of nodes.
        //There will be an additional check for mines before adding a node to queue.

        HashMap<Pair<Integer, Integer>, Pair<Integer, Integer>> prev = new HashMap<>();
        List<Pair<Integer, Integer>> shortestPath = new ArrayList<>();

        //Maintaining the queue for visiting a Breadth first Search
        // and visitedNodes to keep track of Nodes visited
        Queue<Pair<Integer, Integer>> queueNodes = new LinkedList<>();
        HashSet<Pair<Integer, Integer>> visitedNodes = new HashSet<>();

        Pair<Integer, Integer> current = startLocation;

        //do BFS
        queueNodes.add(startLocation);

        while (!queueNodes.isEmpty()) {
            current = queueNodes.remove();
            if (current.equals(endLocation)) {
                break;
            } else {
                if (lifeCount < maxlife - 1) {
                    for (Pair<Integer, Integer> node : nodes.get(current).neighbours) {
                        if (!visitedNodes.contains(node)) {
                            queueNodes.add(node);
                            visitedNodes.add(node);
                            prev.put(node, current);
                        }
                    }
                } else {
                    for (Pair<Integer, Integer> node : nodes.get(current).neighbours) {
                        if (!visitedNodes.contains(node) && !nodes.get(node).mine) {
                            queueNodes.add(node);
                            visitedNodes.add(node);
                            prev.put(node, current);
                        }
                    }
                }
            }

            if (current.equals(endLocation)) {
                break;
            }
        }
        if (!current.equals(endLocation)) {
            return (null);
        }
        for (Pair<Integer, Integer> node = endLocation; node != startLocation; node = prev.get(node)) {
            shortestPath.add(node);
        }
        shortestPath.add(startLocation);
        //Reversing the shortestPath to build it as start to end navigation
        Collections.reverse(shortestPath);

        StringBuffer path = new StringBuffer();
        path.append("[ ");

        for (int count = 1; count < shortestPath.size(); count++) {
            path.append(nodeToPath(shortestPath, count));

            if (count == shortestPath.size() - 1) {
                path.append(" ]");
            } else {
                path.append(", ");
            }
        }
        return path.toString();
    }

    //Building Nodes to navigation path as required by the challenge
    private static StringBuffer nodeToPath(List<Pair<Integer, Integer>> shortestPath, int count) {
        StringBuffer path = new StringBuffer();

        int xdiff = shortestPath.get(count).getKey() - shortestPath.get(count - 1).getKey();
        int ydiff = shortestPath.get(count).getValue() - shortestPath.get(count - 1).getValue();
        if (xdiff > 0) {
            path.append("'down'");
        } else if (xdiff < 0) {
            path.append("'up'");
        } else if (ydiff > 0) {
            path.append("'right'");
        } else if (ydiff < 0) {
            path.append("'left'");
        }
        return path;
    }
}