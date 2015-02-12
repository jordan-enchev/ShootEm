package game;

import gfx.Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import constants.CONSTANTS;

public class Player {

    private int health;
    private int xPos, yPos, dir;
    private Rectangle collisionBox;
    private Random rand;

    private final int startPos = 400;
    private final int safeArea = 200;

    private boolean hasShot;

    public boolean goingUp;
    public boolean goingDown;
    public boolean goingLeft;
    public boolean goingRight;

    public Player() {
        health = 100;
        xPos = startPos;
        yPos = 400;
        collisionBox = new Rectangle(150, 150);
    }

    public void tick() {

        collisionBox.setBounds(xPos, yPos, 150, 150);

        if (goingDown) {
            if (getyPos() < CONSTANTS.GAME_HEIGHT - 150) {
                setyPos(yPos + CONSTANTS.VEL);
            } else {
                goingDown = false;
            }
        }
        if (goingUp) {
            if (getyPos() > 360) {
                setyPos(yPos - CONSTANTS.VEL);
            } else {
                goingUp = false;
            }
        }
        if (goingLeft) {
            if (getxPos() >= startPos - safeArea - 90) {
                setxPos(xPos - CONSTANTS.VEL);
            }
            dir = -1;
        }
        if (goingRight) {
            if (getxPos() <= startPos + safeArea) {
                setxPos(xPos + CONSTANTS.VEL);
            }
            dir = 1;
        }
    }

    public boolean isMoving() {
        return (goingDown || goingLeft || goingRight || goingUp);
    }

    public void changeAsset() {

        Rectangle newCrop = Assets.getPlayerCrop();
        if (goingRight) {
            newCrop.y = 35;
            if (newCrop.x + 200 < 800) {
                newCrop.x += 200;
            } else {
                newCrop.x = 55;
            }
            Assets.setPlayerCrop(newCrop);
        } else if (goingLeft) {
            newCrop.y = 235;
            if (newCrop.x + 200 < 800) {
                newCrop.x += 200;
            } else {
                newCrop.x = 55;
            }
            Assets.setPlayerCrop(newCrop);
        }
    }

    public void render(Graphics g) {
        g.drawImage(Assets.player, this.xPos, this.yPos, null);
    }

    public void loseHealth(int damage) {
        if (isHit() && !isDead()) {
            health -= damage;
            System.out.println("Damage taken " + damage);
            System.out.println("Health left " + getHealth());
        }
    }
    public boolean isDead()	{
    	if (health <= 0) {
			return true;
		}
    	return false;
    }

    public boolean isHit() {
        rand = new Random();
        int hit = rand.nextInt(1 - 0 + 1);

        if (hit == 1) {
            return true;
        }
        return false;

    }

//	
//	
//	
//	
//	Getters and setters:
//	
//	
//	
//	
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

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean hasShot() {
        return hasShot;
    }

    public void hasShot(boolean hasShot) {
        this.hasShot = hasShot;
    }

}
