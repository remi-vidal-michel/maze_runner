package maze.generators;

import maze.Cell;
import maze.MazeGenerator;

public class GraphBasedMazeGenerator implements MazeGenerator {

    public GraphBasedMazeGenerator(Cell[] cell, int width, int height, boolean perfect) {
        generateMaze(cell, width, height, perfect);
    }

    @Override
    public void generateMaze(Cell[] cell, int width, int height, boolean perfect) {
        System.out.println("GraphBasedMazeGenerator");
    }
}