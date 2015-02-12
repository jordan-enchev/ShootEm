package Display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import constants.CONSTANTS;

@SuppressWarnings("serial")
public class Display extends Canvas {

    private JFrame wFrame;
    private Canvas gFrame;

    public Display() {
        createDisplay();
    }

    private void createDisplay() {
        wFrame = new JFrame(CONSTANTS.GAME_TITLE);
        wFrame.setSize(CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT);
        wFrame.setVisible(true);
        wFrame.setResizable(false);
        wFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wFrame.setLocationRelativeTo(null);

        gFrame = new Canvas();
        gFrame.setSize(new Dimension(CONSTANTS.GAME_WIDTH, CONSTANTS.GAME_HEIGHT));
        gFrame.setMaximumSize(new Dimension(CONSTANTS.GAME_WIDTH, CONSTANTS.GAME_HEIGHT));
        gFrame.setMinimumSize(new Dimension(CONSTANTS.GAME_WIDTH, CONSTANTS.GAME_HEIGHT));
        gFrame.setFocusable(true);

        //Linking the canvas onto the frame
        wFrame.add(gFrame);
    }

    public Canvas getgFrame() {
        return gFrame;
    }

}
