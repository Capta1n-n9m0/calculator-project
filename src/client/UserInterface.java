package client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UserInterface extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    private Display display;
    private Keyboard keyboard;
    public UserInterface(){
        setTitle("Awesome Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        display = new Display();
        display.setBackground(Color.BLACK);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.15;
        add(display, c);

        keyboard = new Keyboard();
        keyboard.setBackground(Color.BLACK);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.85;
        add(keyboard, c);

        //For titleless window.
        //setUndecorated(true);
        setMinimumSize(new Dimension(310, 550));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
