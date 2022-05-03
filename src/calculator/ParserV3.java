package calculator;

import constants.Constants;
import numerics.Complex;

import java.util.Currency;
import java.util.LinkedList;

public class ParserV3 {

    LinkedList<StringBuilder> tokens = new LinkedList<>();
    StringBuilder currentToken = new StringBuilder();

    public ParserV3(){

    }
    public LinkedList<String> getAllTokens(){
        LinkedList<String> res = new LinkedList<>();
        for(var tokens : tokens){
            res.add(tokens.toString());
        }
        if(!currentToken.isEmpty()) res.add(currentToken.toString());
        return res;
    }
    public String allTokensAsString(){
        StringBuilder res = new StringBuilder();
        for(var token : getAllTokens()){
            res.append(token);
        }
        return res.toString();
    }

    public Complex evaluate(){
        return Constants.ZERO_COMPLEX;
    }

    public void addCharacter(char c){
        System.out.println("Called addChar: " + c);
        switch (tokens.size()){
            //tokens: (1 2 3) 4 (5 6 7) 8
            // sizes: (0 1 2) 3 (4 5 6) 7
            // writing 1st token or writing 5th token
            case 0, 4 ->{
                System.out.println("case 0");
                switch (currentToken.length()){
                    case 0 ->{
                        if(c == '-'){
                            currentToken.append(c);
                        }else if(Constants.isDigit(c)){
                            currentToken.append(c);
                        }else if(c == '.'){
                            currentToken.append('0');
                            currentToken.append(c);
                        }else if (c == '+'){
                            currentToken.append('0');
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                            currentToken.append(c);
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                        }
                    }
                    case 1 ->{
                        if(currentToken.charAt(0) == '-'){
                            if(Constants.isDigit(c)){
                                currentToken.append(c);
                            }else if(c == '.'){
                                currentToken.append('0');
                                currentToken.append(c);
                            }else if(c == '-' || c == '+'){
                                currentToken.append('1');
                                tokens.add(currentToken);
                                currentToken = new StringBuilder();
                                currentToken.append(c);
                                tokens.add(currentToken);
                                currentToken = new StringBuilder();
                            }
                        }else {
                            if(Constants.isDigit(c)){
                                currentToken.append(c);
                            }else if(c == '.'){
                                currentToken.append(c);
                            } else if(c == '-' || c == '+') {
                                tokens.add(currentToken);
                                currentToken = new StringBuilder();
                                currentToken.append(c);
                                tokens.add(currentToken);
                                currentToken = new StringBuilder();
                            }
                        }
                    }
                    default ->{
                        if(Constants.isDigit(c)){
                            currentToken.append(c);
                        }else if(c == '.'){
                            if(!currentToken.toString().contains(".")){
                                currentToken.append(c);
                            }
                        }else if(c == '-' || c == '+'){
                            if(currentToken.charAt(currentToken.length()-1) == '.'){
                                currentToken.append('0');
                            }
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                            currentToken.append(c);
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                        }
                    }
                }
            }
            // writing 2nd token or writing 6th token
            case 1, 5 ->{
                if(c == '+' || c == '-'){
                    currentToken.append(c);
                    tokens.add(currentToken);
                    currentToken = new StringBuilder();
                }
            }
            // writing 3rd token
            case 2 ->{
                if(currentToken.isEmpty()){
                    if(Constants.isDigit(c)){
                        currentToken.append(c);
                    }else if(c == '.'){
                        currentToken.append('0');
                        currentToken.append(c);
                    }else if(c == 'i'){
                        currentToken.append(c);
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                    }else if(Constants.isOperator(c)){
                        currentToken.append('0');
                        currentToken.append('i');
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        currentToken.append(c);
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                    }
                }else {
                    if(Constants.isDigit(c)){
                        currentToken.append(c);
                    }else if(c == '.') {
                        if(!currentToken.toString().contains(String.valueOf(c))){
                           currentToken.append('.');
                        }
                    }else if(c == 'i'){
                        if(currentToken.charAt(currentToken.length()-1) == '.'){
                            currentToken.append('0');
                        }
                        currentToken.append('i');
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                    }else if(Constants.isOperator(c)){
                        if(currentToken.charAt(currentToken.length()-1) == '.'){
                            currentToken.append('0');
                        }
                        currentToken.append('i');
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        currentToken.append(c);
                        tokens.add(currentToken);
                        currentToken =  new StringBuilder();
                    }
                }
            }
            // writing 4th token
            case 3 ->{
                if(Constants.isOperator(c)){
                    currentToken.append(c);
                    tokens.add(currentToken);
                    currentToken = new StringBuilder();
                }
            }
            // writing 5th token
            // case 4: same as case 0. Writing 5th token is same as writing 1st token
            // writing 6th token
            // case 5: same as case 1. Writing 6th toke is same as writing 2nd token
            // writing 7th token
            case 6 ->{
                if(currentToken.isEmpty()){
                    if(Constants.isDigit(c)){
                        currentToken.append(c);
                    }else if(c == '.'){
                        currentToken.append('0');
                        currentToken.append(c);
                    }else if(c == 'i'){
                        currentToken.append(c);
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        evaluate();
                    }else if(Constants.isOperator(c) || c == '='){
                        currentToken.append('0');
                        currentToken.append('i');
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        currentToken.append(c);
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        evaluate();
                    }
                }else {
                    if(Constants.isDigit(c)){
                        currentToken.append(c);
                    }else if(c == '.') {
                        if(!currentToken.toString().contains(String.valueOf(c))){
                            currentToken.append('.');
                        }
                    }else if(c == 'i'){
                        if(currentToken.charAt(currentToken.length()-1) == '.'){
                            currentToken.append('0');
                        }
                        currentToken.append(c);
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        evaluate();
                    }else if(Constants.isOperator(c) || c == '='){
                        if(currentToken.charAt(currentToken.length()-1) == '.'){
                            currentToken.append('0');
                        }
                        currentToken.append('i');
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        currentToken.append(c);
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        evaluate();
                    }
                }
            }
            // equal sign, or
            case 7 ->{
                if(Constants.isOperator(c) || c == '='){
                    currentToken.append(c);
                    tokens.add(currentToken);
                    currentToken = new StringBuilder();
                    evaluate();
                }
            }
        }
    }

