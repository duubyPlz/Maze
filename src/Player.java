/**
 * The Player class notes the coordinates the player is at, on the board. It
 * also allows other classes to move the player.
 */
public class Player {
    private int tileX;
    private int tileY;
    
    /**
     * Construct a player who starts at 1, 1.
     */
    public Player() {
        // Initialise player to be at the first tile on board.
        tileX = 1;
        tileY = 1;        
    }
    
    /**
     * Constructs a player who starts at a defined tile.
     * 
     * @param x		x coordinate on board
     * @param y		y coordinate on board
     */
    public Player(int x, int y) {
        tileX = x;
        tileY = y;
    }
    
    /**
     * Gets the x coordinate on board.
     * @return	tileX
     */
    public int getTileX() {
        return tileX;
    }
    
    /**
     * Gets the y coordinate on board.
     * @return
     */
    public int getTileY() {
        return tileY;
    }
    
    /**
     * Moves a player a specified displacement in x & y directions.
     * 
     * @param tileX		difference in the x direction (e.g. -1 moves player left one tile)
     * @param tileY		difference in the y direction (e.g. 1 moves player down one tile)
     */
    public void move(int tileX, int tileY) {
        this.tileX += tileX;
        this.tileY += tileY;
    }
}