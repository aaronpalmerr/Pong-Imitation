package game;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Maintains dimensions for GUI.  
 * @author apalm
 *
 */

public class Window extends Canvas {

	/**
	 * Class instance variables.
	 */
	private static final long serialVersionUID = -4369730830015653927L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		// Set GUI size
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		// Other frame settings
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		// Add game to frame.  
		frame.add(game);
		frame.setVisible(true);
		game.start();


	}
	
}
