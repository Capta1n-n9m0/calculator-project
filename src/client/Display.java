package client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyListener;

public class Display extends JPanel {
    private JLabel expression, result;
    private String text = "";
    public Display(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        expression = new JLabel();
        expression.setBackground(Color.BLACK);
        expression.setForeground(Color.WHITE);
        expression.setBorder(new LineBorder(new Color(45, 45, 45), 1, false));
        expression.setFont(new Font("Calibri", Font.ITALIC, 20));
        expression.setHorizontalAlignment(SwingConstants.RIGHT);
        expression.setText("0");
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0; c.gridx = 0;
        c.weighty = 0.35; c.weightx = 1;
        add(expression, c);

        result = new JLabel();
        result.setBackground(Color.BLACK);
        result.setForeground(Color.WHITE);
        result.setBorder(new LineBorder(Color.BLACK, 0, false));
        result.setFont(new Font("Calibri", Font.BOLD, 50));
        result.setHorizontalAlignment(SwingConstants.RIGHT);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1; c.gridx = 0;
        c.weighty = 0.65; c.weightx = 1;
        add(result, c);
        writeText();
    }
    public void writeText(){
        result.setText(text);
        expression.setText(text);
    }
    public void setText(String s){
        text = new String(s);
    }
    public void addText(String s){
        text += s;
    }
    public void addText(char c){
        text += c;
    }
    public void removeLast(){
        text = text.substring(0, text.length()-1);
    }
    public void cleanText(){
        text = "";
    }
}
