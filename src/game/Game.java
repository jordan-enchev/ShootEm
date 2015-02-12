package game;

import gfx.Assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import Display.Background;
import Display.Display;
import constants.CONSTANTS;

public class Game implements Runnable {

    private Display display = null;
    private Graphics g = null;
    private BufferStrategy bs = null;
    @SuppressWarnings("unused")
    private Input iHandler = null;
    private Random rand = new Random();
    private boolean running = false;
    private int eCount = 1;

    private Thread thread;

    private Background background = null;
    private ArrayList<Enemy> enemies = null;
    private ArrayList<Bullet> bullets = null;

    private static Player player = null;

    public Game() {
    }

    public void init() {
        display = new Display();

        Assets.init();
        background = new Background();

        iHandler = new Input(this.display);

        player = new Player();
        enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();

        genEnemy(eCount);
    }

    private void tick() {

        //Player
        player.tick();
        if (enemies.isEmpty()) {
            genEnemy(++eCount);
        }

        if (player.hasShot()) {
            shoot(new Bullet(player.getxPos(), player.getyPos(), player.getDir()));
            player.hasShot(false);
        }

        if (player.isDead()) {
        	if	(iHandler.getX() >= 200 && iHandler.getX() < 575 )	{
        		if (iHandler.getY() >= 415 && iHandler.getY() < 450) {
					restart();
				}
        	}
        }

        if (background.getDir() != 0 || player.isMoving()) {
            player.changeAsset();
        }

        if (player.getxPos() < 110) {
            player.setxPos(110);
            background.moveRight();
        }

        if (player.getxPos() > 600) {
            player.setxPos(600);
            background.moveLeft();
        }

        //Enemies
        int e = enemies.size();
        while (e-- > 0) {
            Enemy enemy = enemies.get(e);
            if (enemy.isDead()) {
                enemy.finalize();
                enemies.remove(enemy);
            } else {
                if (background.getDir() == 1) {
                    enemy.setxPos(enemy.getxPos() + CONSTANTS.VEL);
                    background.setDir(0);
                }
                if (background.getDir() == -1) {
                    enemy.setxPos(enemy.getxPos() - CONSTANTS.VEL);
                    background.setDir(0);
                }
                enemy.tick();
            }
        }

        //Bullets
        int l = bullets.size();
        while (l-- > 0) {
            Bullet bullet = bullets.get(l);
            if (!bullet.inBounds() || bullet.hasHit()) {

                bullet.finalize();
                bullets.remove(bullet);

            } else {
                bullet.tick();
                for (Enemy enemy : enemies) {
                    if (bullet.intersects(enemy.getCollisionBox())) {
                        enemy.takeDMG();
                        enemy.isHit(false);
                        bullet.hasHit(true);
                    }
                }
            }
        }
    }

    private void render() {
        bs = display.getgFrame().getBufferStrategy();
        if (bs == null) {
            display.getgFrame().createBufferStrategy(2);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT);

        background.draw(g);
        
        if(!player.isDead())	{
	        for (Enemy enemy : enemies) {
	
	            if (!enemy.isDead()) {
	                enemy.render(g);
	            }
	        }
	
	        for (Bullet bullet : bullets) {
	            if (bullet.inBounds() || !bullet.hasHit()) {
	                bullet.render(g);
	            }
	        }
	        player.render(g);
	    }
        else	{
        	g.drawImage(Assets.gameOver, 150, 125, null);
        }
        
        bs.show();
        g.dispose();
        
    }

    //Implementing the interface's method
    @Override
    public void run() {
        init();

        //Sets the frames per seconds
        int fps = 30;
        //1 000 000 000 nanoseconds in a second. Thus we measure time in nanoseconds
        //to be more specific. Maximum allowed time to run the tick() and render() methods
        double timePerTick = 1_000_000_000.0 / fps;
        //How much time we have until we need to call our tick() and render() methods
        double delta = 0;
        //The current time in nanoseconds
        long now;
        //Returns the amount of time in nanoseconds that our computer runs.
        long lastTime = System.nanoTime();
        long timer = 0;
        @SuppressWarnings("unused")
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            //Amount of time passed divided by the max amount of time allowed.
            delta += (now - lastTime) / timePerTick;
            //Adding to the timer the time passed
            timer += now - lastTime;
            //Setting the lastTime with the values of now time after we have calculated the delta
            lastTime = now;

            //If enough time has passed we need to tick() and render() to achieve 60 fps
            if (delta >= 1) {
                tick();
                render();
                //Reset the delta
                ticks++;
                delta--;
            }

            if (timer >= 1_000_000_000) {
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start() {

        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shoot(Bullet bullet) {
        bullets.add(bullet);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void genEnemy(int eCount) {
        for (int i = 0; i < eCount; i++) {

            Enemy enemy = new Enemy(rand.nextInt(1600), 450, 50);
            for (Enemy e : enemies) {
                if (enemy.getCollisionBox().contains(e.getCollisionBox()) || enemy.getCollisionBox().contains(player.getCollisionBox())) {
                    enemy = new Enemy(rand.nextInt(1600), 450, 50);
                }
            }
            enemies.add(enemy);
        }
    }

    public static Player getPlayer() {
        return player;
    }
    
    public void reset()	{
        enemies = null;
        bullets = null;
        player = null;
        iHandler = null;
        background = null;
        eCount = 0;
        running = false;
    }
    
    public void restart()	{
    	reset();
    	
    	background = new Background();
    	player = new Player();
    	iHandler = new Input(display);
        enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();
    	
        running = true;
    }
    

}
