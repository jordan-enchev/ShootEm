package game;

import gfx.Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import constants.CONSTANTS;

public class Enemy {

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
		collisionBox = new Rectangle(80, 100);
		rand = new Random();
	}
	
	
	
	public void tick()	{
		if(!isDead()){
			collisionBox.setBounds(xPos, yPos, 80, 100);
		}
		if(intersects(Game.getPlayer().getCollisionBox ()))	{
			attack();
		}
		else	{
			setDirection(Game.getPlayer().getxPos(), Game.getPlayer().getyPos());		
			changeAsset();
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.enemy, xPos, yPos, (dir)*80, 100, null);
	}
	
	public void changeAsset()	{
		Rectangle newCrop = Assets.getEnemyCrop();
		if(isHit)	{
			newCrop.x = 240;
			if(newCrop.x + 130 <= 400)	{
				newCrop.x += 130;
			}
			else {
				newCrop.x = 240;
			}
		} 

		else if(!intersects(Game.getPlayer().getCollisionBox()))	{
			
			newCrop.y = 10;
			
			if(newCrop.x + 40 < 360)	{
				newCrop.x += 40;
			}
			else {
				newCrop.x = 40;
			}
		}
		Assets.setEnemyCrop(newCrop);
	}
	
	public void setDirection(int x, int y)	{
		setxDirection(x);
		setyDirection(y);
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

	public void takeDMG()	{
		
		int damage = rand.nextInt(5-0) +1;
		setHealth(getHealth() - damage);		
		isHit(true);
		changeAsset();
	}
	
	public void attack(){
		damage = rand.nextInt((2-1+1) + 1);
		Game.getPlayer().loseHealth(damage);
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

	public boolean isHit() {
		return isHit;
	}

	public void isHit(boolean isHit) {
		this.isHit = isHit;
	}

}
