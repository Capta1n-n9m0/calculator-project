package client;

import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    private Display display;
    private Keyboard keyboard;
    public UserInterface(){
        setTitle("Awesome Calculator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        display = new Display();
        display.setBackground(new Color(200, 70, 70));
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.3;
        add(display, c);

        keyboard = new Keyboard();
        keyboard.setBackground(new Color(70, 200, 70));
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.7;
        add(keyboard, c);


        setLocationRelativeTo(null);
        setVisible(true);
    }
}
