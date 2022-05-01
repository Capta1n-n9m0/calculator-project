package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInterface extends JFrame implements KeyListener, ActionListener {
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
        display.setFocusable(false);
        display.setBackground(Color.BLACK);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.15;
        add(display, c);

        keyboard = new Keyboard(this);
        keyboard.setFocusable(false);
        keyboard.setBackground(Color.BLACK);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.85;
        add(keyboard, c);

        //For titleless window.
        //setUndecorated(true);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        this.addKeyListener(this);
        setMinimumSize(new Dimension(310, 550));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private void inputProcessor(char c){
        int c_code = (int) c;
        System.out.println(c_code);
        if("1234567890.+-*x/eip()cπ⇄C=÷\n".contains(String.valueOf(c))) {
            switch (c){
                case 'x', '*' -> display.addAndWriteText('x');
                case '÷', '/' -> display.addAndWriteText('÷');
                case '\n', '=' -> display.addAndWriteText('=');
                case 'c', 'C' -> display.cleanAndWriteText();
                case 'p' -> display.addAndWriteText('π');
                default -> display.addAndWriteText(c);
            }
        } else if(c == KeyEvent.VK_BACK_SPACE || c == '⌫') {
            display.removeLast();
            display.writeText();
        }
        switch (c_code){
            case 9 -> display.addAndWriteText("⇄");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputProcessor(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        System.out.println(s);
        inputProcessor(s.charAt(0));
    }
}
