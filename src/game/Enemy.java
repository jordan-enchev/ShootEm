package game;

import gfx.Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import constants.CONSTANTS;

public class Enemy {

	private BufferedImage img;
	private int xPos, yPos, dir, health;
	private Rectangle collisionBox;
	
	private Random rand;
	private int damage = 0;
	private boolean isHit = false;
	
		
	public Enemy(int x, int y, int hp)	{
		xPos = x;
		yPos = y;
		dir = 0;
		health = hp;	
		img = Assets.enemy;
		collisionBox = new Rectangle(100, 100);
		rand = new Random();
	}
	
	
	
	public void tick()	{
		if(!isDead()){
			collisionBox.setBounds(xPos, yPos, 100, 100);
		}		
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.enemy, xPos, yPos, (dir)*100, 100, null);
	}
	
	public void changeAsset()	{
		
		Rectangle newCrop = Assets.getEnemyCrop();
			
				if(gotHit()) {
					newCrop.x = 370;
					if(newCrop.x + 40 <= 410)	{
						newCrop.x += 40;
						gotHit(false);
				}
				else {
					newCrop.x = 370;
				}
				Assets.setEnemyCrop(newCrop);
			} 
			
			else if(!intersects(Game.player.getCollisionBox()))	{
				
				newCrop.y = 10;
				if(newCrop.x + 40 < 360)	{
					newCrop.x += 40;
				}
				else {
					newCrop.x = 40;
				}
				Assets.setEnemyCrop(newCrop);	
			}
		
		
		

	}
	public void setDirection()	{
		setxDirection(Game.player.getxPos());
		setyDirection(Game.player.getyPos());
	}
	public void setxDirection(int x)	{
		
		if (getxPos() < x)	{	//left direction
			setxPos(getxPos() + CONSTANTS.VEL/3);
			dir = -1;
		}
		else if(getxPos() > x)	{	
				
			setxPos(getxPos() - CONSTANTS.VEL/3);	
			dir = 1;
		}
		else {
			
		}
	}
	public void setyDirection(int y)	{
		
		if (getyPos() <= y + 40 )	{	//down
			
				setyPos(getyPos() + CONSTANTS.VEL/2);						
		}
		if (getyPos() > y + 40)	{
		
				setyPos(getyPos() - CONSTANTS.VEL/2);
		}
	}
	
	public boolean intersects(Rectangle r) {
        if(collisionBox.contains(r) || r.contains(collisionBox)) {
            return true;
        }
        return false;
    }

	public void loseHP()	{
		
		int damage = rand.nextInt(5-0) +1;
		setHealth(getHealth() - damage);		
		gotHit(true);
	}
	
	public void dealDMG(){
		damage = rand.nextInt((5-1+1) + 1);
		Game.player.loseHealth(damage);
	}
	
	public boolean isDead()	{
		if(getHealth() <= 0)	{
 			return true;
		}
		return false;	
	}
	public void finalize()	{
		setxPos(0);
		setyPos(610);
		setCollisionBox(null);
		img = null;
	}
	
	//	
	//	
	//	
	//	
	//	Getters and setters:
	//	
	//	
	//	
	//	

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}

	public boolean gotHit() {
		return isHit;
	}

	public void gotHit(boolean isHit) {
		this.isHit = isHit;
	}

}