    public void completeCurrentToken(){
        switch (tokens.size()){
            case 0, 4 -> {
                if(currentToken.isEmpty()){
                    currentToken.append('0');
                } else {
                    if(currentToken.charAt(currentToken.length()-1) == '-'){
                        currentToken.append('1');
                    } else if(currentToken.charAt(currentToken.length()-1) == '.'){
                        currentToken.append('0');
                    }
                }
            }
            case 1, 3, 5 -> {
                if(currentToken.isEmpty()){
                    currentToken.append('+');
                }
            }
            case 2, 6 ->{
                if(currentToken.isEmpty()){
                    currentToken.append('0');
                    currentToken.append('i');
                }else {
                    if(currentToken.charAt(currentToken.length()-1) == '.'){
                        currentToken.append('0');
                        currentToken.append('i');
                    }
                }
            }
            case 7 ->{
                currentToken.append('=');
                tokens.add(currentToken);
                currentToken = new StringBuilder();
            }
        }
    }

    public void completeSequence(){
        while (tokens.size() < 8){
            completeCurrentToken();
            tokens.add(currentToken);
            currentToken = new StringBuilder();
        }
    }

    public void clearSequence(){
        tokens.clear();
        currentToken = new StringBuilder();
    }

    public void removeLastCharacter(){
        if(currentToken.isEmpty()){
            if(!tokens.isEmpty()){
                currentToken = tokens.removeLast();
                removeLastCharacter();
            }
        }else currentToken.deleteCharAt(currentToken.length()-1);
    }


}
