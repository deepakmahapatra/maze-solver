package com.sparkcognition.runner;

import com.sparkcognition.graph.Node;
import com.sparkcognition.maze.Maze;
import com.sparkcognition.path.AllPaths;
import javafx.util.Pair;
import org.junit.Test;

import java.io.File;

import java.util.HashMap;
import java.util.List;

import static com.sparkcognition.graph.GraphBuilder.graphBuilder;
import static com.sparkcognition.maze.DrawMaze.allPaths;
import static com.sparkcognition.maze.MazeLoader.getFile;
import static com.sparkcognition.solver.BFSSolver.solver;
import static org.junit.Assert.*;

public class RunnerTest {

    @Test
    public void testRunner() {
        AllPaths all = prepareTest();
        Pair<Integer, Integer> startLocation = new Pair<>(all.getStartRow(), all.getStartCol());
        Pair<Integer, Integer> endLocation = new Pair<>(all.getFinishRow(), all.getFinishCol());
        HashMap<Pair<Integer, Integer>, Node> nodes = graphBuilder(all);

        //Testing for the example case with One life
        String answer = solver(nodes, startLocation, endLocation, 1);
        assertTrue(answer.equals("[ 'right', 'up', 'up', 'left', 'left' ]"));

        //Testing for the example case with Three Lives
        answer = solver(nodes, startLocation, endLocation, 3);
        assertTrue(answer.equals("[ 'up', 'up', 'left' ]"));

    }

    private AllPaths prepareTest() {
        File file = new File("src/main/resources/mazes.txt");
        String absolutePath = file.getAbsolutePath();
        List<Maze> mazes = getFile(absolutePath);
        AllPaths all = allPaths(mazes.get(0));
        return all;
    }

}