package com.sparkcognition.maze;

import com.sparkcognition.runner.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*This class implements a few static methods
which will be used as a util for getting
the maze information from the text file provided*/

public class MazeLoader {
    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    public static List<Maze> getFile(String fileName) {
        List<Maze> mazes = new ArrayList<Maze>();

        try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().toString();
                List<String> details = Arrays.asList(new String(line).split("-"));
                Maze maze = buildMaze(details);
                mazes.add(maze);
            }
        } catch (FileNotFoundException e) {
            LOG.error("Error Loading the file, check the file path or the file and run again");
        }
        return mazes;
    }

    //this method will return a basic input maze which can be read by the DrawMaze
    // class in a matrix form for generating all the paths
    private static Maze buildMaze(List<String> details) {

        //Handling all the types of input that has been seen in the test text messages
        List<String> dimension = Arrays.asList((details.get(0).toString()
                .replace("\uFEFF", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll("\\s", "")
                .split(",")));

        List<String> mazeMatrix = Arrays.asList((details.get(1)
                .replace("\uFEFF", "")
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\\s", "")
                .split(",")));

        int height = Integer.parseInt(dimension.get(0).trim());
        int width = Integer.parseInt(dimension.get(1).trim());
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

        int k = 0;
        for (int row = 0; row < height; row++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int col = 0; col < width; col++) {
                temp.add(Integer.parseInt(mazeMatrix.get(k)));
                k++;
            }
            matrix.add(temp);
        }
        Maze maze = new Maze(height, width, matrix);
        return maze;
    }
}
