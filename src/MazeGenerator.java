import java.util.Random;

/**
 * MazeGenerator generates square mazes of a specified size (side length).
 * Information about all the walls of the maze are stored in 4 two-dimensional
 * arrays (east, south, west, north).
 * <p>
 * The generation process can be described by firstly initialising all walls to
 * being up. It then recursively iterates over all tiles (starting from the
 * topmost left tile) and decides a random wall to be knocked down (generate
 * holes) for every single tile it visits. It makes sure to visit the whole
 * board by using the visited array (but not visiting tiles on the brim).
 * <p>
 * The resulting maze is then passed to Board via the 4 wall arrays.
 */
public class MazeGenerator {    
    // Document all existing walls as true, false otherwise.
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] north;
    
    // Needed to tell if we've visited every tile on the board
    private boolean[][] visited;
    
    public MazeGenerator(int boardSize) {
        // Construct arrays (+2 is needed to dictate an outer border)
        east = new boolean[boardSize+2][boardSize+2];
        south = new boolean[boardSize+2][boardSize+2];
        west = new boolean[boardSize+2][boardSize+2];
        north = new boolean[boardSize+2][boardSize+2];
        
        visited = new boolean[boardSize+2][boardSize+2];
        
        // Initialise all walls
        for (int i=1; i<boardSize+2; i++) {
            for (int j=1; j<boardSize+2; j++) {
                east[i][j] = true;
                south[i][j] = true;
                west[i][j] = true;
                north[i][j] = true;
            }
        }
        
        // Initialise visited borders, cannot visit the border
        for (int i=0; i<boardSize+2; i++) {
            visited[i][0] = true;
            visited[0][i] = true;
            visited[i][boardSize] = true;
            visited[boardSize][i] = true;
        }        
        
        // Then generate holes through walls
        generateHoles();
    }
    
    /**
     * Entry method to generating holes in walls.
     */
    public void generateHoles() {
        // Random number generator
        Random r = new Random();
        
        generateHoles(r, 1, 1);
    }
    
	/**
	 * Recursively generates holes in initially built walls for every tile on
	 * the board.
	 * 
	 * @param r		Random - random number generator
	 * @param x		x coordinate of the specified tile
	 * @param y		y coordinate of the specified tile
	 */
    private void generateHoles(Random r, int x, int y) {
        visited[x][y] = true;
        
        while (!visited[x-1][y] || !visited[x][y-1] || !visited[x+1][y] || !visited[x][y+1]) {
            while (true) { // Needed as we must force it to go in one of the if cases to fully explore all tiles, else it will just trace recursion path & break.
                 // Random integer between 0 & 3
                int randomNumber = r.nextInt(4);
                if ((randomNumber == 0) && (!visited[x+1][y])) { // East wall is to be removed
                    east[x][y] = false;
                    west[x+1][y] = false;
                    generateHoles(r, x+1, y);
                    break;
                } else if ((randomNumber == 1) && (!visited[x][y-1])) { // South wall is to be removed
                    south[x][y] = false;
                    north[x][y-1] = false;
                    generateHoles(r, x, y-1);
                    break;
                } else if ((randomNumber == 2) && (!visited[x-1][y])) { // West wall is to be removed
                    west[x][y] = false;
                    east[x-1][y] = false;
                    generateHoles(r, x-1, y);
                    break;
                } else if ((randomNumber == 3) && (!visited[x][y+1])) { // North wall is to be removed
                    north[x][y] = false;
                    south[x][y+1] = false;
                    generateHoles(r, x, y+1);
                    break;
                }
            }
        }
    }
    
    /**
     * Gets the two-dimensional array east.
     * 
     * @return east[][]
     */
    public boolean[][] getEast() {
        return east;
    }
    
    /**
     * Gets the two-dimensional array south.
     * 
     * @return south[][]
     */
    public boolean[][] getSouth() {
        return south;
    }
    
    /**
     * Gets the two-dimensional array west.
     * 
     * @return west[][]
     */
    public boolean[][] getWest() {
        return west;
    }
    
    /**
     * Gets the two-dimensional array north.
     * 
     * @return north[][]
     */
    public boolean[][] getNorth() {
        return north;
    }
}