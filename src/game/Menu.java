package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	
	public void mousePressed(MouseEvent e) {
	
		// temp variables for getting mouse position
		int mx = e.getX();
		int my = e.getY();
		
		
		if (Game.gameState == STATE.Menu) {
			// play button
			if (mouseOver(mx, my, 200, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(10, 300, ID.Player, handler)); // add player1
				handler.addObject(new Player(1090, 300, ID.Player2, handler)); // add player2
			
				AudioPlayer.getSound("click").play();
			}
			
			// help button
			if (mouseOver(mx, my, 200, 225, 200, 64)) {
				Game.gameState = STATE.Help;
				AudioPlayer.getSound("click").play();
			}
			
			// quit button
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				System.exit(0);
			}
		}
		
		// back button for help menu
		if (Game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				Game.gameState = STATE.Menu;
				AudioPlayer.getSound("click").play();
				return;
			}
		}

		// try again button for help menu
		if (Game.gameState == STATE.End) {
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				hud.resetGame();
				Game.gameState = STATE.Menu;
				handler.addObject(new MenuPuck(Game.WIDTH/2-25, Game.HEIGHT/2-48, ID.MenuPuck, handler));
				return;
			}
		}

	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	/**
	 * Checks whether the mouse is over a button by testing
	 * if the mouse is within the boundaries of the box. 
	 * @param mx
	 * @param my
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}
		}
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if (Game.gameState == STATE.Menu) {
			
			// fonts
			Font font = new Font("ariel", 1, 50);
			Font font2 = new Font("ariel", 1, 30);
			
			// menu font
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Pong", 105, 100);
			
			// play button and font
			g.setColor(Color.white);
			g.drawRect(200, 150, 200, 64);
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Play", 265, 190);
			
			// set help button and font
			g.setColor(Color.white);
			g.drawRect(200, 225, 200, 64);
			g.drawString("Help", 265, 268);
			
			
			// quit button and font
			g.setColor(Color.white);
			g.drawRect(200, 300, 200, 64);
			g.drawString("Quit", 265, 343);
		}
		else if (Game.gameState == STATE.Help) {
			
			// fonts
			Font font = new Font("ariel", 1, 50);
			Font font2 = new Font("ariel", 1, 20);
			Font font3 = new Font("ariel", 1, 30);
			
			// help menu font
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Help", 105, 100);
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Player One uses the W and S keys to move up and down", 60, 160);
			g.drawString("Player Two uses the UP and DOWN arrows to move up and down", 60, 210);
			g.drawString("Score 5 points to win the game!", 60, 260);
			
			g.setFont(font3);
			g.setColor(Color.white);
			g.drawRect(200, 300, 200, 64);
			g.drawString("Back", 265, 343);
			
		}
		else if (Game.gameState == STATE.End) {
			
			// fonts
			Font font = new Font("ariel", 1, 50);
			Font font2 = new Font("ariel", 1, 40);
			Font font3 = new Font("ariel", 1, 30);
			
			// help menu font
			g.setFont(font);
			g.setColor(Color.red.darker());
			g.drawString("Game, Set, Match!", 35, 100);
			
			g.setFont(font2);
			g.setColor(Color.white);
			
			// Display the winner
			if (HUD.playerOneScore > 4) {
				g.drawString("Player One wins", 90, 215);
			}
			else if (HUD.playerTwoScore > 4) {
				g.drawString("Player Two wins", 90, 215);
			}
			
			// Game over button to return to the main menu
			g.setFont(font3);
			g.setColor(Color.white);
			g.drawRect(150, 300, 200, 64);
			g.drawString("Menu", 210, 341);

		}
		
	}
}
