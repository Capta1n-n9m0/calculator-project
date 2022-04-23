package client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInterface extends JFrame implements KeyListener{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    private Display display;
    private Keyboard keyboard;
    public UserInterface(){
        super();
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
        setFocusable(true);
        this.addKeyListener(this);
        setMinimumSize(new Dimension(310, 550));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if("1234567890.+-*/eip()c\n".contains(String.valueOf(e.getKeyChar()))) {
            if (e.getKeyChar() == '\n'){
                display.addText('=');
                display.writeText();
            } else if(e.getKeyChar() == 'c'){
                display.cleanText();
                display.writeText();
            } else {
                display.addText(e.getKeyChar());
                display.writeText();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            display.removeLast();
            display.writeText();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
