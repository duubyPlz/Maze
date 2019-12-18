/**
 * The GameState class represents the current chosen state the game is in.
 *  - Modes: 0 single player, 1 single player with timer, 2 double player
 *  - Difficulties: 0 easy, 1 medium, 2 hard
 *  - player won: 0 nobody, 1 player1, 2 player2
 */
public class GameState { 
    private int mode;
    private int difficulty;
    private static int playerWon;
    
    public GameState() {
        mode = 0;
        difficulty = 0;    
        
        // Default dummy value
        playerWon = 0;
    }
    
    /**
     * Sets the game mode.
     * 
     * @param mode
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * Sets the game difficulty.
     * 
     * @param difficulty
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    /**
     * Sets the winning player.
     * 
     * @param player
     */
    public static void setPlayerWon(int player) {
        playerWon = player;
    }
    
    /**
     * Gets the current game mode.
     * 
     * @return	mode
     */
    public int getMode() {
        return mode;
    }
    
    /**
     * Gets the current game difficulty.
     * 
     * @return	difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }
    
    /**
     * Gets the winning player.
     * 
     * @return	playerWon
     */
    public int getPlayerWon() {
        return playerWon;
    }
}
