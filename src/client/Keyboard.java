package client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Keyboard extends JPanel {
    private final ArrayList<String> buttons = new ArrayList<String>(){
        {
            add("7");
            add("8");
            add("9");
            add("-");
            add("4");
            add("5");
            add("6");
            add("+");
            add("1");
            add("2");
            add("3");
            add("x");
            add("0");
            add(".");
            add("/");
            add("=");
        }
    };
    public Keyboard(){
        GridLayout l = new GridLayout(4,4);
        l.setHgap(2);
        l.setVgap(2);
        setLayout(l);
        buttons.forEach((t)->{
            JButton button = new JButton(t);
            button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.setBackground(Color.GRAY);
            add(button);
        });
    }
}
