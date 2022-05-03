package calculator;

import java.util.Currency;
import java.util.HashMap;
import java.util.LinkedList;

public class ParserV2 {
    private int numberOfOpenBrackets = 0;
    private int numberOfClosedBrackets = 0;
    private final static String operators = "*x/÷+-";
    //private final static String numerics = "0123456789.i";
    private final static String numerics = "0123456789";
    private final static String specials = "ep.iπ()";
    private final static HashMap<String, Integer> operatorPriority = new HashMap<>(){{
        put("(", 0);put(")", 0);
        put("/", 1);put("÷", 1);put("*", 1);put("x", 1);
        put("+", 2);put("-", 2);
    }};
    protected LinkedList<String> tokens = new LinkedList<>();
    protected StringBuilder currentToken = new StringBuilder();

    public static boolean isOperator(String s){
        return operators.contains(s);
    }
    public static boolean isNumeric(String s){
        return numerics.contains(s);
    }
    public static boolean isSpecial(String s){
        return specials.contains(s);
    }
    public static boolean isOperator(char s){
        return operators.contains(String.valueOf(s));
    }
    public static boolean isNumeric(char s){
        return numerics.contains(String.valueOf(s));
    }
    public static boolean isSpecial(char s){
        return specials.contains(String.valueOf(s));
    }


    public static boolean isContainsDot(String s){
        return s.contains(".");
    }

    public static boolean isContainsDot(StringBuilder s){
        return s.toString().contains(".");
    }

    public LinkedList<String> getProcessedTokens(){
        return tokens;
    }
    public LinkedList<String> getAllTokens(){
        LinkedList<String> temp = (LinkedList<String>) tokens.clone();
        System.out.println(currentToken.length());
        if(currentToken.length() > 0)temp.add(currentToken.toString());
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
        return currentToken.toString();
    }

    public ParserV2(){}

    public void replaceSequence(String s){
        clearSequence();
        for(char c : s.toCharArray()){
            addCharacter(c);
        }
    }

    private boolean ifCanAddOperator(){
        // operator can be added only after a number or a closing bracket
        // if there is a number in currentToken, it should be pushed to token and token itself should be pushed
        if(tokens.isEmpty() && currentToken.isEmpty()) return false;
        if(!currentToken.isEmpty()) return true;
        if(operators.contains(tokens.getLast())) return false;
        if("(".contains(tokens.getLast())) return false;
        return true;
    }

    private boolean ifPreviousIsOperator(){
        if(!tokens.isEmpty()) {
            return !operators.contains(tokens.getLast());
        }
        return false;
    }

    private void addMinusSign(char c){
        if(tokens.isEmpty()){
            if(currentToken.isEmpty()){
                currentToken.append(c);
            }else {
                if(currentToken.charAt(currentToken.length()-1) != '-'){
                    pushAndResetCurrentToken();
                    currentToken.append(c);
                    pushAndResetCurrentToken();
                }
            }
        }else {
            String lastToken = tokens.getLast();
            if(currentToken.isEmpty()){

            }
        }
    }
    private void addOperator(char c){
        // special processing for -sign, because it can be a part of a number
        if(c == '-'){
            addMinusSign(c);
        }
        // + / * should be processed as usual
        else{
            if (ifCanAddOperator()) {
                pushAndResetCurrentToken();
                currentToken.append(c);
                pushAndResetCurrentToken();
            }
        }
    }
    private void addNumber(char c){
        currentToken.append(c);
    }
    private void addSpecial(char c){
        switch (c){
            case '(' -> {
                if(tokens.isEmpty()){
                    currentToken.append(c);
                    pushAndResetCurrentToken();
                }else {

                }
            }
            case ')' -> {

            }
            case 'i' -> {

            }
            case '.' -> {

            }
            case 'e' -> {

            }
            case 'p', 'π' -> {

            }
        }
    }

    public void addCharacter(char c){
        String s = String.valueOf(c);
        if(tokens.isEmpty()){
            if(currentToken.isEmpty()){
                if(numerics.contains(s)){
                    addNumber(c);
                }else {
                    switch (c){
                        case '-'->{
                            currentToken.append(c);
                        }
                        case '(', 'e', 'p', 'i'->{
                            currentToken.append(c);
                            pushAndResetCurrentToken();
                        }
                    }
                }
            }else {
                if(isNumeric(c)){
                    addNumber(c);
                }else if(isOperator(c)) {
                    pushAndResetCurrentToken();
                    currentToken.append(c);
                    pushAndResetCurrentToken();
                }
                else {
                }
            }
        }else {
            if(currentToken.isEmpty()){

            }else {

            }
        }
        // processing operators
        if(isOperator(c)){
            addOperator(c);
        }
        //processing numbers
        else if(isNumeric(c)){
            addNumber(c);
        }
        //processing numbers, i, e, pi, dot and brackets
        else if(isSpecial(c)){
            addSpecial(c);
        }
        System.out.printf("%s + %s%n",tokens, currentToken);
    }

//    public void addCharacter(char c){
//       //        String s = String.valueOf(c);
//        if(operators.contains(s)){
//            if("-".contains(s)){
//                //if the last token is operator, then - belongs to a number
//                if(operators.contains(tokens.get(tokens.size()-1)) && currentToken.length() == 0){
//                    currentToken += s;
//                }
//                else {
//                    if (currentToken.length() != 0) pushAndResetCurrentToken();
//                    tokens.add(s);
//
//                }
//            } else{
//                if (currentToken.length() != 0) pushAndResetCurrentToken();
//                tokens.add(s);
//
//            }
//        } else if(numerics.contains(s)){
//            if("i".contains(s)) {
//                currentToken += s;
//                pushAndResetCurrentToken();
//            }
//            else{
//                if(".".contains(s)){
//                    if(!currentToken.contains(s)){
//                        currentToken += s;
//                    }
//                } else {
//                    currentToken += s;
//                }
//            }
//        }
//    }



    private void pushAndResetCurrentToken(){
        if(!currentToken.isEmpty()) {
            tokens.add(currentToken.toString());
            currentToken.delete(0, currentToken.length());
        }
    }

    public void removeLastCharacter(){
        if (currentToken.isEmpty()){
            if(!tokens.isEmpty()){
                currentToken.append(tokens.removeLast());
                removeLastCharacter();
            }
        }else currentToken.deleteCharAt(currentToken.length()-1);
    }

    public void clearSequence(){
        if(!currentToken.isEmpty())currentToken.delete(0, currentToken.length());
        tokens.clear();
    }
}
