package game;

import gfx.Assets;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import Display.Display;
import constants.CONSTANTS;

public class Game implements Runnable {
	
	private Display display = null;
	private Graphics g = null;
	private BufferStrategy bs = null;
	private Input iHandler = null;
		
		
	public static boolean running = false;
	private Thread thread;
	
	public static Player player = null;
	public static Enemy enemy = null;	

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
			
				 
				System.out.println("Background image inited");
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
							}
							enemy = new Enemy(600, 450, 100);
							
						}
			
		}
		
	}		
	private void tick() {  
		player.tick();
		player.changeAsset();
		enemy.tick();
		
    }
	
    //The method that will draw everything on the canvas
    private void render() {
        //Setting the bufferStrategy to be the one used in our canvas
        //Gets the number of buffers that the canvas should use.
        this.bs = display.getgFrame().getBufferStrategy();
        //If our bufferStrategy doesn't know how many buffers to use
        //we create some manually
        if (bs == null) {
            //Create 2 buffers
            display.getgFrame().createBufferStrategy(2);
            //returns out of the method to prevent errors
            return;
        }
        //Instantiates the graphics related to the bufferStrategy
        g = bs.getDrawGraphics();
        //Clear the screen at every frame
        g.clearRect(0, 0, CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT);
        //Beginning of drawing things on the screen

        g.drawImage(Assets.background, 0, 0, CONSTANTS.GAME_WIDTH, CONSTANTS.GAME_HEIGHT, null);
        
        player.render(g);
//        for (Enemy enemy : enemies) {
			enemy.render(g);
//		}
       
        //End of drawing objects

        //Enables the buffer
        bs.show();
        //Shows everything stored in the Graphics object
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
            //Sets the now variable to the current time in nanoseconds
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

        //Calls the stop method to ensure everything has been stopped
        stop();
    }

    //Creating a start method for the Thread to start our game
    //Synchronized is used because our method is working with threads
    //so we ensure ourselves that nothing will go bad
    public synchronized void start() {
        //If the game is running exit the method
        //This is done in order to prevent the game to initialize
        //more than enough threads
        if(running) {
            return;
        }
        //Setting the while-game-loop to run
        running = true;
        //Initialize the thread that will work with "this" class (game.Game)
        thread = new Thread(this);
        //The start method will call start the new thread and it will call
        //the run method in our class
        thread.start();
    }

    //Creating a stop method for the Thread to stop our game
    public synchronized void stop() {
        //If the game is not running exit the method
        //This is done to prevent the game from stopping a
        //non-existing thread and cause errors
        if(!running) {
            return;
        }
        running = false;
        //The join method stops the current method from executing and it
        //must be surrounded in try-catch in order to work
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}