package Display;

import game.Game;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.w3c.dom.css.Rect;

import constants.CONSTANTS;

public class Camera {
	
	private int x,y;
	private static Rectangle camera;
	
	
	
	public Camera()	{
		
		this.x = 0;
		this.y = 0;

		camera = new Rectangle(x, y, CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT);
		
	}
	
	
	public void tick()	{
		this.x = Game.player.getxPos() - (CONSTANTS.WINDOW_WIDTH - 90);			//90 is player asset width
	}
	
		
	public static boolean inCam(Rectangle rect)	{
		if(camera.contains(rect))	{
			return true;
		}
		return false;
	}

}
