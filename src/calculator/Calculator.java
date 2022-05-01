package calculator;

import constants.Constants;
import numerics.Complex;

import java.rmi.RemoteException;

public class Calculator implements ICalculator{
    public void DebugCall(Complex c1, String op, Complex c2, Result r){
        System.out.printf("Calculator called: (%s) %s (%s) = %s%n", c1, op, c2, r);
    }
    public Result add(Complex c1, Complex c2){
        Result r = new Result(c1.add(c2), StatusCode.OK);
        DebugCall(c1, "+", c2, r);
        return r;
    }
    public Result subtract(Complex c1, Complex c2){
        Result r = new Result(c1.sub(c2), StatusCode.OK);
        DebugCall(c1, "-", c2, r);
        return r;
    }
    public Result multiply(Complex c1, Complex c2){
        Result r = new Result(c1.mul(c2), StatusCode.OK);
        DebugCall(c1, "*", c2, r);
        return r;
    }
    public Result divide(Complex c1, Complex c2){
        Result r;
        if(c2.getPolar().getRadius() == 0)
            r = new Result(null, StatusCode.DivisionByZero);
        else
            r = new Result(c1.div(c2), StatusCode.OK);
        DebugCall(c1, "/", c2, r);
        return r;
    }
    public Result pow(Complex c1, Complex c2){
        Result r;
        if(c1.getPolar().getRadius() == 0 && c1.getPolar().getRadius() == 0)
            r = new Result(null, StatusCode.SyntaxError);
        else
            r = new Result(c1.pow(c2), StatusCode.OK);
        DebugCall(c1, "^", c2, r);
        return r;
    }
    public Result exp(Complex c){
        Result r = new Result(Complex.exp(c), StatusCode.OK);
        DebugCall(Constants.E_COMPLEX, "^", c, r);
        return r;
    }
    public Result ln(Complex c){
        Result r;
        if(c.getPolar().getRadius() == 0)
            r = new Result(null, StatusCode.SyntaxError);
        else
            r = new Result(Complex.ln(c), StatusCode.OK);
        System.out.printf("Calculator called: log(e, %s) = %s%n", c, r);
        return r;
    }
}
