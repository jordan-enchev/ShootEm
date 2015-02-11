package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Display.Display;

public class Input implements KeyListener {

    public Input(Display display) {
        display.getgFrame().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (keyCode == KeyEvent.VK_UP) {
            Game.getPlayer().goingUp = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Game.getPlayer().goingDown = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Game.getPlayer().goingLeft = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Game.getPlayer().goingRight = true;
        }
        if (keyCode == KeyEvent.VK_SPACE){
        	Game.getPlayer().hasShot(true);
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            Game.getPlayer().goingUp = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Game.getPlayer().goingDown = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Game.getPlayer().goingLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Game.getPlayer().goingRight = false;
        }
    }
}
