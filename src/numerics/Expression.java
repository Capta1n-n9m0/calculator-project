package numerics;

import calculator.*;
import constants.Constants;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Expression implements ICalculable {
    /**
     * Expression class allows dynamically constructing and
     * calculating mathematical expressions.
     * Interface ICalculable lets us calculate expression
     * when needed and use Expression alongside Complex.
     */
    private ICalculable value1 = null, value2 = null;
    private Result result = null;
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
    public Expression(ICalculable c1, Operation o) throws Exception{
        initialiseCalculator();
        this.value1 = c1;
        this.operation = o;
    }
    public Expression(ICalculable c1, ICalculable c2, Operation o) throws Exception{
        initialiseCalculator();
        this.value1 = c1;
        this.operation = o;
        this.value2 = c2;
    }

    public Result getValue() throws Exception{
        return Calculate();
    }

    public Result Calculate() throws Exception{
        if(result != null)
            return result;
        if(value1 == null){
            value1 = new Result(Constants.ZERO_COMPLEX, StatusCode.OK);
        }else{
            if(operation == null){
                operation = Operation.ADD;
            }else {
                if (value2 == null) {
                    value2 = value1;
                }
            }
        }
        switch (this.operation){
            case ADD ->         result = calculator.add(value1.getValue(), value2.getValue());
            case SUBTRACT ->    result = calculator.subtract(value1.getValue(), value2.getValue());
            case MULTIPLY ->    result = calculator.multiply(value1.getValue(), value2.getValue());
            case DIVIDE ->      result = calculator.divide(value1.getValue(), value2.getValue());
            default ->          throw new IllegalStateException("Invalid operation: " + this.operation);
        }
        return result;
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

    @Override
    public String toString() {
        String op = null;
        switch (operation){
            case ADD -> op = "+";
            case SUBTRACT -> op = "-";
            case DIVIDE -> op = "/";
            case MULTIPLY -> op = "*";
        }
        return String.format("(%s %s %s)%n", value1, op, value2);
    }
}
