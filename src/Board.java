import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * The Board JPanel stores maze generation details and displays it. It also
 * displays the position in which the Player is at, and a defined end point for
 * the maze.
 */
public class Board extends JPanel {
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] north;
    
    private int boardSize;
    private static int tileSize = 15; // Default tile size
    
    private Player player;
    private Player player2;
    
    private static final int EAST = 0;
    private static final int SOUTH = 1;
    private static final int WEST = 2;
    private static final int NORTH = 3;
    
    // MultiplayerBoard can change this colour (to line colour) when the old goal is unneeded.
    protected Color mazeEndColor = Color.green;
    
    public Board(int boardSize) {
        // Initialise
        this.setFocusable(true);
        this.addMouseListener(new MouseInput());
        this.addKeyListener(new KeyListener());
        Color darkDarkGrey = new Color(26, 26, 26);
        this.setBackground(darkDarkGrey);

        // Set board size
        this.boardSize = boardSize;
        tileSize = (450 + boardSize) / boardSize; // 450 is a ratio magic number to get a reasonable tileSize to boardSize proportion
        
        // Generate maze
        MazeGenerator mg = new MazeGenerator(boardSize);
        east = mg.getEast();
        south = mg.getSouth();
        west = mg.getWest();
        north = mg.getNorth();
        
        // Create player
        player = new Player();
    }

	/**
	 * If a second player is needed, this sets Board's player2 instance
	 * variable. This is to be used only by MultiplayerBoard.
	 * 
	 * @param player2
	 */
    protected void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Needed to clear prior images
        Graphics2D g2D = (Graphics2D) g;
        displayMaze(g2D);
        
        Color darkOrange = new Color(236, 118, 0, 220);
        drawCharacter(g2D, player, darkOrange);
    }
    
    /**
     * Draws the maze walls, defined in arrays east[], south[], west[], north[].
     * 
     * @param g2D	Graphics2D from paintComponent()
     */
    public void displayMaze(Graphics2D g2D) {
        for (int i=0; i<boardSize; i++) {
            for (int j=0; j<boardSize; j++) {
                if (east[i][j]) {
                    drawLine(g2D, i+1, j, i+1, j+1);
                }
                if (south[i][j]) {
                    drawLine(g2D, i, j, i+1, j);
                }
                if (west[i][j]) {
                    drawLine(g2D, i, j, i, j+1);
                }
                if (north[i][j]) {
                    drawLine(g2D, i, j+1, i+1, j+1);
                }
            }
        }
        drawEndGoal(g2D, false);
    }
    
	/**
	 * Draws the green end goal onto the maze. If multiplayerEnd is true, the
	 * end goal is drawn on the bottom middle instead of the bottom left.
	 * 
	 * @param g2D	Graphics2D from paintComponent()
	 * @param multiplayerEnd	true if the multiplayer goal position is needed, false otherwise.
	 */
    protected void drawEndGoal(Graphics2D g2D, boolean multiplayerEnd) {
        float strokeWidth;
        // Check if stroke width is too thin
        if ((tileSize / 5) < 1) {
            strokeWidth = 1;
        } else {
            strokeWidth = tileSize / 5;
        }
        double readjustment = tileSize/2.0;
        g2D.setColor(mazeEndColor);
        g2D.setStroke(new BasicStroke(strokeWidth));
        
        Line2D line;
        // Is it the multiplayer ending?
    	if (multiplayerEnd) {
    		int middleOfBoard = boardSize/2;
    		System.out.println("boardSize: " + boardSize + " middle: " + middleOfBoard);
    		if ((boardSize % 2) == 0) { // The board is odd number of tiles wide (i.e. boardSize is even)
		        line = new Line2D.Double(middleOfBoard*tileSize - strokeWidth, 
                        boardSize*tileSize - readjustment, 
                        middleOfBoard*tileSize + strokeWidth, 
                        boardSize*tileSize - readjustment);
    		} else { // The board is even number of tiles wide (i.e. boardSize is odd)
    			// Make the end goal 2 tiles long
		        line = new Line2D.Double(middleOfBoard*tileSize - strokeWidth, 
                        boardSize*tileSize - readjustment, 
                        (middleOfBoard+1)*tileSize + strokeWidth, 
                        boardSize*tileSize - readjustment);
    		}
    	} else {
	        line = new Line2D.Double((boardSize-1)*tileSize - readjustment + strokeWidth, 
                                     boardSize*tileSize - readjustment, 
                                     boardSize*tileSize - readjustment - strokeWidth, 
                                     boardSize*tileSize - readjustment);
    	}
        g2D.draw(line);
    }
    
    /**
     * Draws a line, defined by 4 coordinates for start and end points.
     * 
     * @param g2D	Graphics2D from paintComponent()
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    private void drawLine(Graphics2D g2D, int x1, int y1, int x2, int y2) {
        // Shift up maze printing by half tileSize
        double readjustment = tileSize / 2.0;
        
        float strokeWidth;
        if ((tileSize / 5) < 1) {
            strokeWidth = 1;
        } else {
            strokeWidth = tileSize / 5;
        }
        
        g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2D.setColor(Color.gray);

        Line2D line = new Line2D.Double(x1*tileSize - readjustment, y1*tileSize - readjustment, 
                                        x2*tileSize - readjustment, y2*tileSize - readjustment);
        g2D.draw(line);
    }

    /**
     * Draws a square at the coordinates the Player is at.
     * 
     * @param g2D	Graphics2D from paintComponent()
     * @param p		the player
     * @param color	colour to represent the player with
     */
    protected void drawCharacter(Graphics2D g2D, Player p, Color color) {
        g2D.setPaint(color);
        
        double ratio = 1.0 / 2.0; // Magic number to make character width proportionate to tileSize, change it to liking.
        double width = ratio * tileSize;
        
        double padding;
        if (boardSize > 30) { // Tiles too small
            padding = tileSize / 3.0;
            width = tileSize / 2.0;
        } else {
            // tileSize = width + 2*padding, width = 3/4*tileSize
            padding = (0.5) * (tileSize - width);
        }
        
        // Shift up maze printing by half tileSize
        double readjustment = (tileSize / 2.0);
        

        Rectangle2D character = new Rectangle2D.Double((p.getTileX() * tileSize) + padding - readjustment, 
                                                       (p.getTileY() * tileSize) + padding - readjustment,
                                                       width, width);

        g2D.fill(character);
        
        g2D.setPaint(Color.black);
    }
    
    /**
     * Checks if the specified wall exists.
     * 
     * @param wall		EAST, SOUTH, WEST or NORTH
     * @param x			x coordinate of wanted tile
     * @param y			y coordinate of wanted tile
     * @return	true if the specified wall exists, false otherwise.
     */
    private boolean isWall(int wall, int x, int y) {
    	if (wall == EAST) {
            // This check is needed as the border is not detected if only walls are checked
            if ((x == 0) || (y == 0)) {
                return true;
            }
            return east[x][y];
    	} else if (wall == SOUTH) {
            if ((x == 0) || (y == 0)) {
                return true;
            }
            return south[x][y];
    	} else if (wall == WEST) {
            if ((x == 0) || (y == 0)) {
                return true;
            }
            return west[x][y];
    	} else if (wall == NORTH) {
            if ((x == 0) || (y == 0)) {
                return true;
            }
            return north[x][y];
    	} else {
    		throw new IllegalArgumentException("Invalid direction (should be from 0 to 3): " + wall);
    	}
    }

	/**
	 * This key adapter is added to this JPanel to detect key presses.
	 */
    public class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int x = player.getTileX();
            int y = player.getTileY();

            int endX = boardSize - 1; // Last tile on board
            int endY = boardSize - 1;
            
            int keyCode = e.getKeyCode();
            
            if (keyCode == KeyEvent.VK_UP) {
                // Check if a wall is present before moving
                if (!isWall(NORTH, x, y - 1)) {
                    player.move(0, -1);
                }
            }
            
            if (keyCode == KeyEvent.VK_DOWN) {
                if ((x == endX) && (y == endY)) { // End state
                	// Set player won
                    GameState.setPlayerWon(1);
                    Game.setStateEnd();
                }
                if (!isWall(SOUTH, x, y + 1)) {
                    player.move(0, 1);
                }
            }
            
            if (keyCode == KeyEvent.VK_LEFT) {
                if (!isWall(EAST, x - 1, y)) {
                    player.move(-1, 0);
                }
            }
            
            if (keyCode == KeyEvent.VK_RIGHT) {
                if (!isWall(WEST, x + 1, y)) {
                    player.move(1, 0);
                }
            }
            
            // If player 2 exists
            if (player2 != null) {
                int x2 = player2.getTileX();
                int y2 = player2.getTileY();
                
                // The multiplayer end goal has different x coordinates
                int endX1 = boardSize/2;
                int endX2 = (boardSize/2) + 1; // For boards with even number of tiles
                
                if (keyCode == KeyEvent.VK_W) {
                    if (!isWall(NORTH, x2, y2 - 1)) {
                        player2.move(0, -1);
                    }
                }
                
                if (keyCode == KeyEvent.VK_S) {
                	if ((boardSize % 2) == 0) { // odd no. tiles
                        if ((x2 == endX1) && (y2 == endY)) { // End state
                        	// Set player won
                            GameState.setPlayerWon(2);
                            Game.setStateEnd();
                        }
                	} else { // even no. tiles
                        if (((x2 == endX1) && (y2 == endY)) || (x2 == endX2) && (y2 == endY)) {
                        	// Set player won
                            GameState.setPlayerWon(2);
                            Game.setStateEnd();
                        }
                	}

                    if (!isWall(SOUTH, x2, y2 + 1)) {
                        player2.move(0, 1);
                    }
                }
                
                if (keyCode == KeyEvent.VK_A) {
                    if (!isWall(EAST, x2 - 1, y2)) {
                        player2.move(-1, 0);
                    }
                }
                
                if (keyCode == KeyEvent.VK_D) {
                    if (!isWall(WEST, x2 + 1, y2)) {
                        player2.move(1, 0);
                    }
                }
            }
            repaint();
        }
    }
    
	/**
	 * This mouse listener is to make sure the focus is on this JPanel when
	 * playing the game, so the keys work.
	 */
    public class MouseInput implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent arg0) {
            
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
            requestFocusInWindow();
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            requestFocusInWindow();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            
        }
    }
}