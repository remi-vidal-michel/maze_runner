package maze;

import maze.exceptions.MazeGenerationException;
import maze.generators.GraphBasedMazeGenerator;
import maze.generators.OptimizedMazeGenerator;
import maze.generators.SimpleImperfectMazeGenerator;
import maze.generators.SimplePerfectMazeGenerator;

public class Maze {
    public int width, height;
    public String generationType, generatorMethod;
    public Cell[] maze;

    public Maze(int width, int height, String generationType, String generatorMethod) throws MazeGenerationException {
        this.width = width;
        this.height = height;
        this.generationType = generationType;
        this.generatorMethod = generatorMethod;
        maze = new Cell[width * height];
        initializeGrid();
        selectGenerator();
        displayMaze();
    }

    public void selectGenerator() throws MazeGenerationException {
        MazeGenerator generator = switch (generationType.toLowerCase()) {

            case "perfect" -> switch (generatorMethod.toLowerCase()) {
                case "simple" -> new SimplePerfectMazeGenerator(maze, width, height);
                case "graph" -> new GraphBasedMazeGenerator();
                case "optimized" -> new OptimizedMazeGenerator();
                default -> throw new MazeGenerationException("Méthode de génération invalide.");
            };
            case "imperfect" -> switch (generatorMethod.toLowerCase()) {
                case "simple" -> new SimpleImperfectMazeGenerator();
                case "graph" -> new GraphBasedMazeGenerator();
                case "optimized" -> new OptimizedMazeGenerator();
                default -> throw new MazeGenerationException("Méthode de génération invalide.");
            };
            default -> throw new MazeGenerationException("Type de labyrinthe invalide.");
        };
    }

    private void initializeGrid() {
        int id = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                maze[id] = new Cell(x, y);
                maze[id].setId(id);
                id++;
            }
        }
    }

    public void displayMaze() {
        maze[0].removeWall(3);
        maze[maze.length - 1].removeWall(1);
        int delta = 0;
        for (int y = 0; y < height; y++) {
            StringBuilder str1 = new StringBuilder();
            StringBuilder str2 = new StringBuilder();
            StringBuilder str3 = new StringBuilder();
            for (int x = 0; x < width; x++) {
                String[] strs = maze[x + delta].getCell();
                str1.append(strs[0]);
                str2.append(strs[1]);
                str3.append(strs[2]);
            }
            delta += width;
            System.out.println(str1 + "\n" + str2 + "\n" + str3);
        }
    }
}
