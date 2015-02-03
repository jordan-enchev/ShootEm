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
            Game.player.goingUp = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Game.player.goingDown = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Game.player.goingLeft = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Game.player.goingRight = true;
        }
        if (keyCode == KeyEvent.VK_SPACE){
        	Game.player.setHasShot(true);
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
            Game.player.goingUp = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Game.player.goingDown = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Game.player.goingLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Game.player.goingRight = false;
        }
    }
}