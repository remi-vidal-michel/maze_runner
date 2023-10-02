package maze.generators;

import maze.Cell;
import maze.MazeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Random;

public class GraphBasedMazeGenerator implements MazeGenerator {

    private final Random random = new Random();

    public GraphBasedMazeGenerator(Cell[] cell, int width, int height, boolean perfect) {
        generateMaze(cell, width, height, perfect);
    }

    @Override
    public void generateMaze(Cell[] cell, int width, int height, boolean perfect) {
        List<Integer> possibilities = new ArrayList<>(4); // Possible directions
        Stack<Integer> path = new Stack<>(); // Path of visited cells
        path.push(0); // Starting cell

        while (!path.isEmpty()) {
            possibilities.clear(); // Resets possible directions (main difference with SimpleMazeGenerator)
            int id = path.peek(); // Current cell
            cell[id].setVisited(); // Marks current cell as visited
            int x = id % width;
            int y = id / width;

            if (y != 0 && !cell[id - width].isVisited()) { // Checks if top cell is unvisited or out of bounds
                possibilities.add(0);
            }
            if (x != width - 1 && !cell[id + 1].isVisited()) { // Checks if right cell is unvisited or out of bounds
                possibilities.add(1);
            }
            if (y != height - 1 && !cell[id + width].isVisited()) { // Checks if bottom cell is unvisited or out of bounds
                possibilities.add(2);
            }
            if (x != 0 && !cell[id - 1].isVisited()) { // Checks if left cell is unvisited or out of bounds
                possibilities.add(3);
            }

            if (!possibilities.isEmpty()) {
                int direction = random.nextInt(possibilities.size()); // Chooses a random direction
                switch (possibilities.get(direction)) {
                    case 0 -> {
                        cell[id].removeWall(0); // Removes top wall
                        cell[id - width].removeWall(2); // Removes bottom wall of top cell
                        path.push(id - width); // Makes top cell current for next iteration
                    }
                    case 1 -> {
                        cell[id].removeWall(1); // Removes right wall
                        cell[id + 1].removeWall(3); // Removes left wall of right cell
                        path.push(id + 1); // Makes right cell current for next iteration
                    }
                    case 2 -> {
                        cell[id].removeWall(2); // Removes bottom wall
                        cell[id + width].removeWall(0); // Removes top wall of bottom cell
                        path.push(id + width); // Makes bottom cell current for next iteration
                    }
                    case 3 -> {
                        cell[id].removeWall(3); // Removes left wall
                        cell[id - 1].removeWall(1); // Removes right wall of left cell
                        path.push(id - 1); // Makes left cell current for next iteration
                    }
                }
            } else {
                path.pop(); // Backtracks if no unvisited neighbors
            }
        }
        if (!perfect) { // Creates a loop if the maze is imperfect
            if (cell[0].hasWall(1)) {
                cell[0].removeWall(1);
                cell[1].removeWall(3);
            } else {
                cell[0].removeWall(2);
                cell[width].removeWall(0);
            }
        }
    }
}