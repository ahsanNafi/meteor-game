package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import game.Game.State;

public class Menu extends MouseAdapter{
	Game game;
	Handler handler;
	Random r;
	HUD hud;
	
	//Constructor 
	public Menu (Game game,Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		r = new Random();
		this.hud = hud;
	}
	
	//what happens when you click a box
	public void mousePressed(MouseEvent e) {
		
		//save mouse position
		int mx = e.getX();
		int my = e.getY();
		
		//start game
		if(Game.gameState == State.Menu) {
			if(mouseOver(mx,my,Game.WIDTH/2-110,160,200,64)) {
				Game.gameState = State.Select;	
				AudioPlayer.getSound("menu_sound1").play();
				return;
			}
		
		//game help 
			if (mouseOver(mx,my,Game.WIDTH/2-110,240,200,64) ) {
				AudioPlayer.getSound("menu_sound1").play();
				Game.gameState = State.Help;
		}
		
		//game closes
			if(mouseOver(mx,my,Game.WIDTH/2-110,320,200,64)) {
				System.exit(1);
			}
		}
		
		
		if(Game.gameState == State.Help) {
			
			if(mouseOver(mx,my,Game.WIDTH/2-110,380,200,64)) {
				AudioPlayer.getSound("menu_sound1").play();
				Game.gameState = State.Menu;
				return;
			}	
		}
		
		if( Game.gameState == State.Select) {
			//normal
			if(mouseOver(mx,my,Game.WIDTH/2-110,160,200,64)) {	
				AudioPlayer.getSound("menu_sound1").play();
				Game.gameState = State.Game;
				handler.addObject(new Player(Game.WIDTH/2-16,Game.HEIGHT/2-16,ID.Player,handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-20),r.nextInt(Game.HEIGHT-100),ID.BasicEnemy, handler));
				game.diff = 0;
			}
		
		// hard level
			if (mouseOver(mx,my,Game.WIDTH/2-110,240,200,64) ) {
				AudioPlayer.getSound("menu_sound1").play();
				Game.gameState = State.Game;
				handler.addObject(new Player(Game.WIDTH/2-16,Game.HEIGHT/2-16,ID.Player,handler));
				handler.clearEnemys();
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-17),r.nextInt(Game.HEIGHT-27), ID.SmartEnemy, handler));			
				game.diff = 1;
		}
		
		// super hard
			if(mouseOver(mx,my,Game.WIDTH/2-110,320,200,64)) {
				AudioPlayer.getSound("menu_sound1").play();
				Game.gameState = State.Game;
				handler.addObject(new Player(Game.WIDTH/2-16,Game.HEIGHT/2-16,ID.Player,handler));
				handler.clearEnemys();
				for (int i = 0 ; i < 3 ; ++i) {
					handler.addObject(new BossOne(Game.WIDTH/2-40,-100, ID.BossOne, handler));
				}
				game.diff = 2;
			}
			if(mouseOver(mx,my,20,25,70,30)) {
				AudioPlayer.getSound("menu_sound1").play();
				Game.gameState = State.Menu;
			}
		}
		
		
		
		if(Game.gameState == State.EndScreen) {
			 if(mouseOver(mx,my,Game.WIDTH/2-120,380,250,64)){
				AudioPlayer.getSound("menu_sound2").play();
				Game.gameState = State.Game;
				hud.setLevel(1);
				hud.score(0);
				Spawn.levelSpawner =2;
				Game.gameState = State.Menu;
			}	
		}
	}
	
	
	
	public void mouseReleased(MouseEvent e) {
		
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false; 
		}else return false;
	}
	
	public void tick() {
		
	}
	
	
	public void render(Graphics g) {
		if(Game.gameState == State.Menu) {
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1,30);
			g.setFont(fnt);
			g.setColor(Color.orange);
			g.drawString("Meteor", Game.WIDTH/2-90, 120);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-110,160,200,64);
			g.drawString("Play", Game.WIDTH/2-40, 205);
			
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-110,240,200,64);
			g.drawString("Help", Game.WIDTH/2-45, 285);
				
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-110,320,200,64);
			g.drawString("Quit", Game.WIDTH/2-45, 365);
			
		}else if (Game.gameState == State.Help) {
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1,20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", Game.WIDTH/2-60, 60);
			
			g.setFont(fnt2);
			g.drawString("Use 'W' 'A' 'S' 'D' to Control your player", 120, 140);
			
			g.setFont(fnt2);
			g.drawString("Press 'P' to Pause", 220, 190);
			g.setFont(fnt2);
			g.drawString("Avoid all the enemys", 205, 240);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-110,380,200,64);
			g.drawString("Back", Game.WIDTH/2-70, 430);
		
		}else if (Game.gameState == State.EndScreen) {
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1,20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over!", Game.WIDTH/2-130, 100);
			
			g.setFont(fnt2);
			g.drawString("You Scored "+ hud.getScore(), 250, 190);
		
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-120,380,250,64);
			g.drawString("Try Again", Game.WIDTH/2-110, 430);
			
		}else if(Game.gameState == State.Select) {
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1,30);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Difficulty", Game.WIDTH/2-115, 100);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-110,160,200,64);
			g.drawString("Normal", Game.WIDTH/2-60, 205);
			
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-110,240,200,64);
			g.drawString("Hard", Game.WIDTH/2-45, 285);
				
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2-110,320,200,64);
			g.drawString("Unplayable", Game.WIDTH/2-85, 365);
			
			g.setColor(Color.white);
			g.drawString("back", 20, 50);
		}
	
	}

}
