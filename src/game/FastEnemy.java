package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class FastEnemy extends GameObject {
	private Handler handler;
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		speedx = 3;
		speedy = 10;
	}


	public void tick() {
		
		x += speedx;
		y += speedy;
		if(y<= 0 || y >= Game.HEIGHT - 36)speedy *=-1;
		if(x<= 0 || x >= Game.WIDTH - 16)speedx *=-1;
		
		handler.addObject(new Trail((int) x,(int) y,ID.Trail, Color.cyan , 16, 16, 0.06f , handler));
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.cyan);
		g.fillRect((int)x,(int)y,16,16);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}

}
