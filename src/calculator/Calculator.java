package calculator;

public class Calculator implements ICalculator{

    public Result add(Complex c1, Complex c2){
        return new Result(c1.add(c2), StatusCode.OK);
    }
    public Result subtract(Complex c1, Complex c2){
        return new Result(c1.sub(c2), StatusCode.OK);
    }
    public Result multiply(Complex c1, Complex c2){
        return new Result(c1.mul(c2), StatusCode.OK);
    }
    public Result divide(Complex c1, Complex c2){
        if(c2.getPolar().getRadius() == 0)
            return new Result(null, StatusCode.DivisionByZero);
        return new Result(c1.div(c2), StatusCode.OK);
    }
}
