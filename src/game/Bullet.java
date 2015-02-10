package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import constants.CONSTANTS;

public class Bullet {

			private Rectangle boundingBox;											//checks for collision
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
				
				boundingBox = new Rectangle(this.x,this.y, 2, 2 );
				hasHit = false;
				
			}
 			
			public void tick()	{	
				
				boundingBox.setBounds(x, y, 2, 2);
				if(inBounds())	{
				x += (dir)*CONSTANTS.VEL*2;
				}
				for (Enemy enemy : Game.enemies) {
					
					if(Intersects(enemy.getBoundingBox()))	{
						enemy.loseHP();	
						hasHit = true;
					}
						
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
			
			public boolean Intersects(Rectangle r) {
		        if(this.boundingBox.contains(r) || r.contains(this.boundingBox)) {
		            return true;
		        }
		        return false;
		    }
			
			
			public void finalize()	{			
					x = 0;
					y = 610;
					setShot(null);
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

			public Rectangle getShot() {
				return boundingBox;
			}

			public boolean hasHit() {
				return hasHit;
			}

			public void setHasHit(boolean hasHit) {
				this.hasHit = hasHit;
			}

			public void setShot(Rectangle shot) {
				this.boundingBox = shot;
			}

			
			

}
