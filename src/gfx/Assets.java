package gfx;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int playerImgWidth = 90, playerImgHeight = 150 , enemyImgWidth = 30, enemyImgHeight = 60;
    private static Rectangle playerCrop = new Rectangle(55, 35, playerImgWidth, playerImgHeight), enemyCrop = new Rectangle(40, 10, enemyImgWidth, enemyImgHeight);
    static SpriteSheet playerSheet, enemySheet;

    public static BufferedImage player, enemy, background;
    //Loads every resource needed for the game
    public static void init() {
        
    	playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
    	enemySheet = new SpriteSheet(ImageLoader.loadImage("/textures/zombie.gif"));
        background = ImageLoader.loadImage("/textures/background.png");

        player = playerSheet.crop(playerCrop.x, playerCrop.y, playerImgWidth, playerImgHeight);
        enemy = enemySheet.crop(enemyCrop.x, enemyCrop.y, enemyImgWidth, enemyImgHeight);
   
    }
    
    
	public static void setPlayerCrop(Rectangle newCrop) {
		Assets.playerCrop = newCrop;
		player = playerSheet.crop(playerCrop.x, playerCrop.y, playerImgWidth, playerImgHeight);
	}
	public static Rectangle getPlayerCrop() {
		return playerCrop;
	}
	public static Rectangle getEnemyCrop() {
		return enemyCrop;
	}
	public static void setEnemyCrop(Rectangle newCrop) {
		Assets.enemyCrop = newCrop;
		enemy = enemySheet.crop(enemyCrop.x, enemyCrop.y, enemyImgWidth, enemyImgHeight);
	}
	
    
}
