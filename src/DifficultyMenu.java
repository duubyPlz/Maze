import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The DifficultyMenu JPanel constructs the text and JButtons that appear on the
 * difficulty menu.
 */
public class DifficultyMenu extends JPanel {
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton backButton;

    private Color grey = new Color(190, 190, 190);
    private Color darkGrey = new Color(150, 150, 150);
    private Color orange = new Color(255, 128, 0);
    
    private GameState state;
    
	/**
	 * Initialises this panel and all buttons.
	 * 
	 * @param s		the current GameState, needed so this menu's buttons can
	 *            	update the state.
	 */
    public DifficultyMenu(GameState s) {
        this.setBackground(Color.white);
        this.state = s;
        this.setLayout(null);
        
        // Construct buttons
        easyButton = new JButton("easy");
        mediumButton = new JButton("medium");
        hardButton = new JButton("hard");
        backButton = new JButton("back");
        
        // Initialise button attributes:
        Font buttonFont = new Font("trebuchet ms", Font.BOLD, 25);
        
        easyButton.setOpaque(true);
        mediumButton.setOpaque(true);
        hardButton.setOpaque(true);
        backButton.setOpaque(true);
        
        //bounds
        easyButton.setBounds(190, 200, 90, 40);
        mediumButton.setBounds(170, 250, 130, 40);
        hardButton.setBounds(190, 300, 90, 40);
        backButton.setBounds(370, 400, 60, 30);
        
        //font
        easyButton.setFont(buttonFont);
        mediumButton.setFont(buttonFont);
        hardButton.setFont(buttonFont);
        backButton.setFont(new Font("trebuchet ms", Font.BOLD, 18));
        
        //background
        easyButton.setBackground(grey);
        mediumButton.setBackground(grey);
        hardButton.setBackground(grey);
        backButton.setBackground(grey);
        
        //border
        easyButton.setBorderPainted(false);
        mediumButton.setBorderPainted(false);
        hardButton.setBorderPainted(false);
        backButton.setBorderPainted(false);
        backButton.setMargin(new Insets(1, 1, 1, 1)); // no big border around text
        
        //mouse listener
        easyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Entered easy button
                easyButton.setBackground(orange);
            }
            
            public void mouseExited(MouseEvent e) {
                // Exited easy button
            	easyButton.setBackground(grey);
            }
            
            public void mousePressed(MouseEvent e) {
                // Pressed easy button
                state.setDifficulty(0);
                Game.setStateGame();
            }
        });
        mediumButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Entered medium button
                mediumButton.setBackground(orange);
            }
            
            public void mouseExited(MouseEvent e) {
                // Exited medium button
            	mediumButton.setBackground(grey);
            }
            
            public void mousePressed(MouseEvent e) {
                // Pressed medium button
                state.setDifficulty(1);
                Game.setStateGame();
            }
        });
        hardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Entered hard button
                hardButton.setBackground(orange);
            }
            
            public void mouseExited(MouseEvent e) {
                // Exited hard button
            	hardButton.setBackground(grey);
            }
            
            public void mousePressed(MouseEvent e) {
                // Pressed hard button
                state.setDifficulty(2);
                Game.setStateGame();
            }
        });
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Entered back button
                backButton.setBackground(darkGrey);
            }
            
            public void mouseExited(MouseEvent e) {
                // Exited back button
            	backButton.setBackground(grey);
            }
            
            public void mousePressed(MouseEvent e) {
            	// Pressed back button
            	Game.setStateMenu();
            }
        });
        
        // Add them to this panel
        this.add(easyButton);
        this.add(mediumButton);
        this.add(hardButton);
        this.add(backButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
        // Set title: difficulty
        Font menuTitleFont = new Font("trebuchet ms", Font.PLAIN, 50);
        g2D.setFont(menuTitleFont);
        Color greyTranslucent = new Color(70, 70, 70, 220);
        g2D.setColor(greyTranslucent);
        String title = "difficulty";
        g2D.drawString(title, 130, 150);
    }
}
