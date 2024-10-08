package game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class SmartEnemy extends GameObject{
	private Handler handler;
	private GameObject player;

	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for (int i = 0 ; i < handler.object.size(); i++)
			if(handler.object.get(i).getID() == ID.Player) {
				player = handler.object.get(i);
			}
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}

	public void tick() {
		x += speedx;
		y += speedy;

		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;

		float distance =(float) Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()) );
		
			speedx = (float) ((-1.0/distance) * diffX*3);
			speedy = (float) ((-1.0/distance) * diffY*3);
			
		handler.addObject(new Trail( (int)x,(int) y,ID.Trail, Color.magenta , 16, 16, 0.02f , handler));
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.magenta);
		g.fillRect((int)x,(int)y,16,16);
	}




}
