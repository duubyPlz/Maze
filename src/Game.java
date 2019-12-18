import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Game class is the main entry point for the maze game. It manages a JFrame
 * that chooses which JPanel to host in a particular state. The chosen JPanels
 * are then added to a main panel.
 * 
 * @author	
 * COMP2911 Project
 */
public class Game {
    private static enum State {
        MENU,
        DIFFICULTY,
        GAME,
        END
    };
    private static State state = State.MENU;
    private static JFrame frame = new JFrame("Maze");
    private static GameState gameState = new GameState();
    private static TimerPanel tp;
    
    // Substituting for time or player when one doesn't exist in that state
    private static final int DUMMY_VALUE = -1; 
    
    /**
     * 
     */
    public Game() {
        frame.setSize(480, 500);
        frame.setLocationRelativeTo(null);
        
        initialiseGUI();
    }
    
    /**
     * In charge of creating, swapping panels and managing layouts.
     */
    private static void initialiseGUI() {
        int boardSize;
        
        // Initialise frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        if (state == State.MENU) { // 1. Main menu
            frame.setSize(480, 520);
            frame.getContentPane().removeAll();

            MainMenu menu = new MainMenu(gameState);
            
            frame.setContentPane(menu);
        } else if (state == State.DIFFICULTY) { // 2. Difficulty menu
        	frame.setSize(480, 500);
            frame.getContentPane().removeAll();
            
            DifficultyMenu difficulty= new DifficultyMenu(gameState);

            frame.setContentPane(difficulty);
        } else if (state == State.GAME) { // 3. Game screen        	
        	// Difficulty:
            if (gameState.getDifficulty() == 0) { //easy
            	// Resize frame
            	frame.setSize(480, 530);
                boardSize = 10;
            } else if (gameState.getDifficulty() == 1) {
            	// Resize frame
            	frame.setSize(480, 540);
                boardSize = 15; //medium
            } else {
            	// Resize frame
            	frame.setSize(480, 540);
                boardSize = 20; //hard
            }
            
            // Mode:
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            if (gameState.getMode() == 0) { //singleplayer
                frame.getContentPane().removeAll();
                
                // Add board
                Board board = new Board(boardSize);
                
                mainPanel.add(board, BorderLayout.CENTER);
                
                frame.setContentPane(mainPanel);
        	} else if(gameState.getMode() == 1) { //singleplayer timed
                frame.getContentPane().removeAll();
                
                // Resize frame
                frame.setSize(480, 575);

                // Add timer and board
                tp = new TimerPanel();
                Board board = new Board(boardSize);
                
                mainPanel.add(tp, BorderLayout.NORTH);
                mainPanel.add(board, BorderLayout.CENTER);
                
                frame.setContentPane(mainPanel);
            } else if (gameState.getMode() == 2) { //multiplayer
                frame.getContentPane().removeAll();
                
                // Add board
                MultiPlayerBoard board = new MultiPlayerBoard(boardSize);
                
                mainPanel.add(board, BorderLayout.CENTER);
                
                frame.setContentPane(mainPanel);
            } else {
            	throw new IllegalArgumentException("Unknown mode: " + gameState.getMode());
            }
            
            // Add navigation panel
            NavigationPanel np = new NavigationPanel();
            mainPanel.add(np, BorderLayout.PAGE_END);
        } else if (state == State.END) { // 4. End screen
            frame.getContentPane().removeAll();
            
            // Mode endings
            int mode = gameState.getMode();
            EndMenu end = null;
            if (mode == 0) { // Singleplayer end screen
                end = new EndMenu(0, DUMMY_VALUE, DUMMY_VALUE);
            } else if (mode == 1) { // Timed singleplayer end screen
                end = new EndMenu(1, tp.getTimeCounter(), DUMMY_VALUE);
            } else if (mode == 2) { // Multiplayer end screen
                end = new EndMenu(2, DUMMY_VALUE, gameState.getPlayerWon());
            }
            if (end != null) {
                frame.setContentPane(end);
            } else {
                throw new IllegalArgumentException("Incorrect mode: " + mode);
            }
        }
        
        frame.setVisible(true);
    }
    
    /** 
     * Sets the game's state to displaying the main menu.
     */
    public static void setStateMenu() {
        state = State.MENU;
        initialiseGUI();
    }
    
    
    /**
     * Sets the game's state to displaying gameplay.
     */
    public static void setStateGame() {
        state = State.GAME;
        initialiseGUI();
    }
    
    /**
     * Sets the game's state to displaying difficulty menu.
     */
    public static void setStateDifficulty() {
        state = State.DIFFICULTY;
        initialiseGUI();
    }
    
    /**
     * Sets the game's state to displaying end screen.
     */
    public static void setStateEnd() {
        state = State.END;
        initialiseGUI();
    }    
    
    /**
     * Main function to create game.
     * @param args	command line arguments, unused.
     */
    public static void main(String[] args) {
        Game playGame = new Game();
    }
}
