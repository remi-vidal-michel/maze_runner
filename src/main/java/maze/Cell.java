package maze;

public class Cell {
    public int id;
    private boolean visited = false;
    private final boolean[] wall = {true, true, true, true}; // Top, Right, Bottom, Left

    public Cell(int id) {
        this.id = id;
    }

    // Getters and Setters

    public boolean isVisited() {
        return visited;
    }
    public void setVisited() { visited = true; }
    public boolean hasWall(int direction) { return wall[direction]; }
    public void removeWall(int direction) {
        wall[direction] = false;
    }

    // Methods

    public String[] printCell() {
        return new String[]{"#" + (wall[0] ? "#" : ".") + "#", (wall[3] ? "#" : ".") + "." + (wall[1] ? "#" : "."), "#" + (wall[2] ? "#" : ".") + "#"};
    }
}