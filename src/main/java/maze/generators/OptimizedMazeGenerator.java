package maze.generators;

import maze.Cell;
import maze.MazeGenerator;

public class OptimizedMazeGenerator implements MazeGenerator {

    public OptimizedMazeGenerator(Cell[] cell, int width, int height, boolean perfect) {
        generateMaze(cell, width, height, perfect);
    }

    @Override
    public void generateMaze(Cell[] cell, int width, int height, boolean perfect) {
        System.out.println("OptimizedMazeGenerator");
    }
}