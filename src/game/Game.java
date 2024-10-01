package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	// don't know why this is needed

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT =  WIDTH/12*9;;
	private Thread thread;
	
	private boolean running = false;
	public static boolean paused = false;
	public int diff = 0;
	
	
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	//game menu
	public enum State{
		Menu,
		Help,
		Game,
		Select,
		EndScreen
	};
	
	public static State gameState = State.Menu;
	
	//lets make the games objects.
	public Game () {
		handler = new Handler(); 
		hud = new HUD();
		spawner = new Spawn(handler, hud, this);
		r = new Random();
		menu = new Menu(this, handler, hud);
		
		
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);
		
		AudioPlayer.init();
		
		AudioPlayer.getMusic("music").loop();
		
		//makes the window
		new Window(WIDTH,HEIGHT, "Meteor", this );
		 if(gameState == State.Menu){
			for (int i = 0; i <= 15; ++i)
				handler.addObject(new MenuParticle(r.nextInt(WIDTH-30),r.nextInt(HEIGHT-50),ID.MenuPartcle,handler));
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true; 
			
	}
	
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
		
	//this loop helps with frame refresh, super important.
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 30.0;
		double ns = 1000000000/ amountOfTicks;
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
			if(System.currentTimeMillis()-timer > 1000) {
				timer += 1000;
				System.out.println("FPS: "+ frames);
				frames = 0;
			}	
		}
		stop();
	}
	
	//ticking away
	private void tick() {		
		if(gameState == State.Game) {
			
			if(!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				if(HUD.health <=0) {
					AudioPlayer.getSound("menu_sound1").play();
					HUD.health =100;
					gameState =State.EndScreen;
					handler.clearEnemys();
				for (int i = 0; i <= 10; ++i)
						handler.addObject(new MenuParticle(r.nextInt(WIDTH-30),r.nextInt(HEIGHT-50),ID.MenuPartcle,handler));			
				}		
			}
		}else if (gameState == State.Menu || gameState == State.EndScreen || gameState == State.Select || gameState == State.Help){
			menu.tick();
			handler.tick();
		}
	}
	
	
	/*rendering stuff
	*set your canvas size, position and color
	*/
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if(paused){
			
			g.drawString("PAUSED", WIDTH/2-25, 200);
		}
		if(gameState == State.Game) {			
			hud.render(g);	
		}else if (gameState == State.Menu || gameState == State.Help || gameState == State.EndScreen || gameState == State.Select){
			menu.render(g);
		}
		g.dispose();	
		bs.show();
	}
	
	
	//player restriction
	public static float clamp(float var, int min, int max) {
		if(var >= max)
			return var = (float)max;
		else if (var <= min)
			return var = (float)min;
		else 
			return var;
	}
						
	//Lonely main method			
	public static void main (String []args) {
			
			new Game();
	}
	
}