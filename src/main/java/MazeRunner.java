import maze.Maze;
import maze.exceptions.MazeGenerationException;

public class MazeRunner {
    public static void main(String[] args) throws MazeGenerationException {

        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        String generationType = args[2];
        String generatorMethod = args[3];
        new Maze(width, height, generationType, generatorMethod);
    }
}
