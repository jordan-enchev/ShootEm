package game;

import gfx.Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Display.Background;
import constants.CONSTANTS;

public class Player {
	private int health, ammo;
	private BufferedImage img;
	private int xPos, yPos, dir;
	private Rectangle boundingBox;
	private Random rand;
	
	private final int startPos = 400;
	private final int safeArea = 200;
	
	public static boolean hasShot;
	
	public  boolean goingUp;
    public  boolean goingDown;
    public  boolean goingLeft;
    public  boolean goingRight;
    
	
	public Player()	{
		health = 100;
		//ammo = 30;	
		img = Assets.player;
		xPos = startPos;
		yPos = 400;
		boundingBox = new Rectangle(100,150);
	}

	public void tick()	{

		
		boundingBox.setBounds(xPos, yPos, 100, 150);
	
			if(goingDown)	{
				
				if(getyPos() < CONSTANTS.GAME_HEIGHT - 150)	{		//player image heigth = 150
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
			
				if(getxPos()  >= startPos-safeArea-90 )	{
					setxPos(xPos - CONSTANTS.VEL);
				}
				dir = -1;		
			}
			if(goingRight)	{
	
				if(getxPos() <= startPos+safeArea)	{
					setxPos(xPos + CONSTANTS.VEL);
				}
				dir = 1;
				
			}
			
			
	}
		
	public boolean isMoving()	{
		return (goingDown || goingLeft || goingRight || goingUp);
	}

	
	public void changeAsset()	{
		
				Rectangle newCrop = Assets.getPlayerCrop();
				if	(goingRight)	{
					newCrop.y = 35;
					if(newCrop.x + 200 < 800)	{
						newCrop.x += 200;
					}
					else {
						newCrop.x = 55;
					}
					Assets.setPlayerCrop(newCrop);
				}
				
				else if(goingLeft){
					newCrop.y = 235;
					if(newCrop.x + 200 < 800)	{
						newCrop.x += 200;
					}
					else {
						newCrop.x = 55;
					}
					Assets.setPlayerCrop(newCrop);
					
				}
		
	}
	
	
	public void render(Graphics g)	{
		g.drawImage(Assets.player, this.xPos, this.yPos, null);	
	}


	
	public void loseHealth(int damage)
	{	
		if(ifHit())	{
		health -= damage;
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
	

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
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
	

}
