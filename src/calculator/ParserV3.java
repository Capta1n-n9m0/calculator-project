package calculator;

import constants.Constants;
import numerics.Complex;
import numerics.Coordinate;
import numerics.Expression;
import numerics.Operation;

import java.util.LinkedList;

public class ParserV3 {

    LinkedList<StringBuilder> tokens = new LinkedList<>();
    StringBuilder currentToken = new StringBuilder();

    Result result = null;
    public String errorMessage = null;
    public ParserV3() {

    }

    public LinkedList<String> getAllTokens() {
        LinkedList<String> res = new LinkedList<>();
        for (var tokens : tokens) {
            res.add(tokens.toString());
        }
        if (!currentToken.isEmpty()) res.add(currentToken.toString());
        return res;
    }

    public String allTokensAsString() {
        StringBuilder res = new StringBuilder();
        for (var token : getAllTokens()) {
            res.append(token);
        }
        return res.toString();
    }

    public Result evaluate() throws Exception {
        completeSequence();

        Complex a = Complex.fromString(tokens.get(0).toString());
        Complex b = Complex.fromString(tokens.get(2).toString());
        Complex c = Complex.fromString(tokens.get(4).toString());
        Complex d = Complex.fromString(tokens.get(6).toString());

        Operation opA_B, opC_D, opC1_C2;
        switch (tokens.get(1).toString().charAt(0)) {
            case '+' -> opA_B = Operation.ADD;
            case '-' -> opA_B = Operation.SUBTRACT;
            default -> throw new IllegalStateException("Unexpected value: " + tokens.get(1).toString().charAt(0));
        }
        Expression c1 = new Expression(a, b, opA_B);
        System.out.println(c1);
        switch (tokens.get(5).toString().charAt(0)) {
            case '+' -> opC_D = Operation.ADD;
            case '-' -> opC_D = Operation.SUBTRACT;
            default -> throw new IllegalStateException("Unexpected value: " + tokens.get(5).toString().charAt(5));
        }
        Expression c2 = new Expression(c, d, opC_D);
        System.out.println(c2);
        switch (tokens.get(3).toString().charAt(0)) {
            case '+' -> opC1_C2 = Operation.ADD;
            case '-' -> opC1_C2 = Operation.SUBTRACT;
            case 'x', '*' -> opC1_C2 = Operation.MULTIPLY;
            case '/', '÷' -> opC1_C2 = Operation.DIVIDE;
            default -> throw new IllegalStateException("Unexpected value: " + tokens.get(3).toString().charAt(0));
        }
        Expression whole_expression = new Expression(c1, c2, opC1_C2);
        Result calculation_response = whole_expression.Calculate();
        this.result = calculation_response;
        clearSequence();
        if (calculation_response.isOk()) {
            StringBuilder builder = new StringBuilder();
            builder.append(result.getAnswer().getCoordinate().getX());
            if(result.getAnswer().getCoordinate().getY() >= 0) builder.append('+');
            builder.append(result.getAnswer().getCoordinate().getY());
            builder.append('i');
            System.out.println(builder);
            for (char character : builder.toString().toCharArray()) {
                addCharacter(character);
            }
            errorMessage = null;
        }else {
            clearSequence();
            switch (calculation_response.getStatusCode()){
                case DivisionByZero -> errorMessage = "Division by zero!";
                case SyntaxError -> errorMessage = "Syntax Error!";
                case IncorrectInputError -> errorMessage = "Incorrect input!";
                default -> throw new IllegalStateException("Unexpected value: " + calculation_response.getStatusCode());
            }
        }
        return calculation_response;
    }

