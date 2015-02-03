package game;

import gfx.Assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import constants.CONSTANTS;

public class Enemy {

	private BufferedImage img;
	private int xPos, yPos, health;
	private Rectangle boundingBox;
	private boolean goingLeft, goingRight;
		
	public Enemy(int x, int y, int hp)	{
		this.xPos = x;
		this.yPos = y;
		this.health = hp;	
		this.img = Assets.enemy;
		this.boundingBox = new Rectangle(40, 60);
	}
	
	
	
	public void tick()	{
		
		boundingBox.setBounds(xPos, yPos, 40, 60);
		
		if(!Intersects(Game.player.getBoundingBox()))	{
			setDirection();		
			changeAsset();
		}
		else	{
			
		}
		
		if (isDead()) {
			
		}
	}
	
	public void render(Graphics g) {
			
			if(goingRight){
				g.drawImage(Assets.enemy, this.xPos, this.yPos, -40, 60, null);
			}
			else if(goingLeft)	{
				g.drawImage(Assets.enemy, this.xPos, this.yPos, 40, 60, null);
			}
			else g.drawImage(Assets.enemy, this.xPos, this.yPos, null);
			
		}
	
	public void changeAsset()	{
		
		Rectangle newCrop = Assets.getOtherCrop();
			
			newCrop.y = 10;
			if(newCrop.x + 40 < 360)	{
				newCrop.x += 40;
			}
			else {
				newCrop.x = 40;
			}
			
			
			Assets.setOtherCrop(newCrop);
	}
	public void setDirection()	{
		setxDirection(Game.player.getxPos());
//		setyDirection(Game.player.getyPos());
	}
	public void setxDirection(int x)	{
		
		if (getxPos() <= x)	{	//right direction
			setxPos(getxPos() + CONSTANTS.VEL/2);
			goingRight = true;
			goingLeft = false;
		}
		if(getxPos()> x)	{	
				
			setxPos(getxPos() - CONSTANTS.VEL/2);	
			goingLeft = true;
			goingRight = false;
		}
	}
	public void setyDirection(int y)	{
		
		if (getyPos() <= y)	{	//down
			
				setyPos(getyPos() + CONSTANTS.VEL/2);						
		}
		if (getyPos() > y)	{
		
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
		setHealth(health - 5);
	}
	

	
	public boolean isDead()	{
		if(getHealth() == 0)	{
			return true;
		}
		return false;	
	}

	
	

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

	
	

}
