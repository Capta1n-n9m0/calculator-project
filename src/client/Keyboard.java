package client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class Keyboard extends JPanel {
    private final ArrayList<String> buttons = new ArrayList<String>(){
        {
            add("(");add(")");add("C");add("⌫");add("÷");
            add("⇄");add("7");add("8");add("9");add("x");
            add("ż");add("4");add("5");add("6");add("-");
            add("π");add("1");add("2");add("3");add("+");
            add("e");add(    "0"     );add(".");add("=");
        }
    };
    public Keyboard(){
        setBorder(new LineBorder(Color.BLACK, 1, false));
        GridBagLayout l = new GridBagLayout();
        setLayout(l);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        var ref = new Object() {
            public int counter = 0;
        };
        buttons.forEach((t)->{
            JButton button = new JButton(t);
            button.setBorder(new RoundedBorder(Color.BLACK, 2,20));
            c.fill = GridBagConstraints.BOTH;
            //c.insets = new Insets(1, 1, 1, 1);
            c.gridx = ref.counter % 5;
            c.gridy = ref.counter / 5;
            c.weightx = 1;
            c.weighty = 1;
            if(t.equals("0")) {
                c.gridwidth = 2;
                ref.counter++;
            }
            else c.gridwidth = 1;
            button.setFont(new Font("", Font.PLAIN, 20));
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if("÷x-+=".contains(t)){
                button.setBackground(new Color(227,156,51));
                button.setForeground(Color.WHITE);
            } else if("1234567890.".contains(t)){
                button.setBackground(Color.GRAY);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(Color.LIGHT_GRAY);
                button.setForeground(Color.BLACK);
            }
            //button.setBackground(Color.GRAY);
            add(button, c);
            ref.counter++;
        });
    }
}
