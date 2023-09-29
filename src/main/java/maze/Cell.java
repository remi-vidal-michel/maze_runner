package maze;

public class Cell {
    public int id; // Cell's id in the maze
    private boolean visited = false;
    private final boolean[] wall = {true, true, true, true}; // Top, Right, Bottom, Left

    public Cell(int id) {
        this.id = id;
    }

    // Getters and Setters

    public boolean isVisited() {
        return visited;
    }
    public void setVisited() { this.visited = true; }
    public boolean hasWall(int direction) { return wall[direction]; }
    public void removeWall(int direction) {
        wall[direction] = false;
    }

    // Methods

    public String[] getCell() {
        String top = wall[0] ? "#" : ".";
        String right = wall[1] ? "#" : ".";
        String bot = wall[2] ? "#" : ".";
        String left = wall[3] ? "#" : ".";
        String[] cell = new String[3];
        cell[0] = "#" + top + "#";
        cell[1] = left + "." + right;
        cell[2] = "#" + bot + "#";
        return cell;
    }
}