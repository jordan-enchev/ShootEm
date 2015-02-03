package game;

import gfx.Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import constants.CONSTANTS;

public class Player {
	private int health, ammo;
	private BufferedImage img;
	private int xPos, yPos;
	private Rectangle boundingBox;
	
	public static boolean goingUp;
    public static boolean goingDown;
    public static boolean goingLeft;
    public static boolean goingRight;
	
	
	public Player()	{
		this.health = 100;
		this.ammo = 30;	
		this.img = Assets.player;
		xPos = 0;
		yPos = 400;
		this.boundingBox = new Rectangle(100,150);
	}

	public void tick()	{
		
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
				if(getyPos() > 350)	{
					
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
				
			}
			if(goingRight)	{
	
					if(getxPos() < CONSTANTS.GAME_WIDTH - 90)	{
						setxPos(xPos + CONSTANTS.VEL);
					}
					else	{
						goingRight = false;
					}	
			}
			
			
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
