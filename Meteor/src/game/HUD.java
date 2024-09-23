package game;

import java.awt.Color;

import java.awt.Graphics;

public class HUD {
	public static float health = 100;
	private float color;
	private int score = 0;
	private int level = 1;
	
	public void tick() {
		health = Game.clamp((float)health, 0, 100);
		color = Game.clamp((float)health, 0, 255);
		color = health*2;
		++score;
	}
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75,(int)color,0));
		g.fillRect(15 , 15, (int)health * 2 , 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);	
		g.drawString("Score: " + score, 15, 65);
		g.drawString("Level: " + level, 15, 80);
	}
		
	public void score (int score) {
		this.score = score;
	}
	public int getScore() {
		return this.score;
	}
	public int getLevel() {
		return this.level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}
