package calculator;

import java.util.LinkedList;

public class ParserV2 {
    private String operators = "*x/÷+-()";
    private String numerics = "0123456789.i";
    protected LinkedList<String> tokens = new LinkedList<>();
    protected String currentToken = "";
    protected boolean inCurrentToken = false;


    public LinkedList<String> getProcessedTokens(){
        return tokens;
    }
    public LinkedList<String> getAllTokens(){
        LinkedList<String> temp = (LinkedList<String>) tokens.clone();
        System.out.println(currentToken.length());
        if(currentToken.length() > 0)temp.add(currentToken);
        return temp;
    }


    public String getCurrentToken(){
        return currentToken;
    }

    public ParserV2(){}

    public void addCharacter(char c){
        inCurrentToken = true;
        String s = String.valueOf(c);
        if(operators.contains(s)){
            if("-".contains(s)){
                //if the last token is operator
                if(operators.contains(tokens.get(tokens.size()-1)) && currentToken.length() == 0){
                    currentToken += s;
                }
                else {
                    if (currentToken.length() != 0) pushAndResetCurrentToken();
                    tokens.add(s);
                    inCurrentToken = false;
                }
            } else{
                if (currentToken.length() != 0) pushAndResetCurrentToken();
                tokens.add(s);
                inCurrentToken = false;
            }
        } else if(numerics.contains(s)){
            if("i".contains(s)) {
                currentToken += s;
                pushAndResetCurrentToken();
            }
            else{
                if(".".contains(s)){
                    if(!currentToken.contains(s)){
                        currentToken += s;
                    }
                } else {
                    currentToken += s;
                }
            }
        }
    }

    private void pushAndResetCurrentToken(){
        tokens.add(currentToken);
        currentToken = "";
        inCurrentToken = false;
    }

    public void removeLastCharacter(){
        if(currentToken.length() == 0){
            currentToken = tokens.removeLast();
        }
        currentToken = currentToken.substring(0, currentToken.length()-1);
    }
}
