package gfx;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 90, height = 150 , otherWidth = 30, otherHeight = 60;
    private static Rectangle cropPoint = new Rectangle(55, 35, 1, 1), otherCrop = new Rectangle(40, 10, 1, 1);
    static SpriteSheet sheet, otherSheet;

    public static BufferedImage player, enemy, background, ground;
    //Loads every resource needed for the game
    public static void init() {
        
    	sheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
    	otherSheet = new SpriteSheet(ImageLoader.loadImage("/textures/zombie.gif"));
        background = ImageLoader.loadImage("/textures/background.png");

        player = sheet.crop(cropPoint.x, cropPoint.y, width, height);
        enemy = otherSheet.crop(otherCrop.x, otherCrop.y, otherWidth, otherHeight);
   
    }
	public static void setCropPoint(Rectangle newCrop) {
		Assets.cropPoint = newCrop;
		player = sheet.crop(cropPoint.x, cropPoint.y, width, height);
	}
	public static Rectangle getCropPoint() {
		return cropPoint;
	}
	public static Rectangle getOtherCrop() {
		return otherCrop;
	}
	public static void setOtherCrop(Rectangle newCrop) {
		Assets.otherCrop = newCrop;
		enemy = otherSheet.crop(otherCrop.x, otherCrop.y, otherWidth, otherHeight);
	}
	
    
}
