package maze;

public class Cell {
    public int x, y; // Coordinates
    private boolean visited = false;
    private final boolean[] wall = {true, true, true, true}; // Top, Right, Bottom, Left

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isVisited() {
        return visited;
    }
    public void setVisited() { this.visited = true; }
    public boolean hasWall(int direction) { return wall[direction]; }
    public void removeWall(int direction) {
        wall[direction] = false;
    }

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