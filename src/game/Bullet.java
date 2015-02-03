package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import constants.CONSTANTS;

public class Bullet {

			private Rectangle shot;
			private int x, y, vel;
			
			public Bullet()	{
				if(Game.player.isHeadTo())	{
					this.x = Game.player.getxPos() + 90;
					this.y = Game.player.getyPos() + 75; //shoots from center of 
				}
				else	{
					this.x = Game.player.getxPos();
					this.y = Game.player.getyPos() + 75; //shoots from center of 
				}
				shot = new Rectangle(this.x,this.y, 1, 1);
				if(Game.player.isHeadTo()){
					vel = -1;
				}
				else	{
					vel = 1;
				}
				
			}
			
			
			public void tick()	{		
				this.setX(getX() + (vel)*CONSTANTS.VEL*2);

			}
			public void render(Graphics g)	{
				g.setColor(Color.green);
				g.fillRect(getX(), getY(), 10, 10);
			}

			
			
			
			
			
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
