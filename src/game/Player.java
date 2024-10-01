package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 32, 32);
	}
	
	
	//position of your object
	public void tick() {
		
		x += speedx;
		y += speedy;
		
		x = Game.clamp(x, 3, Game.WIDTH-40);
		y = Game.clamp(y, 1, Game.HEIGHT-62);
		
		collision();
	}
	
	
	private void collision() {
		for(int i =0 ; i < handler.object.size(); ++i) {
			
			GameObject tempObject = handler.object.get(i);;
			if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.SmartEnemy || tempObject.getID() == ID.BossOne) {
				if(getBounds().intersects(tempObject.getBounds())){

					HUD.health-=2;
				}
			}
		}
		
	}
	
	
	//render your player object
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,(int) y, 32, 32);
	}		

}
