package game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;


/**
 * This game is a recreation of the classic Atari game Pong.  This class
 * handles numerous game objects that work together to render
 * the graphics and mathematical calculations.  
 * 
 * It also contains the Main for the program. 
 * 
 * @author apalm
 *
 */

public class Game extends Canvas implements Runnable {

	/**
	 * Class variables
	 */
	private static final long serialVersionUID = 951872290244265304L;
	public static final int WIDTH = 1040, HEIGHT = (WIDTH / 12 * 9);
	private boolean running = false;
	Thread thread;
	Handler handler;
	HUD hud;
	Random r;
	Spawner spawn;
	Menu menu;
	
	public static STATE gameState = STATE.Menu;
	
	/**
	 * Constructor
	 */
	public Game() {
		handler = new Handler();
		hud = new HUD();
		new Window(WIDTH, HEIGHT, "Pong", this);
		this.addKeyListener(new KeyInput(handler));
		r = new Random();
		menu = new Menu(this, handler, hud);
		this.addMouseListener(menu);
		spawn = new Spawner(handler);
	
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		if (gameState == STATE.Menu) {
			handler.addObject(new MenuPuck(Game.WIDTH/2-25, Game.HEIGHT/2-48, ID.MenuPuck, handler));
		}
		
	
		

	}

	/**
	 * Creates a thread of the game and runs it.
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	 * Joins the thread.  
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		thread.start();
	}
	
	/**
	 * Runs the game using magical code.   
	 */
	@Override
	public void run() {
		requestFocus(); // don't need to click on screen to control input

		// complex gaming code that magically runs game
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	/**
	 * Runs the Handler, HUD, Spawn objects.  
	 */
	public void tick() {
		handler.tick();
		
		if (gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
			
		}
		
		else if (gameState == STATE.Game) {
			handler.clearMenu();
			hud.tick();
			spawn.tick();
			
			if (HUD.playerOneScore > 4 || HUD.playerTwoScore > 4) {
				gameState = STATE.End;
				handler.reset();
			}
				
			
		}
		
		
		
	}
	
	/**
	 * Renders all the graphics on the main window.  
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black); // set game background color to black
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.white.darker()); // set dividing line to grey
		g.fillRect(WIDTH/2-25, 0, 25, HEIGHT);

		handler.render(g); // render handler
		
		if (gameState == STATE.Game) {
			hud.render(g); // render HUD
		}
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}

		g.dispose();
		bs.show();
	}
	
	/**
	 * Sets the limits that objects can travel in the GUI,
	 * thus ensuring they remain within the borders when neccessary.
	 * @param var
	 * @param min
	 * @param max
	 * @return
	 */
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		}
		else if (var <= min) {
			return var = min;
		}
		return var;
	}
	
	/**
	 * Main 
	 * @param args
	 */
	public static void main(String[] args) {
		new Game();
	}
}
