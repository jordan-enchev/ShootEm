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
	private int xPos, yPos, health;
	private Rectangle boundingBox;
	
	private Random rand;
	private int damage = 0;
	private int lastDir;
	
		
	public Enemy(int x, int y, int hp)	{
		this.xPos = x;
		this.yPos = y;
		this.health = hp;	
		this.img = Assets.enemy;
		this.boundingBox = new Rectangle(40, 60);
		this.rand = new Random();
	}
	
	
	
	public void tick()	{
		
		if(!isDead()){
			boundingBox.setBounds(xPos, yPos, 40, 60);
			
			for (Bullet bullet : Game.bullets) {
				
				if(Intersects(bullet.getShot()))	{
					loseHP();	
					bullet.setHasHit(true);
				}
			}
			
			
			if(!Intersects(Game.player.getBoundingBox ()))	{
				setDirection();		
				changeAsset();
			}
			else	{
				dealDMG();
				changeAsset();
			}
		
		}		
	}
	
	public void render(Graphics g) {
			
		g.drawImage(Assets.enemy, this.xPos, this.yPos, (lastDir)*40, 60, null);
	
		}
	
	public void changeAsset()	{
		
		Rectangle newCrop = Assets.getOtherCrop();
			
		if(!Intersects(Game.player.getBoundingBox()))	{
			newCrop.y = 10;
			if(newCrop.x + 40 < 360)	{
				newCrop.x += 40;
			}
			else {
				newCrop.x = 40;
			}
		}
//		else {
//			if(newCrop.x + 40 < 361)	{
//				newCrop.x += 40;
//			}
//			else {
//				newCrop.x = 320;
//			}
//			
//		}
			
			
			Assets.setOtherCrop(newCrop);
	}
	public void setDirection()	{
		setxDirection(Game.player.getxPos());
		setyDirection(Game.player.getyPos());
	}
	public void setxDirection(int x)	{
		
		if (getxPos() <= x)	{	//left direction
			setxPos(getxPos() + CONSTANTS.VEL/2);
			lastDir = -1;
		}
		if(getxPos() > x)	{	
				
			setxPos(getxPos() - CONSTANTS.VEL/2);	
			lastDir = 1;
		}
	}
	public void setyDirection(int y)	{
		
		if (getyPos() <= y + 60 )	{	//down
			
				setyPos(getyPos() + CONSTANTS.VEL/2);						
		}
		if (getyPos() > y + 75)	{
		
				setyPos(getyPos() - CONSTANTS.VEL/2);
		}
	}
	
	public boolean Intersects(Rectangle r) {
        if(this.boundingBox.contains(r) || r.contains(this.boundingBox)) {
            return true;
        }
        return false;
    }
				
	
	public void loseHP()	{
		
		int damage = rand.nextInt(5-0) +1;
		setHealth(getHealth() - damage);		
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



	public Rectangle getBoundingBox() {
		return boundingBox;
	}



	

}
