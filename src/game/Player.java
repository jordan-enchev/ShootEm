package game;

import gfx.Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import constants.CONSTANTS;

public class Player {
	private int health, ammo;
	private BufferedImage img;
	private int xPos, yPos;
	private Rectangle boundingBox;
	private Random rand;
	
	public  boolean goingUp;
    public  boolean goingDown;
    public  boolean goingLeft;
    public  boolean goingRight;
    
    public static boolean headedRight;
    public static boolean hasShot;
	
	
	public Player()	{
		this.health = 100;
		//this.ammo = 30;	
		this.img = Assets.player;
		xPos = 0;
		yPos = 400;
		this.boundingBox = new Rectangle(100,150);
	}

	public void tick()	{

		if(hasShot())	{
			Game.setBullets(new Bullet());
			setHasShot(false);
		}
		
		if(isDead()){
			Game.running = false;
			System.out.println("You died!!!");
		}

		boundingBox.setBounds(xPos, yPos, 100, 150);
			

		
			if(goingDown)	{
				
					if(getyPos() < CONSTANTS.GAME_HEIGHT - 180)	{
						setyPos(yPos + CONSTANTS.VEL);
					}
					else	{
						goingDown = false;
					}	
			}
			if(goingUp)	{
				if(getyPos() > 360)	{
					
						setyPos(yPos - CONSTANTS.VEL);
					}
				else	{
					goingUp = false;
				}
	
			}
			if(goingLeft)	{
			
				if(getxPos()  > 0 )	{
					setxPos(xPos - CONSTANTS.VEL);
				}
				else	{
					goingLeft = false;
				}	
				headedRight = true;
				
			}
			if(goingRight)	{
	
					if(getxPos() < CONSTANTS.GAME_WIDTH - 90)	{
						setxPos(xPos + CONSTANTS.VEL);
					}
					else	{
						goingRight = false;
					}	
					headedRight = false;
			}
			
			
		}
		
	public boolean isMoving()	{
		return (goingDown || goingLeft || goingRight || goingUp);
	}
	
	public void changeAsset()	{
		
				Rectangle newCrop = Assets.getCropPoint();
				if	(goingRight){
					newCrop.y = 35;
					if(newCrop.x + 200 < 800)	{
						newCrop.x += 200;
					}
				else {
					newCrop.x = 55;
				}
					Assets.setCropPoint(newCrop);
				}
				
				if(goingLeft){
					newCrop.y = 235;
					if(newCrop.x + 200 < 800)	{
						newCrop.x += 200;
					}
				else {
					newCrop.x = 55;
				}
					Assets.setCropPoint(newCrop);
					
				}
		
	}
	
	
	public void render(Graphics g)	{
		g.drawImage(Assets.player, this.xPos, this.yPos, null);	
	}


	
	public void loseHealth(int damage)
	{	
		if(ifHit())	{
		setHealth(getHealth()-damage);
		System.out.println("Damage taken " + damage);
		System.out.println("Health left " + getHealth());
		}
	}
	
	public boolean ifHit()	{
		rand = new Random();
		int hit = rand.nextInt(1-0+1);
		if(hit == 1)	{
			return true;
		}
		return false;
		
	}
	public boolean isDead()	{
		if (getHealth() <= 0){
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public boolean isHeadedRight() {		//returns true if headed right
		return headedRight;
	}


	public boolean hasShot() {
		return hasShot;
	}

	public void setHasShot(boolean hasShot) {
		Player.hasShot = hasShot;
	}


}
