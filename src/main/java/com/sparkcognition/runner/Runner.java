package com.sparkcognition.runner;

import com.sparkcognition.graph.Node;

import com.sparkcognition.maze.Maze;
import com.sparkcognition.graph.GraphBuilder;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

import static com.sparkcognition.graph.NodeBuilder.graphBuilder;
import static com.sparkcognition.maze.DrawMaze.allPaths;
import static com.sparkcognition.maze.MazeLoader.getFile;
import static com.sparkcognition.solver.BFSSolver.solver;

public class Runner {
    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        String inputPath = "";
        int lives = 3;
        File textFile = new File("answers.txt");

        //checking if arguments were given else using default values as in the assignment
        if (args.length == 0) {
            LOG.warn("No input was given. Using the default maze and default life of 3");
            File file = new File("src/main/resources/mazes.txt");
            inputPath = file.getAbsolutePath();
        } else if (args.length > 1) {
            inputPath = args[0];
            lives = Integer.parseInt(args[1].trim());
        } else {
            inputPath = args[0];
        }

        List<Maze> mazes = getFile(inputPath);
        LOG.info(String.format("Successfully uploaded the file"));

        //getting the all possible steps in the maze
        for (int mazenumber = 0; mazenumber < mazes.size(); mazenumber++) {
            long startTime = System.currentTimeMillis();
            GraphBuilder all = allPaths(mazes.get(mazenumber));
            LOG.info(String.format("Successfully decoded the maze number %d", mazenumber));

            Pair<Integer, Integer> startLocation = new Pair<>(all.getStartRow(), all.getStartCol());
            Pair<Integer, Integer> endLocation = new Pair<>(all.getFinishRow(), all.getFinishCol());
            HashMap<Pair<Integer, Integer>, Node> nodes = graphBuilder(all);

            String answer = solver(nodes, startLocation, endLocation, lives);
            long endtime = System.currentTimeMillis();

            if (answer != null) {
                writeFile(String.format("\nSuccessfully found the path to maze %d \n", mazenumber), textFile);
                writeFile(answer, textFile);
                LOG.info(String.format("Successfully found the path to end for maze %d, Processing time: %d", mazenumber, endtime - startTime));
            } else {
                writeFile(String.format("\nNo Path found the path to maze %d \n", mazenumber), textFile);
                LOG.warn(String.format("No path found the path to end for maze %d, Processing time: %d", mazenumber, endtime - startTime));
            }
        }

    }

    //helper method for writing the results to a file
    public static void writeFile(String answer, File textFile) {
        try {
            FileWriter fw = new FileWriter(textFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(answer);
            out.close();
            bw.close();
            fw.flush();
            fw.close();
        } catch (IOException e) {
            LOG.error("Write file not found");
        }

    }
}
