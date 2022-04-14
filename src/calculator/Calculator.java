package calculator;

import numerics.Complex;

public class Calculator implements ICalculator{
    public void DebugCall(Complex c1, String op, Complex c2, Result r){
        System.out.printf("Calculator called: %s %s %s = %s%n", c1, op, c2, r);
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
}
