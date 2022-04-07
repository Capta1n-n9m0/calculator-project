package calculator;

public interface ICalculator {
    public Result add(Complex c1, Complex c2);
    public Result subtract(Complex c1, Complex c2);
    public Result multiply(Complex c1, Complex c2);
    public Result divide(Complex c1, Complex c2);
}
