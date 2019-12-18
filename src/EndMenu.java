import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The EndMenu JPanel manages the different text that appear for each
 * corresponding end state (casual, timed, multiplayer - results such as time,
 * player that won). It also constructs JButtons that are added to this panel.
 */
public class EndMenu extends JPanel {
    private JButton menuButton;
    private JButton retryButton;
    
    private int ending;
    private int time;
    private int player;
    
    private static final int SINGLEPLAYER = 0;
    private static final int TIMEDSINGLE = 1;
    private static final int MULTIPLAYER = 2;
    
    private final Color grey = new Color(190, 190, 190);
    private final Color orange = new Color(255, 128, 0);
    
    /**
	 * Initialises this panel, all buttons and instance variables.
	 * <p>
	 * Types of endings: casual (SINGLEPLAYER), timed (TIMEDSINGLE), 2 players
	 * (MULTIPLAYER).
	 * 
	 * @param ending	the type of ending required
	 * @param time		if the ending is TIMEDSINGLE, the time taken to finish maze;
	 *            		else a dummy value.
	 * @param player	if the ending is MULTIPLAYER, the player that won; else a
	 *            		dummy value.
	 */
    public EndMenu(int ending, int time, int player) {
        // Initialise panel
        this.setBackground(Color.white);
        this.setLayout(null);
        
        // Initialise instance variables (will be left blank if not related)
        this.ending = ending;
        if (ending == TIMEDSINGLE) {
            this.time = time;
        } else if (ending == MULTIPLAYER) {
            this.player = player;
        }
        
        // Construct buttons
        menuButton = new JButton("back to menu");
        retryButton = new JButton("retry");
        
        // Initialise button attributes:
        Font buttonFont = new Font("trebuchet ms", Font.BOLD, 25);
        
        menuButton.setOpaque(true);
        retryButton.setOpaque(true);
        
        //bounds
        menuButton.setBounds(135, 270, 200, 50);
        retryButton.setBounds(190, 330, 95, 50);
        
        //font
        menuButton.setFont(buttonFont);
        retryButton.setFont(buttonFont);
        
        //background
        menuButton.setBackground(grey);
        retryButton.setBackground(grey);
        
        //border
        menuButton.setBorderPainted(false);
        retryButton.setBorderPainted(false);
        
        //mouse listener
        menuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Entered menu button
            	menuButton.setBackground(orange);
            }
            
            public void mouseExited(MouseEvent e) {
                // Exited menu button
            	menuButton.setBackground(grey);
            }
            
            public void mousePressed(MouseEvent e) {
            	// Pressed menu button
                Game.setStateMenu();
            }
        });
        retryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Entered retry button
            	retryButton.setBackground(orange);
            }
            
            public void mouseExited(MouseEvent e) {
                // Exited retry button
            	retryButton.setBackground(grey);
            }
            
            public void mousePressed(MouseEvent e) {
            	// Pressed retry button
                Game.setStateGame();
            }
        });
        
        // Add them to this panel
        this.add(menuButton);
        this.add(retryButton);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
        if (ending == SINGLEPLAYER) {
            paintSingleEnding(g2D);
        } else if (ending == TIMEDSINGLE) {
            paintTimedEnding(g2D);
        } else if (ending == MULTIPLAYER) {
            paintMultiEnding(g2D);
        }
    }
    
    /**
     * Paints the specific text for the SINGLEPLAYER ending to g2D.
     * 
     * @param g2D	Graphics2D from paintComponent()
     */
    private void paintSingleEnding(Graphics2D g2D) {
        // Title
        Font titleFont = new Font("trebuchet ms", Font.PLAIN, 38);
        g2D.setFont(titleFont);
        Color greyTranslucent = new Color(90, 90, 90, 200);
        g2D.setColor(greyTranslucent);
        g2D.drawString("You've completed", 80, 160);
        g2D.drawString("the maze!", 145, 200);
    }
    
    /**
     * Paints the specific text and time for the TIMEDSINGLE ending to g2D.
     * 
     * @param g2D	Graphics2D from paintComponent()
     */
    private void paintTimedEnding(Graphics2D g2D) {
        // Title
        Font titleFont = new Font("trebuchet ms", Font.PLAIN, 38);
        g2D.setFont(titleFont);
        Color greyTranslucent = new Color(90, 90, 90, 200);
        g2D.setColor(greyTranslucent);
        g2D.drawString("You completed", 100, 120);
        g2D.drawString("the maze in", 128, 160);
        
        // Time
        Font timeFont = new Font("trebuchet ms", Font.BOLD, 45);
        g2D.setFont(timeFont);
        Color grey = new Color(80, 80, 80);
        g2D.setColor(grey);
        if (time < 10) {
        	g2D.drawString("" + time, 225, 210);
        } else if (time < 100) {
        	g2D.drawString("" + time, 210, 210);
        } else {
        	g2D.drawString("" + time, 195, 210);
        }
        
        // Title
        g2D.setFont(new Font("trebuchet ms", Font.PLAIN, 30));
        if (time == 1) {
        	g2D.drawString("second", 190, 240);
        } else {
        	g2D.drawString("seconds", 185, 240);
        }
    }
    
    /**
     * Paints the specific text for the MULTIPLAYER ending to g2D.
     * 
     * @param g2D	Graphics2D from paintComponent()
     */
    private void paintMultiEnding(Graphics2D g2D) {
        // Player
        Color grey = new Color(80, 80, 80);
        g2D.setColor(grey);
        Font playerFont = new Font("trebuchet ms", Font.BOLD, 40);
        g2D.setFont(playerFont);
        g2D.drawString("Player " + player, 155, 160);
        
        // Title
        Font titleFont = new Font("trebuchet ms", Font.PLAIN, 38);
        g2D.setFont(titleFont);
        Color greyTranslucent = new Color(90, 90, 90, 200);
        g2D.setColor(greyTranslucent);
        g2D.drawString("won!", 190, 220);
    }
}
