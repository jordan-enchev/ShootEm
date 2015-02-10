package Display;

import game.Game;
import gfx.Assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import constants.CONSTANTS;

public class Background {
	private int x, _x, y, _y;
	private BufferedImage image;
	public int dir;
	
	public Background()
	{
		image = Assets.background;
		x = 0;
		y = 0;
		_x = x + CONSTANTS.GAME_WIDTH;
		_y = y + CONSTANTS.GAME_HEIGHT;
		dir = 0;
				
	}
	
	public void draw(Graphics g)
	{
		if(g == null)
			return;
		
		g.drawImage(image, x , y, _x, _y, null);
	}
	
	
	public void moveLeft()	{
		if(_x > 1200)	{
			x -= CONSTANTS.VEL;
			_x -= CONSTANTS.VEL;
			dir = -1;
		}
				
	}
	public void moveRight()	{
		if(x < 0)	{
			x += CONSTANTS.VEL;
			_x += CONSTANTS.VEL;
			dir = 1;
		}
	}
	
	
	public void finalize()
	{
		image = null;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
