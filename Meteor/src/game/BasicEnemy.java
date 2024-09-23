package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class BasicEnemy extends GameObject {
	private Handler handler;
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		speedx = 5;
		speedy = 7;
	}


	public void tick() {
		
		x += speedx;
		y += speedy;
		if(y<= 0 || y >= Game.HEIGHT - 17) {
			
			speedy*=-1;
		}
		if(x<= 0 || x >= Game.WIDTH - 16) {
			
			speedx *=-1;
		}
		
		handler.addObject(new Trail((int) x,(int) y,ID.Trail, Color.red , 16, 16, 0.06f , handler));
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y,16,16);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}

}