    public void addCharacter(char c) throws Exception {
        errorMessage = null;
        if (c == '=') evaluate();
        else {
            switch (tokens.size()) {
                //tokens: (1 2 3) 4 (5 6 7) 8
                // sizes: (0 1 2) 3 (4 5 6) 7
                // writing 1st token or writing 5th token
                case 0, 4 -> {
                    switch (currentToken.length()) {
                        case 0 -> {
                            if (c == '-') {
                                currentToken.append(c);
                            } else if (Constants.isDigit(c)) {
                                currentToken.append(c);
                            } else if (c == '.') {
                                currentToken.append('0');
                                currentToken.append(c);
                            } else if (c == '+') {
                                currentToken.append('0');
                                tokens.add(currentToken);
                                currentToken = new StringBuilder();
                                currentToken.append(c);
                                tokens.add(currentToken);
                                currentToken = new StringBuilder();
                            }
                        }
                        case 1 -> {
                            if (currentToken.charAt(0) == '-') {
                                if (Constants.isDigit(c)) {
                                    currentToken.append(c);
                                } else if (c == '.') {
                                    currentToken.append('0');
                                    currentToken.append(c);
                                } else if (c == '-' || c == '+') {
                                    currentToken.append('1');
                                    tokens.add(currentToken);
                                    currentToken = new StringBuilder();
                                    currentToken.append(c);
                                    tokens.add(currentToken);
                                    currentToken = new StringBuilder();
                                }
                            } else {
                                if (Constants.isDigit(c)) {
                                    currentToken.append(c);
                                } else if (c == '.') {
                                    currentToken.append(c);
                                } else if (c == '-' || c == '+') {
                                    tokens.add(currentToken);
                                    currentToken = new StringBuilder();
                                    currentToken.append(c);
                                    tokens.add(currentToken);
                                    currentToken = new StringBuilder();
                                }
                            }
                        }
                        default -> {
                            if (Constants.isDigit(c)) {
                                currentToken.append(c);
                            } else if (c == '.') {
                                if (!currentToken.toString().contains(".")) {
                                    currentToken.append(c);
                                }
                            } else if (c == '-' || c == '+') {
                                if (currentToken.charAt(currentToken.length() - 1) == '.') {
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
                case 1, 5 -> {
                    if (!currentToken.isEmpty()) {
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        addCharacter(c);
                    }
                    if (c == '+' || c == '-') {
                        currentToken.append(c);
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                    }
                }
                // writing 3rd token
                case 2 -> {
                    if (currentToken.isEmpty()) {
                        if (Constants.isDigit(c)) {
                            currentToken.append(c);
                        } else if (c == '.') {
                            currentToken.append('0');
                            currentToken.append(c);
                        } else if (c == 'i') {
                            currentToken.append(c);
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                        } else if (Constants.isOperator(c)) {
                            currentToken.append('0');
                            currentToken.append('i');
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                            currentToken.append(c);
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                        }
                    } else {
                        if (Constants.isDigit(c)) {
                            currentToken.append(c);
                        } else if (c == '.') {
                            if (!currentToken.toString().contains(String.valueOf(c))) {
                                currentToken.append('.');
                            }
                        } else if (c == 'i') {
                            if (currentToken.charAt(currentToken.length() - 1) == '.')
                                currentToken.append('0');
                            currentToken.append('i');
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                        } else if (Constants.isOperator(c)) {
                            if (currentToken.charAt(currentToken.length() - 1) == '.')
                                currentToken.append('0');
                            if (currentToken.charAt(currentToken.length() - 1) != 'i')
                                currentToken.append('i');
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                            currentToken.append(c);
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                        }
                    }
                }
                // writing 4th token
                case 3 -> {
                    if (!currentToken.isEmpty()) {
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        addCharacter(c);
                    }
                    if (Constants.isOperator(c)) {
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
                case 6 -> {
                    if (currentToken.isEmpty()) {
                        if (Constants.isDigit(c)) {
                            currentToken.append(c);
                        } else if (c == '.') {
                            currentToken.append('0');
                            currentToken.append(c);
                        } else if (c == 'i') {
                            currentToken.append(c);
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                            evaluate();
                        } else if (Constants.isOperator(c) || c == '=') {
                            currentToken.append('0');
                            currentToken.append('i');
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                            currentToken.append(c);
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                            evaluate();
                        }
                    } else {
                        if (Constants.isDigit(c)) {
                            currentToken.append(c);
                        } else if (c == '.') {
                            if (!currentToken.toString().contains(String.valueOf(c))) {
                                currentToken.append('.');
                            }
                        } else if (c == 'i') {
                            if (currentToken.charAt(currentToken.length() - 1) == '.') {
                                currentToken.append('0');
                            }
                            currentToken.append(c);
                            tokens.add(currentToken);
                            currentToken = new StringBuilder();
                            evaluate();
                        } else if (Constants.isOperator(c) || c == '=') {
                            if (currentToken.charAt(currentToken.length() - 1) == '.') {
                                currentToken.append('0');
                            }
                            if (currentToken.charAt(currentToken.length() - 1) != 'i')
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
                case 7 -> {
                    if (Constants.isOperator(c) || c == '=') {
                        currentToken.append(c);
                        tokens.add(currentToken);
                        currentToken = new StringBuilder();
                        evaluate();
                    }
                }
            }
        }
    }

    public void completeCurrentToken() {
        switch (tokens.size()) {
            case 0, 4 -> {
                if (currentToken.isEmpty()) {
                    currentToken.append('0');
                } else {
                    if (currentToken.charAt(currentToken.length() - 1) == '-') {
                        currentToken.append('1');
                    } else if (currentToken.charAt(currentToken.length() - 1) == '.') {
                        currentToken.append('0');
                    }
                }
            }
            case 1, 3, 5 -> {
                if (currentToken.isEmpty()) {
                    currentToken.append('+');
                }
            }
            case 2, 6 -> {
                if (currentToken.isEmpty()) {
                    currentToken.append('0');
                    currentToken.append('i');
                } else {
                    if (currentToken.charAt(currentToken.length() - 1) == '.') {
                        currentToken.append('0');
                        currentToken.append('i');
                    }
                    if (currentToken.charAt(currentToken.length() - 1) != 'i') {
                        currentToken.append('i');
                    }
                }
            }
            case 7 -> {
                currentToken.append('=');
                tokens.add(currentToken);
                currentToken = new StringBuilder();
            }
        }
    }

    public void completeSequence() {
        while (tokens.size() < 8) {
            completeCurrentToken();
            tokens.add(currentToken);
            currentToken = new StringBuilder();
        }
    }

    public void clearSequence() {
        tokens.clear();
        currentToken = new StringBuilder();
    }

    public void removeLastCharacter() {
        if (currentToken.isEmpty()) {
            if (!tokens.isEmpty()) {
                currentToken = tokens.removeLast();
                removeLastCharacter();
            }
        } else currentToken.deleteCharAt(currentToken.length() - 1);
        if (currentToken.isEmpty()) {
            if (!tokens.isEmpty()) {
                currentToken = tokens.removeLast();
            }
        }
    }


}
