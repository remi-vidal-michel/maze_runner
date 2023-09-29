package maze;

import maze.generators.GraphBasedMazeGenerator;
import maze.generators.OptimizedMazeGenerator;
import maze.generators.SimpleMazeGenerator;

public class Maze {
    public int width, height;
    public String generationType, generatorMethod;
    public Cell[] cell;

    public Maze(int width, int height, String generationType, String generatorMethod) {
        this.width = width;
        this.height = height;
        this.generationType = generationType; // perfect or imperfect
        this.generatorMethod = generatorMethod; // simple, graph or optimized
        cell = new Cell[width * height];
        initializeGrid();
        selectGenerator();
        displayMaze();
    }

    public void selectGenerator()  {
        switch (generationType) {
            case "perfect" -> {
                switch (generatorMethod) {
                    case "simple" -> new SimpleMazeGenerator(cell, width, height, true);
                    case "graph" -> new GraphBasedMazeGenerator(cell, width, height, true);
                    case "optimized" -> new OptimizedMazeGenerator(cell, width, height, true);
                }
            }
            case "imperfect" -> {
                switch (generatorMethod) {
                    case "simple" -> new SimpleMazeGenerator(cell, width, height, false);
                    case "graph" -> new GraphBasedMazeGenerator(cell, width, height, false);
                    case "optimized" -> new OptimizedMazeGenerator(cell, width, height, false);
                }
            }
        };
    }

    private void initializeGrid() {
        for (int id = 0; id < cell.length; id++) {
                cell[id] = new Cell(id);
            }
    }

    public void displayMaze() {
        cell[0].removeWall(0);
        cell[cell.length - 1].removeWall(2);
        int delta = 0;
        for (int y = 0; y < height; y++) {
            StringBuilder str1 = new StringBuilder();
            StringBuilder str2 = new StringBuilder();
            StringBuilder str3 = new StringBuilder();
            for (int x = 0; x < width; x++) {
                String[] strs = cell[x + delta].getCell();
                str1.append(strs[0]);
                str2.append(strs[1]);
                str3.append(strs[2]);
            }
            delta += width;
            System.out.println(str1 + "\n" + str2 + "\n" + str3);
        }
    }
}
