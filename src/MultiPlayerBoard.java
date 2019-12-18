import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * The MultiplayerBoard class builds onto Board, by constructing a new Player
 * and representing it on the board.
 */
public class MultiPlayerBoard extends Board {
    private Player player2;
    
    public MultiPlayerBoard(int boardSize) {
        super(boardSize);
        player2 = new Player(boardSize-1, 1);
        super.setPlayer2(player2);
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.mazeEndColor = Color.gray;
        super.paintComponent(g); // Calls Board's paintComponent()
        
        Graphics2D g2D = (Graphics2D) g;
        super.mazeEndColor = Color.green;
        super.drawEndGoal(g2D, true);
        
        Color yellow = new Color(244, 233, 17, 220);
        super.drawCharacter(g2D, player2, yellow);
    }
}
