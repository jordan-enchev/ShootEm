package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import constants.CONSTANTS;

public class Bullet {

			private Rectangle shot;
			private int x, y, vel;
			
			public Bullet()	{
				if(Game.player.isHeadedRight())	{
					this.x = Game.player.getxPos() + 90;
					this.y = Game.player.getyPos() + 75; 
				}
				else	{
					this.x = Game.player.getxPos();
					this.y = Game.player.getyPos() + 75; 
				}
				shot = new Rectangle(this.x,this.y, 1, 1);
				if(Game.player.isHeadedRight()){
					vel = -1;
				}
				else	{
					vel = 1;
				}
				
			}
			
			
			public void tick()	{		
				
				this.setX(getX() + (vel)*CONSTANTS.VEL*2);
				
				
				
				
				if(!inBounds()){
					try {
						this.finalize();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			public void render(Graphics g)	{
				g.setColor(Color.green);
				g.fillRect(getX(), getY(), 10, 10);
			}
			
			public boolean inBounds(){
				if(x>0 && x <= CONSTANTS.GAME_WIDTH && y > 0 && y <= CONSTANTS.GAME_HEIGHT){
					return true;
				}
				return false;
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
			
			
			
			public int getX() {
				return x;
			}

			public void setX(int x) {
				this.x = x;
			}

			public int getY() {
				return y;
			}

			public void setY(int y) {
				this.y = y;
			}

			public Rectangle getShot() {
				return shot;
			}

			public void setShot(Rectangle shot) {
				this.shot = shot;
			}
			
			
	
	
}
