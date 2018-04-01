package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Heads-up display that tracks each players' score. 
 * @author apalm
 *
 */

public class HUD {
	
	
	public static int playerOneScore;
	public static int playerTwoScore;

	public void tick() {
		
	}

	public void render(Graphics g) {
		
		// create new font
		g.setColor(Color.white.darker());
		g.setFont(new Font("Arial", 1, 75));

		// draw scores onto the hud
		g.drawString(""+ playerOneScore, 200, 75);
		g.drawString(""+ playerTwoScore, 750, 75);

	}


	/**
	 * Increments playerOne's score.
	 */
	public void PlayerOneScored() {
		this.playerOneScore++;
	}

	/**
	 * Returns playerOne's score.
	 * @return
	 */
	public int getPlayerOneScore() {
		return this.playerOneScore;
	}
	
	/**
	 * Increments playerTwo's score.
	 */
	public void PlayerTwoScored() {
		this.playerTwoScore++;
	}

	/**
	 * Returns playerTwo's score.
	 * @return
	 */
	public int getPlayerTwoScore() {
		return this.playerTwoScore;
	}
	
	/**
	 * Resets both scores to 0.
	 */
	public void resetGame() {
		playerOneScore = 0;
		playerTwoScore = 0;
	}

}
