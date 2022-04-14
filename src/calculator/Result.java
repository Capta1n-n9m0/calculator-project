package calculator;

import numerics.Complex;

import java.io.Serializable;

public class Result implements Serializable {
    private final Complex answer;
    private final StatusCode statusCode;

    public Result(Complex answer, StatusCode statusCode){
        this.answer = answer;
        this.statusCode = statusCode;
    }

    public Complex getAnswer(){
        return answer;
    }
    public StatusCode getStatusCode() {
        return statusCode;
    }
    public boolean isOk(){
        return statusCode.equals(StatusCode.OK);
    }

    public String toString() {
        String s;
        switch (statusCode) {
            case OK -> {
                s = "OK";
            }
            case SyntaxError -> {
                s = "SyntaxError";
            }
            case DivisionByZero -> {
                s = "DivisionByZero";
            }
            case IncorrectInputError -> {
                s = "IncorrectInputError";
            }
            default -> {
                throw new IllegalStateException("Unknown status code: " + statusCode);
            }
        }
        return String.format("Result { %s; %s}", s, answer);
    }
}
