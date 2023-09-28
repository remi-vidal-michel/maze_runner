package maze.generators;

import maze.Cell;
import maze.MazeGenerator;

import java.util.Random;

public class GraphBasedMazeGenerator implements MazeGenerator {
    Random rand = new Random();

    public GraphBasedMazeGenerator(Cell[] cell, int width, int height) {
        generateMaze(cell, width, height);
    }

    @Override
    public void generateMaze(Cell[] cell, int width, int height) {
        System.out.println("GraphBasedMazeGenerator");
    }
}