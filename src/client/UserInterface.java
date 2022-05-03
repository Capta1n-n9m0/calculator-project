package client;

import calculator.ICalculator;
import calculator.ParserV2;
import calculator.ParserV3;

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
    ParserV3 parser = new ParserV3();
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

    public void inputProcessor(char c) throws Exception {
        int c_code = (int) c;
        if("1234567890.+-*x/eip()cπ⇄C=÷\n".contains(String.valueOf(c))) {
            System.out.println((int) c);
            switch (c_code){
                case 'x', '*' -> parser.addCharacter('x');
                case '÷', '/' -> parser.addCharacter('÷');
                case '\n', '=' -> parser.addCharacter('=');
                case 'c', 'C' -> parser.clearSequence();
                case 'p' -> parser.addCharacter('π');
                default -> parser.addCharacter(c);
            }
        } else if(c == KeyEvent.VK_BACK_SPACE || c == '⌫') {
            parser.removeLastCharacter();
        }
        switch (c_code){
            case 9 -> parser.addCharacter('⇄');
        }
        display.sendTokens(parser.getAllTokens());
        //display.writeNewText(parser.allTokensAsString());
        System.out.println(parser.getAllTokens());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            inputProcessor(e.getKeyChar());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        System.out.println(s);
        try {
            inputProcessor(s.charAt(0));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
