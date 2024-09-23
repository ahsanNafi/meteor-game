package game;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r;
	private Game game;
	private int scoreKeep = 0;
	public static int levelSpawner= 2;
	
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		r = new Random();
	}
		
	public void tick() {
		scoreKeep++;
		if(scoreKeep >= 100) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel()+1);
			if(game.diff == 0) {
				if( levelSpawner<= 4)
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-23),r.nextInt(Game.HEIGHT-100), ID.BasicEnemy, handler));
				if(levelSpawner > 4 && levelSpawner < 9)
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-17),r.nextInt(Game.HEIGHT-21), ID.FastEnemy, handler));
				if(levelSpawner > 9 && levelSpawner < 13)
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-17),r.nextInt(Game.HEIGHT-100), ID.BasicEnemy, handler));
				if(levelSpawner >= 13 && levelSpawner <=15) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-17),r.nextInt(Game.HEIGHT-27), ID.HardEnemy, handler));
					levelSpawner = 1 ;
				}
			}
			if (game.diff == 1) {
				if (levelSpawner == 3) {
					for(int j = 0 ; j < 5; j++)
						
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-30),r.nextInt(Game.HEIGHT-60), ID.HardEnemy, handler));
				}
				if (levelSpawner >3 && levelSpawner <= 8)
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-17),r.nextInt(Game.HEIGHT-21), ID.FastEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-23),r.nextInt(Game.HEIGHT-100), ID.BasicEnemy, handler));
				if (levelSpawner == 12) {
					handler.clearEnemys();
					handler.addObject(new BossOne(Game.WIDTH/2-40,-100, ID.BossOne, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-23),r.nextInt(Game.HEIGHT-100), ID.BasicEnemy, handler));
					
				}
				if (levelSpawner == 15) levelSpawner = 1;
			}
			
			if (game.diff == 2) {
				if (levelSpawner == 4)
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-17),r.nextInt(Game.HEIGHT-27), ID.SmartEnemy, handler));
				if (levelSpawner == 7)
					for (int j = 0;  j < 4 ; ++j)
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-17),r.nextInt(Game.HEIGHT-21), ID.HardEnemy, handler));
				if(levelSpawner == 15)
					levelSpawner = 1;
			}
			
			++levelSpawner;
		}
		
	}
}
