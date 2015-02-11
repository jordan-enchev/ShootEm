package gfx;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int playerImgWidth = 90, playerImgHeight = 150,
    						enemyImgWidth = 30, enemyImgHeight = 60,
    						bulletImgWidth = 60, bulletImgHeight = 30;
    
    private static Rectangle playerCrop = new Rectangle(55, 35, playerImgWidth, playerImgHeight), 
    						enemyCrop = new Rectangle(40, 10, enemyImgWidth, enemyImgHeight), 
    						bulletCrop = new Rectangle(0, 190, bulletImgWidth, bulletImgHeight);
    
    static SpriteSheet playerSheet, enemySheet, bulletSheet;

    public static BufferedImage player, enemy, bullet, background;
    //Loads every resource needed for the game
    public static void init() {
        
    	playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
    	enemySheet = new SpriteSheet(ImageLoader.loadImage("/textures/zombie.gif"));
    	bulletSheet = new SpriteSheet(ImageLoader.loadImage("/textures/shoot.png"));
    	
    	
        background = ImageLoader.loadImage("/textures/background.png");

        player = playerSheet.crop(playerCrop.x, playerCrop.y, playerImgWidth, playerImgHeight);
        enemy = enemySheet.crop(enemyCrop.x, enemyCrop.y, enemyImgWidth, enemyImgHeight);
        bullet = bulletSheet.crop(bulletCrop.x, bulletCrop.y, bulletImgWidth, bulletImgHeight);
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


	public static Rectangle getBulletCrop() {
		return bulletCrop;
	}


	public static void setBulletCrop(Rectangle bulletCrop) {
		Assets.bulletCrop = bulletCrop;
	}
	
    
}
