package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class BossOne extends GameObject{
	private Handler handler;
	private Random r = new Random();
	
	public int i = 0;
	private int timer = 60;
	private int timer2 = 50;
	public BossOne(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		
		speedx = 0;
		speedy = 2;

	}


	public void tick() {
		
		x += speedx;
		y += speedy;
		
		
		if(timer <= 0) speedy = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0) {
			
			if (speedx ==0) speedx =4; 
			
			if(speedx > 0)
				speedx += 0.05f;
			else if (speedx < 0 )
				speedx -= 0.05f;
			
			speedx = Game.clamp(speedx, -10, 10);
			
			int spawn = r.nextInt(10);
			if(spawn == 0) 
				handler.addObject(new BossBullet((int)x+48,(int)y+48, ID.BasicEnemy, handler));			
		}

		if(x<= 0 || x >= Game.WIDTH - 96)speedx *=-1;

		//handler.addObject(new Trail((int) x,(int) y,ID.Trail, Color.WHITE , 16, 16, 0.06f , handler));
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.blue);
		g.fillRect((int)x,(int)y,96,96);

	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 96, 96);
	}

}