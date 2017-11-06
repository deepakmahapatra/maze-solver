package com.sparkcognition.maze;

import com.sparkcognition.path.AllPaths;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static com.sparkcognition.maze.DrawMaze.allPaths;
import static com.sparkcognition.maze.MazeLoader.getFile;
import static org.junit.Assert.*;

public class AllPathsTest {
    @Test
    public void testDrawingMaze(){
        AllPaths all = prepareTest();
        assertTrue(all.getFinishCol()==0);
        assertTrue(all.getFinishRow()==0);
        assertTrue(all.getStartCol()==1);
        assertTrue(all.getStartRow()==2);
        assertTrue(all.getCells().get(1).get(1).mine);
    }

    private AllPaths prepareTest() {
        File file = new File("src/main/resources/mazes.txt");
        String absolutePath = file.getAbsolutePath();
        List<Maze> mazes = getFile(absolutePath);
        AllPaths all = allPaths(mazes.get(0));
        return all;
    }

}