package calculator;

import constants.Constants;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Expression implements ICalculable{
    /**
     * Expression class allows dynamically constructing and
     * calculating mathematical expressions.
     * Interface ICalculable lets us calculate expression
     * when needed and use Expression alongside Complex.
     */
    private ICalculable value1 = null, value2 = null;
    private Complex result = null;
    private Operation operation;
    private ICalculator calculator;

    private void initialiseCalculator() throws Exception{
        Registry registry = LocateRegistry.getRegistry(Constants.SERVER, Constants.PORT);
        calculator = (ICalculator) registry.lookup(Constants.TAG);
    }

    public Expression() throws Exception {
        initialiseCalculator();
    }
    public Expression(ICalculable c) throws Exception{
        initialiseCalculator();
        this.value1 = c;
    }
    public Expression(ICalculable c, Operation o) throws Exception{
        initialiseCalculator();
        this.value1 = c;
        this.operation = o;
    }
    public Expression(ICalculable c1, Operation o, ICalculable c2) throws Exception{
        initialiseCalculator();
        this.value1 = c1;
        this.operation = o;
        this.value2 = c2;
    }

    public Complex getValue() throws Exception{
        return Calculate().getAnswer();
    }

    public Result Calculate() throws Exception{
        if(result != null)
            return new Result(result, StatusCode.OK);
        if(value1 == null){
            return calculator.add(Constants.ZERO_COMPLEX.getValue(), Constants.ZERO_COMPLEX);
        }else{
            if(operation == null){
                return calculator.add(value1.getValue(), Constants.ZERO_COMPLEX.getValue());
            }else {
                value2 = value1;
            }
        }
        switch (this.operation){
            case ADD -> {
                return calculator.add(value1.getValue(), value2.getValue());
            }
            case SUBTRACT -> {
                return calculator.subtract(value1.getValue(), value2.getValue());
            }
            case MULTIPLY -> {
                return calculator.multiply(value1.getValue(), value2.getValue());
            }
            case DIVIDE -> {
                return calculator.divide(value1.getValue(), value2.getValue());
            }
            default -> throw new IllegalStateException("Invalid operation: " + this.operation);
        }
    }

    // These setters are wrong, but it works for now
    public ICalculable getValue1() {
        return value1;
    }
    public Expression setValue1(ICalculable value1) {
        this.result = null;
        this.value1 = value1;
        return this;
    }
    public ICalculable getValue2() {
        return value2;
    }
    public Expression setValue2(ICalculable value2) {
        this.result = null;
        this.value2 = value2;
        return this;
    }
    public Operation getOperation() {
        return operation;
    }
    public Expression setOperation(Operation operation) {
        this.result = null;
        this.operation = operation;
        return this;
    }
}
