package maze.generators;

import maze.Cell;
import maze.MazeGenerator;

import java.util.Random;
import java.util.Stack;

public class SimpleImperfectMazeGenerator implements MazeGenerator {
    Random rand = new Random();

    public SimpleImperfectMazeGenerator(Cell[] maze, int width, int height) {
        generateMaze(maze, width, height);
    }

    @Override
    public void generateMaze(Cell[] maze, int width, int height) {
        Stack<Integer> path = new Stack<>();
        path.push(0);
        int visited = 1;

        while (visited < width * height) {
            int id = path.peek();
            maze[id].visited = true;
            int x = id % width;
            int y = id / width;

            int[] possibilities = {1, 1, 1, 1};
            if (x == 0 || maze[id - 1].isVisited()) {
                possibilities[3] = 0;
            }
            if (x == width - 1 || maze[id + 1].isVisited()) {
                possibilities[1] = 0;
            }
            if (y == 0 || maze[id - width].isVisited()) {
                possibilities[0] = 0;
            }
            if (y == height - 1 || maze[id + width].isVisited()) {
                possibilities[2] = 0;
            }

            if (sum(possibilities) != 0) {
                int index = rand.nextInt(4);

                while (possibilities[index] == 0) {
                    index = rand.nextInt(4);
                }
                switch (index) {
                    case 0 -> {
                        maze[id].removeWall(0);
                        maze[id - width].removeWall(2);
                        path.push(id - width);
                    }
                    case 1 -> {
                        maze[id].removeWall(1);
                        maze[id + 1].removeWall(3);
                        path.push(id + 1);
                    }
                    case 2 -> {
                        maze[id].removeWall(2);
                        maze[id + width].removeWall(0);
                        path.push(id + width);
                    }
                    case 3 -> {
                        maze[id].removeWall(3);
                        maze[id - 1].removeWall(1);
                        path.push(id - 1);
                    }
                }
                visited++;
            } else {
                path.pop();
            }
        }
        if (maze[0].walls[1]) {
            maze[0].removeWall(1);
            maze[1].removeWall(3);
        } else {
            maze[0].removeWall(2);
            maze[width].removeWall(0);
        }
    }

    public static int sum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}