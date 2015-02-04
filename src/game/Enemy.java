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
	private boolean isHit = false;
	
		
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
			}
		}		
	}
	
	public void render(Graphics g) {
			
		g.drawImage(Assets.enemy, this.xPos, this.yPos, (lastDir)*40, 60, null);
	
		}
	
	public void changeAsset()	{
		
		Rectangle newCrop = Assets.getOtherCrop();
			
				if(isHit()) {
				newCrop.x = 370;
				if(newCrop.x + 40 <= 410)	{
					newCrop.x += 40;
					setIsHit(false);
				}
				else {
					newCrop.x = 370;
				}
				Assets.setOtherCrop(newCrop);
			} 
			
				else if(!Intersects(Game.player.getBoundingBox()))	{
			newCrop.y = 10;
			if(newCrop.x + 40 < 360)	{
				newCrop.x += 40;
			}
			else {
				newCrop.x = 40;
			}
			Assets.setOtherCrop(newCrop);	
		}
		
		
		

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
		setIsHit(true);
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
		this.setxPos(0);
		this.setyPos(610);
		this.boundingBox.setBounds(this.getxPos(), this.getyPos(), 1, 1);
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



	public boolean isHit() {
		return isHit;
	}



	public void setIsHit(boolean isHit) {
		this.isHit = isHit;
	}



	

}
