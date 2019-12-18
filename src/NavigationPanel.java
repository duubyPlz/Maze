import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * NavigationPanel hosts buttons that might be needed when in game to move to
 * another state (e.g. back to menu).
 */
public class NavigationPanel extends JPanel {
	private JButton menuButton;
	
	private final Color darkGreyBack = new Color(26, 26, 26);
	private final Color lightGreyBack = new Color(40, 40, 40);
	private final Color darkGreyFore = new Color(100, 100, 100);
	private final Color lightGreyFore = new Color(150, 150, 150);
	
    public NavigationPanel() {
        // Initialise panel
    	this.setPreferredSize(new Dimension(480, 50));
    	Color darkDarkGrey = new Color(26, 26, 26);
        this.setBackground(darkDarkGrey);
        this.setLayout(null);
        
        // Construct button
        menuButton = new JButton("back to menu");
        
        // Initialise button attributes
        menuButton.setOpaque(true);
        menuButton.setBounds(310, 10, 130, 30);
        menuButton.setFont(new Font("trebuchet ms", Font.BOLD, 18));
        menuButton.setBackground(darkGreyBack);
        menuButton.setForeground(darkGreyFore);
        menuButton.setBorderPainted(false);
        menuButton.setMargin(new Insets(1, 1, 1, 1)); // no big border around text
        menuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Entered menu button            	
            	menuButton.setBackground(lightGreyBack);
            	menuButton.setForeground(lightGreyFore);
            }
            
            public void mouseExited(MouseEvent e) {
                // Exited menu button
                menuButton.setBackground(darkGreyBack);
                menuButton.setForeground(darkGreyFore);
            }
            
            public void mousePressed(MouseEvent e) {
            	// Pressed menu button
            	Game.setStateMenu();
            }
        });
        
        // Add button to panel
        this.add(menuButton);
    }
}
