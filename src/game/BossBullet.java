package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class BossBullet extends GameObject {
	private Handler handler;
	Random r = new Random();
	public BossBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);


		this.handler = handler;

		speedx = (r.nextInt(5 - -5)+ -5);
		speedy = 7;//(r.nextInt(5 - -5)+ -5);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}


	public void tick() {
		x += speedx;
		y += speedy;
		
		
		if(x<= 0 || x >= Game.WIDTH - 16)speedx *=-1;
		if( y >= Game.HEIGHT ||   y <= 0	) handler.removeObject(this);
		handler.addObject(new Trail((int) x,(int) y,ID.Trail, Color.green, 16, 16, 0.04f , handler)); 
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.green);
		g.fillRect((int)x,(int)y,16,16);
	}

}
