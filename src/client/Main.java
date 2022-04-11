package client;

import calculator.*;
import constants.Constants;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws Exception {
        // testArithmetics();
        // testGUI();
        testExpression();
    }
    public static void testArithmetics() throws Exception {
        //testing arithmetics here
        System.out.println("Connecting to server.");
        Registry registry = LocateRegistry.getRegistry(Constants.SERVER, Constants.PORT);

        System.out.println("Getting object from server");
        ICalculator stub = (ICalculator) registry.lookup(Constants.TAG);

        System.out.println("Creating testing data.");
        Complex c1 = new Complex(new Coordinate(4,3));
        Complex c2 = new Complex(new Coordinate(-12, 5));
        Complex c3 = new Complex(new Polar(Math.PI/2, 0));

        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);

        System.out.println("Running tests.");
        Result res1 = stub.add(c1, c2);
        Result res2 = stub.multiply(c1, c2);
        Result res3 = stub.divide(c1, c3);
        Result res4 = stub.subtract(c3, c2);

        System.out.println("Showing results.");
        if(res1.isOk()){
            System.out.println("r1. c1 + c2 = " + res1.getAnswer());
        }
        if(res2.isOk()){
            System.out.println("r2. c1 * c2 = " + res2.getAnswer());
        }
        if(res3.isOk()){
            System.out.println("r3. c1 / c3 = " + res3.getAnswer());
        }
        if(res4.isOk()){
            System.out.println("r4. c3 - c2 = " + res4.getAnswer());
        }

        System.out.println("Done. Press Enter to close.");
        System.in.read();
    }
    public static void testGUI() throws Exception{
        UserInterface UI = new UserInterface();
    }
    public static void testExpression() throws Exception{
        System.out.println("Creating testing data.");
        Complex c1 = new Complex(new Coordinate(4,3));
        Complex c2 = new Complex(new Coordinate(-12, 5));
        Complex c3 = new Complex(new Coordinate(6, 7));

        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);

        System.out.println("Calculating c1 + (c2 + c3)*c1");
        //c1 + (c2 + c3)*c1
        Result r = (new Expression(c1, Operation.ADD,
                new Expression(new Expression(c2, Operation.ADD, c3), Operation.MULTIPLY, c1))).Calculate();

        System.out.println(r.getAnswer());


    }
}
