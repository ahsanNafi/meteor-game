package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject {
	private Handler handler;
	private Random r;
	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		r = new Random();
		this.handler = handler;
		speedx = 5;
		speedy = 10;
	}


	public void tick() {
		
		x += speedx;
		y += speedy;


		if( y >= Game.HEIGHT - 40) {
			
			speedy *= -1;
	
		}else if(y< 0) speedy = r.nextInt(10);
		if(x >= Game.WIDTH - 36) {
			speedx = r.nextInt(10);
			speedx*= -1;
		}else if (x <=0) speedx = r.nextInt(10);
		handler.addObject(new Trail((int) x,(int) y,ID.Trail, Color.yellow , 16, 16, 0.06f , handler));
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillRect((int)x,(int)y,16,16);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}

}
