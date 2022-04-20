package client;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    JTextField expression, result;
    public Display(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        expression = new JTextField();
        expression.setBackground(new Color(200,50,50));
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0; c.gridx = 0;
        c.weighty = 0.2; c.weightx = 1;
        add(expression, c);

        result = new JTextField();
        result.setBackground(new Color(130, 50,50));
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1; c.gridx = 0;
        c.weighty = 0.8; c.weightx = 1;
        add(result, c);
    }
}
