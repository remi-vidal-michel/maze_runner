package maze.generators;

import maze.Cell;
import maze.MazeGenerator;

import java.util.Random;
import java.util.Stack;

public class SimplePerfectMazeGenerator implements MazeGenerator {
    Random rand = new Random();

    public SimplePerfectMazeGenerator(Cell[] maze, int width, int height) {
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
//            System.out.println("id: " + id);
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
                    }
                    case 1 -> {
                        maze[id].removeWall(1);
                        maze[id + 1].removeWall(3);
                    }
                    case 2 -> {
                        maze[id].removeWall(2);
                        maze[id + width].removeWall(0);
                    }
                    case 3 -> {
                        maze[id].removeWall(3);
                        maze[id - 1].removeWall(1);
                    }
                }
                path.push(id + (index == 0 ? -width : index == 1 ? 1 : index == 2 ? width : -1));
                visited++;
            } else {
                path.pop();
            }
        }
    }

    public static int sum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
//        System.out.println(sum);
        return sum;
    }
}