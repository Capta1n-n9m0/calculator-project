package client;

import calculator.ParserV3;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.LinkedList;

public class Display extends JPanel {
    private JLabel expression, value;
    private String text = "0";
    private StringBuilder expressionText = new StringBuilder();
    private StringBuilder valueText = new StringBuilder();
    public Display(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        expression = new JLabel();
        expression.setFocusable(false);
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

        value = new JLabel();
        value.setFocusable(false);
        value.setBackground(Color.BLACK);
        value.setForeground(Color.WHITE);
        value.setBorder(new LineBorder(Color.BLACK, 0, false));
        value.setFont(new Font("Calibri", Font.BOLD, 50));
        value.setHorizontalAlignment(SwingConstants.RIGHT);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1; c.gridx = 0;
        c.weighty = 0.65; c.weightx = 1;
        add(value, c);
        sendTokens(new LinkedList<>());
    }

    public void sendExpression(LinkedList<String> tokens){
        expressionText.delete(0, expressionText.length());
        expressionText.append("<html>");
        for(int i = 0; i < 8; i++){
            if (i == 0 || i == 4) expressionText.append('(');
            if(i < tokens.size()){
                expressionText.append("<font style=\"color:white;\">");
                expressionText.append(tokens.get(i));
                expressionText.append("</font>");
            }else {
                expressionText.append("<font style=\"color:gray;\">");
                switch (i){
                    case 0, 4 -> expressionText.append("0.0");
                    case 1, 3, 5 -> expressionText.append("+");
                    case 2, 6 -> expressionText.append("0.0i");
                    case 7 -> expressionText.append("=");
                }
                expressionText.append("</font>");
            }
            if(i == 2 || i == 6) expressionText.append(')');
        }
        text = expressionText.toString();
        expressionText.append("</html>");
        expression.setText(expressionText.toString());
    }
    public void sendValue(LinkedList<String> tokens){
        valueText.delete(0, valueText.length());
        valueText.append("<html>");
        valueText.append("(");
        for(int i = 0; i < 3; i++){
            if(i < tokens.size()){
                valueText.append("<font style=\"color:white;\">");
                valueText.append(tokens.get(i));
                valueText.append("</font>");
            } else {
                valueText.append("<font style=\"color:gray;\">");
                switch (i){
                    case 0 -> valueText.append("0.0");
                    case 1 -> valueText.append("+");
                    case 2 -> valueText.append("0.0i");
                }
                valueText.append("</font>");
            }
        }
        valueText.append(")");
        valueText.append("</html>");
        value.setText(valueText.toString());
    }
    public void sendTokens(LinkedList<String> tokens){
        sendExpression(tokens);
        if(tokens.size() < 5){
            sendValue(new LinkedList<>(tokens.subList(0, tokens.size())));
        }else {
            sendValue(new LinkedList<>(tokens.subList(4, tokens.size())));
        }
    }
    public void sendParser(ParserV3 parser){
        if(parser.errorMessage == null)
            sendTokens(parser.getAllTokens());
        else{
            sendExpression(new LinkedList<>());
            valueText.delete(0, valueText.length());
            valueText.append("<html>");
            valueText.append("<font style=\"color:red;\">");
            valueText.append(parser.errorMessage);
            valueText.append("</font>");
            valueText.append("</html>");
            value.setText(valueText.toString());
        }
    }

    public void writeText(){
        value.setText(text);
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
    public void addAndWriteText(String s){
        addText(s);
        writeText();
    }
    public void addAndWriteText(char c){
        addText(c);
        writeText();
    }
    public void cleanAndWriteText(){
        cleanText();
        writeText();
    }

    public void writeNewText(String s){
        text = s;
        writeText();
    }
}
