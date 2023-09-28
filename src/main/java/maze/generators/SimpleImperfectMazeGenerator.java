package maze.generators;

import maze.Cell;
import maze.MazeGenerator;

import java.util.Random;
import java.util.Stack;

public class SimpleImperfectMazeGenerator implements MazeGenerator {
    Random rand = new Random();

    public SimpleImperfectMazeGenerator(Cell[] cell, int width, int height) {
        generateMaze(cell, width, height);
    }

    @Override
    public void generateMaze(Cell[] cell, int width, int height) {
        Stack<Integer> path = new Stack<>();
        path.push(0);

        while (!path.isEmpty()) {
            int id = path.peek();
            cell[id].setVisited();
            int x = id % width;
            int y = id / width;

            int[] possibilities = {1, 1, 1, 1};
            if (x == 0 || cell[id - 1].isVisited()) {
                possibilities[3] = 0;
            }
            if (x == width - 1 || cell[id + 1].isVisited()) {
                possibilities[1] = 0;
            }
            if (y == 0 || cell[id - width].isVisited()) {
                possibilities[0] = 0;
            }
            if (y == height - 1 || cell[id + width].isVisited()) {
                possibilities[2] = 0;
            }

            if (sum(possibilities) != 0) {
                int index = rand.nextInt(4);

                while (possibilities[index] == 0) {
                    index = rand.nextInt(4);
                }
                switch (index) {
                    case 0 -> {
                        cell[id].removeWall(0);
                        cell[id - width].removeWall(2);
                        path.push(id - width);
                    }
                    case 1 -> {
                        cell[id].removeWall(1);
                        cell[id + 1].removeWall(3);
                        path.push(id + 1);
                    }
                    case 2 -> {
                        cell[id].removeWall(2);
                        cell[id + width].removeWall(0);
                        path.push(id + width);
                    }
                    case 3 -> {
                        cell[id].removeWall(3);
                        cell[id - 1].removeWall(1);
                        path.push(id - 1);
                    }
                }
            } else {
                path.pop();
            }
        }
        if (cell[0].hasWall(1)) {
            cell[0].removeWall(1);
            cell[1].removeWall(3);
        } else {
            cell[0].removeWall(2);
            cell[width].removeWall(0);
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