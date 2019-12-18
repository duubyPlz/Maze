import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * TimerPanel constructs a timer and uses it to increment a counter. This
 * counter is displayed via a JLabel, which represents seconds which have passed
 * since the starting of a game.
 */
public class TimerPanel extends JPanel {
    private JLabel label;
    private Timer timer;
    
    private TimeClass tc;

    public TimerPanel() {
        // Initialise panel
    	this.setPreferredSize(new Dimension(480, 35));
        Color darkDarkGrey = new Color(26, 26, 26);
        this.setBackground(darkDarkGrey);
        
        // Construct label & timer
        label = new JLabel();
        label.setForeground(Color.white);
        label.setFont(new Font("Trebuchet ms", Font.BOLD, 30));
        add(label);
        
        int count = 0;
        label.setText("" + count);
        
        tc = new TimeClass(count);
        timer = new Timer(1000, tc);
        timer.start();
    }

    /**
     * Gets the current time. (Also represents the time taken after a game ends).
     * @return	the current value of timeCounter
     */
    public int getTimeCounter() {
        return tc.timeCounter;
    }
    
	/**
	 * This action listener detects events given by the Timer and increments a
	 * counter at each event.
	 */
    private class TimeClass implements ActionListener {
        private int timeCounter;
        
        public TimeClass(int counter) {
            timeCounter = counter;
        }

        @Override
        public void actionPerformed(ActionEvent tc) {
            timeCounter++;
            label.setText("" + timeCounter);
        }
    }
}
