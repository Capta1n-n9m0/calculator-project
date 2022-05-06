package calculator;

import constants.Constants;
import numerics.Complex;

public class Calculator implements ICalculator{
    public void DebugCall(Result c1, String op, Result c2, Result r){
        System.out.printf("Calculator called: (%s) %s (%s) = %s%n", c1, op, c2, r);
    }
    public Result add(Result c1, Result c2){
        Result r;
        if (c1.isOk() && c2.isOk()){
            r = new Result(c1.getAnswer().add(c2.getAnswer()), StatusCode.OK);
        }else {
            if(c1.isOk()) r = new Result(null, c2.getStatusCode());
            else r = new Result(null, c1.getStatusCode());
        }
        DebugCall(c1, "+", c2, r);
        return r;
    }
    public Result subtract(Result c1, Result c2){
        Result r;
        if (c1.isOk() && c2.isOk()){
            r = new Result(c1.getAnswer().sub(c2.getAnswer()), StatusCode.OK);
        }else {
            if(c1.isOk()) r = new Result(null, c2.getStatusCode());
            else r = new Result(null, c1.getStatusCode());
        }
        DebugCall(c1, "-", c2, r);
        return r;
    }
    public Result multiply(Result c1, Result c2){
        Result r;
        if (c1.isOk() && c2.isOk()){
            r = new Result(c1.getAnswer().mul(c2.getAnswer()), StatusCode.OK);
        }else {
            if(c1.isOk()) r = new Result(null, c2.getStatusCode());
            else r = new Result(null, c1.getStatusCode());
        }
        DebugCall(c1, "*", c2, r);
        return r;
    }
    public Result divide(Result c1, Result c2){
        Result r;
        if (c1.isOk() && c2.isOk()){
            if(c2.getAnswer().getPolar().getRadius() == 0){
                r = new Result(null, StatusCode.DivisionByZero);
            }else
                r = new Result(c1.getAnswer().div(c2.getAnswer()), StatusCode.OK);
        }else {
            if(c1.isOk()) r = new Result(null, c2.getStatusCode());
            else r = new Result(null, c1.getStatusCode());
        }
        DebugCall(c1, "/", c2, r);
        return r;
    }
    public Result pow(Result c1, Result c2){
        Result r;
        if (c1.isOk() && c2.isOk()){
            if(c1.getAnswer().getPolar().getRadius() == 0 && c1.getAnswer().getPolar().getRadius() == 0)
                r = new Result(null, StatusCode.SyntaxError);
            else
                r = new Result(c1.getAnswer().pow(c2.getAnswer()), StatusCode.OK);
        }else {
            if(c1.isOk()) r = new Result(null, c2.getStatusCode());
            else r = new Result(null, c1.getStatusCode());
        }
        DebugCall(c1, "^", c2, r);
        return r;
    }
    public Result exp(Result c){
        Result r;
        if(c.isOk()){
            r = new Result(Complex.exp(c.getAnswer()), StatusCode.OK);
        }else {
            r = new Result(null, c.getStatusCode());
        }
        DebugCall(new Result(Constants.E_COMPLEX, StatusCode.OK), "^", c, r);
        return r;
    }
    public Result ln(Result c){
        Result r;
        if (c.isOk()){
            if(c.getAnswer().getPolar().getRadius() == 0)
                r = new Result(null, StatusCode.SyntaxError);
            else
                r = new Result(Complex.ln(c.getAnswer()), StatusCode.OK);
        }else {
            r = new Result(null, c.getStatusCode());
        }
        System.out.printf("Calculator called: log(e, %s) = %s%n", c, r);
        return r;
    }
}
