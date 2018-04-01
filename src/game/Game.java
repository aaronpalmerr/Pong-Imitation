package game;

import java.awt.*;  
import java.awt.image.BufferStrategy;
import java.util.Random;



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
	
	/**
	 * Constructor
	 */
	public Game() {
		handler = new Handler();
		hud = new HUD();
		new Window(WIDTH, HEIGHT, "Pong", this);
		this.addKeyListener(new KeyInput(handler));
		r = new Random();
		
		
		handler.addObject(new Puck(WIDTH/2-25, HEIGHT/2-48, ID.Puck, handler));
		handler.addObject(new Player(10, 300, ID.Player, handler));
		handler.addObject(new Player(1090, 300, ID.Player2, handler));
		
		
		
		
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
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	
	
	public void tick() {
		handler.tick();
		hud.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.white.darker());
		g.fillRect(WIDTH/2-25, 0, 25, HEIGHT);

		handler.render(g); // render handler
		
		hud.render(g); // render HUD

		g.dispose();
		bs.show();
	}
	
	/**
	 * Sets the limits that a player can travel to in the GUI,
	 * thus ensure they remain in the borders.
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
	
	
	public static void main(String[] args) {
		new Game();
	}
}
