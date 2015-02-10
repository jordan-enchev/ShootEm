package game;

import gfx.Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
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
	private Input iHandler = null;
	private Random rand = new Random();
	
		
		
	public static boolean running = false;
	private Thread thread;
	
	public static Background background = null;
	public static Player player = null;
	public static ArrayList<Enemy> enemies = null;	
	public static ArrayList<Bullet> bullets = null;

	public Game()	{
	}
	
	public void init()	{
		display = new Display();
		if(display == null)	{
			System.out.println("Failed to init display");
		}
		else	{
			System.out.println("Display inited");
			
			Assets.init();
			background = new Background();
			if(background == null)	{
				System.out.println("Failed to init backgroud");
			}
			else	{
				System.out.println("Background inited");
				iHandler = new Input(this.display);	
				if(iHandler == null){
					System.out.println("Failed to init iHandler");
					}
				else	{
					System.out.println("iHandler inited");
					player = new Player();
					if (player == null) {
						System.out.println("Failed to init player");
						
					}
					else {
						System.out.println("Player inited");
						enemies = new ArrayList<Enemy>();
						bullets = new ArrayList<Bullet>();
						enemies.add(new Enemy(rand.nextInt(1600), 450, rand.nextInt((100-10)+10)));	
//						enemies.add(new Enemy(rand.nextInt(1600), 450, rand.nextInt((100-10)+10)));	
//						enemies.add(new Enemy(rand.nextInt(1600), 450, rand.nextInt((100-10)+10)));	
					}
				}
			}
		}
		
	}		
	

	private void tick() {  
		
			
		player.tick();
			if(player.hasShot){
				shoot(new Bullet(player.getxPos(), player.getyPos(), player.getDir()));
				player.hasShot = false;
			}
			if(player.getHealth() <= 0){
				running = false;
			}
		player.changeAsset();
		
		if(player.getxPos() < 110)	{
			player.setxPos(110);
			background.moveRight();
		}
		if(player.getxPos() > 600)	{
			player.setxPos(600);
			background.moveLeft();
		}
		
		
		
		
		int e = enemies.size();
		while(e-- > 0){
			Enemy enemy = enemies.get(e);
			if(enemy.isDead())	{	
				enemies.remove(enemy);
				enemy.finalize();
			}
			else	{
				if(background.dir == 1)	{
	        		enemy.setxPos(enemy.getxPos() + CONSTANTS.VEL);
	        		background.dir = 0;
	        	}
	        	if(background.dir == -1)	{
	        		enemy.setxPos(enemy.getxPos() - CONSTANTS.VEL);
	        		background.dir = 0;
	        	}
				enemy.tick();
				
			}
		}
		
		
		
		int l = bullets.size();
		while(l-- > 0){
			Bullet bullet = bullets.get(l);
			if(!bullet.inBounds() || bullet.hasHit())	{
				bullets.remove(bullet);
   				bullet.finalize();
			}
			else	{
				bullet.tick();
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

        for (Enemy enemy : enemies) {
        	       	
        	if(!enemy.isDead())
        	{
        		enemy.render(g);
        	}
		}
        
        
        for (Bullet bullet : bullets) {
        	if(bullet.inBounds() || !bullet.hasHit())	{
        		bullet.render(g);
        	}
        		
		}
        
        
        player.render(g);

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
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            //Amount of time passed divided by the max amount of time allowed.
            delta += (now-lastTime) / timePerTick;
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
        
        if(running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

 
    public synchronized void stop() {
        if(!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void shoot(Bullet bullet) {
		bullets.add(bullet);
	}

	public static ArrayList<Bullet> getBullets() {
		return bullets;
	}
	

    

}