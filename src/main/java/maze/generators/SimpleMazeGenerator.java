package maze.generators;

import maze.Cell;
import maze.MazeGenerator;

public class SimpleMazeGenerator implements MazeGenerator {

    public SimpleMazeGenerator(Cell[] cell, int width, int height, boolean perfect) {
        generateMaze(cell, width, height, perfect);
    }

    @Override
    public void generateMaze(Cell[] cell, int width, int height, boolean perfect) {

    }
}