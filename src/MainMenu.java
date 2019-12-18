import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The MainMenu JPanel constructs the text and JButtons that appear on the main
 * menu.
 */
public class MainMenu extends JPanel {
    private JButton singlePlayer1;
    private JButton singlePlayer2;
    private JButton doublePlayer;
    private JButton quitGame;
    
    private final Color grey = new Color(190, 190, 190);
    private final Color darkGrey = new Color(150, 150, 150);
    private final Color green = new Color(181, 230, 29);
    private final Color blue = new Color(119, 215, 255);
    private final Color red = new Color(255, 117, 117);
    
    private GameState state;
    
	/**
	 * Initialises this panel and all buttons.
	 * 
	 * @param s		 the current GameState, needed so this menu's buttons can
	 *            	 update the state.
	 */
    public MainMenu(GameState s) {
        this.setBackground(Color.white);
        this.state = s;
        this.setLayout(null);
        
        // Construct buttons
        singlePlayer1 = new JButton("Casual");
        singlePlayer2 = new JButton("Timed");
        doublePlayer = new JButton("Multiplayer");
        quitGame = new JButton("Quit");
        
        // Initialise button attributes
        Font buttonFont = new Font("trebuchet ms", Font.BOLD, 25);
        
        singlePlayer1.setOpaque(true);
        singlePlayer2.setOpaque(true);
        doublePlayer.setOpaque(true);
        quitGame.setOpaque(true);
        
        //bounds
        singlePlayer1.setBounds(170, 150, 120, 40);
        singlePlayer2.setBounds(170, 200, 120, 40);
        doublePlayer.setBounds(145, 250, 175, 40);
        quitGame.setBounds(180, 300, 100, 40);
        
        //font
        singlePlayer1.setFont(buttonFont);
        singlePlayer2.setFont(buttonFont);
        doublePlayer.setFont(buttonFont);
        quitGame.setFont(buttonFont);
        
        //background
        singlePlayer1.setBackground(grey);
        singlePlayer2.setBackground(grey);
        doublePlayer.setBackground(grey);
        quitGame.setBackground(grey);
        
        //border
        singlePlayer1.setBorderPainted(false);
        singlePlayer2.setBorderPainted(false);
        doublePlayer.setBorderPainted(false);
        quitGame.setBorderPainted(false);
        
        //mouse listener
        singlePlayer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Entered casual button
                singlePlayer1.setBackground(green);
            }
            
            public void mouseExited(MouseEvent e) {
                // Exited casual button
                singlePlayer1.setBackground(grey);
            }
            
            public void mousePressed(MouseEvent e) {
                // Pressed Single Play button
                state.setMode(0);
                Game.setStateDifficulty();
            }
        });
        singlePlayer2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Entered timed button
                singlePlayer2.setBackground(blue);
            }
            
            public void mouseExited(MouseEvent e) {
                // Exited timed button
                singlePlayer2.setBackground(grey);
            }
            
            public void mousePressed(MouseEvent e) {
                // Pressed Single play with timer button
                state.setMode(1);
                Game.setStateDifficulty();
            }
        });
        doublePlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Entered multiplayer button
                doublePlayer.setBackground(red);
            }
            
            public void mouseExited(MouseEvent e) {
                // Exited multiplayer button
                doublePlayer.setBackground(grey);
            }
            
            public void mousePressed(MouseEvent e) {
                // Pressed Double Player button
                state.setMode(2);
                Game.setStateDifficulty();
            }
        });
        quitGame.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
                quitGame.setBackground(darkGrey);
            }
        	public void mouseExited(MouseEvent e) {
        		quitGame.setBackground(grey);
        	}
        	
        	public void mousePressed(MouseEvent e) {
        		System.exit(0);
        	}
        });

        // Add them to this panel
        this.add(singlePlayer1);
        this.add(singlePlayer2);
        this.add(doublePlayer);
        this.add(quitGame);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Remember!
        Graphics2D g2D = (Graphics2D) g;
        
        // Title
        Font menuTitleFont = new Font("cambria", Font.BOLD, 48);
        g2D.setFont(menuTitleFont);
        Color greyTranslucent = new Color(70, 70, 70, 200);
        g2D.setColor(greyTranslucent);
        String title = "THE aMAZEing";
        g2D.drawString(title, 70, 70);
        g2D.setFont(new Font("cambria", Font.BOLD, 40));
        g2D.drawString("GAME", 175, 115);
        
        // Instructions image        
        Image instructions = (new ImageIcon("src/instructions.png").getImage());
        g2D.drawImage(instructions, 70, 360, null);
        
    }
}
