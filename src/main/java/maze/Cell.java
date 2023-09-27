package maze;

public class Cell {
    public int x, y; // Coordinates
    public int id; // Unique ID
    public boolean visited = false;
    public boolean[] walls = {true, true, true, true}; // Top, Right, Bottom, Left

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void removeWall(int direction) {
        walls[direction] = false;
    }

    public String[] getCell() {
        String top = walls[0] ? "#" : ".";
        String right = walls[1] ? "#" : ".";
        String bot = walls[2] ? "#" : ".";
        String left = walls[3] ? "#" : ".";
        String[] cell = new String[3];
        cell[0] = "#" + top + "#";
        cell[1] = left + "." + right;
        cell[2] = "#" + bot + "#";
        return cell;
    }
}