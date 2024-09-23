package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class MenuParticle extends GameObject {
	private Handler handler;
	private Random r = new Random();
	
	
	private Color color;
	
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		speedx = (r.nextInt(5 - -5)+ -5)+3;
		speedy = (r.nextInt(5 - -5)+ -5)+3;
		
		color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
	}


	public void tick() {
		
		x += speedx;
		y += speedy;
		if(speedx == 0 && speedy == 0) {
			speedy = (r.nextInt(5 - -5)+ -5)+4;
			speedx = (r.nextInt(5 - -5)+ -5)+4;
		}
		
		if(y<= 0 || y >= Game.HEIGHT - 36)speedy *=-1;
		if(x<= 0 || x >= Game.WIDTH - 16)speedx *=-1;
		
		handler.addObject(new Trail((int) x,(int) y,ID.Trail, color, 18, 18, 0.03f , handler));
	}
	
	public void render(Graphics g) {
		
		g.setColor(color);
		g.fillRect((int)x,(int)y,18,18);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}

}

