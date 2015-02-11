package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import constants.CONSTANTS;

public class Bullet {

			private Rectangle collisionBox;											//checks for collision
			private int x, y, dir;											//	bullet current position and direction (1 - right, -1 - left)
			private boolean hasHit;											//	returnrs true on impact
			BufferedImage img;												
			
			public Bullet(int x, int y, int dir)	{
				
				if(dir < 0)	{
					this.x = x + 90;							 //initial bullet position with player x,y with image size corrections;
					this.y = y + 75; 
					this.dir = dir;
				}
				if(dir > 0)	{
					this.x = x;
					this.y = y + 75; 
					this.dir = dir;
				}
				
				collisionBox = new Rectangle(this.x,this.y, 2, 2 );
				hasHit = false;
				
			}
 			
			public void tick()	{	
				
				collisionBox.setBounds(x, y, 2, 2);
				
				if(inBounds())	{
				x += (dir)*CONSTANTS.VEL*2;
				}
			}
			
			public void render(Graphics g)	{
				g.setColor(Color.green);
				g.fillRect(x, y, 10, 10);
			}
			
			
			public boolean inBounds(){
				if(x >= 0 && x <= CONSTANTS.GAME_WIDTH && y >= 0 && y <= CONSTANTS.GAME_HEIGHT){
					return true;
				}
				return false;
			}
			
			public boolean intersects(Rectangle r) {
		        if(collisionBox.contains(r) || r.contains(collisionBox)) {
		            return true;
		        }
		        return false;
		    }
			
			
			public void finalize()	{			
					x = 0;
					y = 610;
					collisionBox = null;
					}


			
		//	
		//	
		//	
		//	
		//			Getters and setters:
		//	
		//	
		//	
		//	
			
			
			
			public int getxPos() {
				return x;
			}

			public int getyPos() {
				return y;
			}
			
			public void setxPos(int x) {
				this.x = x;
			}

			public void setyPos(int y) {
				this.y = y;
			}

			public Rectangle getCollisionBox() {
				return collisionBox;
			}

			public boolean hasHit() {
				return hasHit;
			}

			public void hasHit(boolean hasHit) {
				this.hasHit = hasHit;
			}
}
