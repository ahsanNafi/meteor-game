package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.Game.State;

public class KeyInput extends KeyAdapter{
	
	Game game;
	
	private Handler handler;
	private boolean[] keyDown = {false,false,false,false};
	
	public KeyInput(Handler handler, Game game ) {
		this.handler = handler;
		this.game = game;
	}
	
	public void keyPressed (KeyEvent e ) {
		int key = e.getKeyCode();
		//you press, the player moves

		for (int i = 0; i< handler.object.size(); ++i) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID()== ID.Player) {
				
				// input for player1
				if (key == KeyEvent.VK_W) {tempObject.setSpeedY(-7); keyDown[0] = true;}
				if (key == KeyEvent.VK_S) {tempObject.setSpeedY(7); keyDown[1] = true;}
				if (key == KeyEvent.VK_A) {tempObject.setSpeedX(-7); keyDown[2] = true;}
				if (key == KeyEvent.VK_D) {tempObject.setSpeedX(7); keyDown[3] = true;}
			}

		}
		if (key == KeyEvent.VK_P && Game.gameState == State.Game) {
			if(Game.paused) Game.paused = false;
			else Game.paused = true;
		}
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i< handler.object.size(); ++i) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID()== ID.Player) {
				if (key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setSpeedY(0);
				if (key == KeyEvent.VK_S) keyDown[1] = false;//tempObject.setSpeedY(0);
				if (key == KeyEvent.VK_A) keyDown[2] = false;//tempObject.setSpeedX(0);
				if (key == KeyEvent.VK_D) keyDown[3] = false;//tempObject.setSpeedX(0);
				
				
				if(!keyDown[0] && !keyDown[1]) tempObject.setSpeedY(0);
				if(!keyDown[2] && !keyDown[3]) tempObject.setSpeedX(0);
			}

			
		}
	}

}
