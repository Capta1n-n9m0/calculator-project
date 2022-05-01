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

    public String allTokensAsString(){
        StringBuilder res = new StringBuilder();
        LinkedList<String> temp = getAllTokens();
        for(var token : temp){
            res.append(token);
        }
        return res.toString();
    }

    public String getCurrentToken(){
        return currentToken;
    }

    public ParserV2(){}

    public void replaceSequence(String s){
        clearSequence();
        for(char c : s.toCharArray()){
            addCharacter(c);
        }
    }

    public void addCharacter(char c){
        inCurrentToken = true;
        String s = String.valueOf(c);
        if(operators.contains(s)){
            if("-".contains(s)){
                //if the last token is operator, then - belongs to a number
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

    public void clearSequence(){
        currentToken = "";
        tokens.clear();
    }
}
