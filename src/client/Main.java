package client;

import calculator.*;
import constants.Constants;
import numerics.*;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws Exception {
        // Tests
        // testArithmetics();
        // testGUI();
        // testExpression();
        testTokens();

        //closing
        System.out.println("Done. Press Enter to close.");
        System.in.read();
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
        Complex c3 = new Complex(new Polar(0, Math.PI/2));

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
    }
    public static void testGUI() throws Exception{
        UserInterface UI = new UserInterface();
    }
    public static void testExpression() throws Exception{
        System.out.println("Creating testing data.");
        Complex c0 = new Complex(new Polar(0, Math.PI/2));
        Complex c1 = new Complex(new Coordinate(4,3));
        Complex c2 = new Complex(new Coordinate(-12, 5));
        Complex c3 = new Complex(new Coordinate(6, 7));

        System.out.println("c0 = " + c0);
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);

        System.out.println("Calculating r1 = c1 + (c2 + c3)*c1");
        System.out.println("Calculating r2 = r1 * c0");
        System.out.println("Calculating r3 = r1 / c0");

        //c1 + (c2 + c3)*c1
        Result r1 = (
                new Expression(
                c1, Operation.ADD, new Expression(
                        new Expression(
                                c2, Operation.ADD, c3
                        ), Operation.MULTIPLY, c1)
                )
            ).Calculate();
        Result r2 = (new Expression(r1.getAnswer(), Operation.MULTIPLY, c0)).Calculate();
        System.out.println("c0 = " + c0);
        Result r3 = (new Expression(r1.getAnswer(), Operation.DIVIDE, c0)).Calculate();
        Result r4 = new Expression(c1)
                .setOperation(Operation.ADD)
                .setValue2(new Expression(new Expression()
                            .setValue1(c2)
                            .setOperation(Operation.ADD)
                            .setValue2(c3))
                        .setOperation(Operation.MULTIPLY)
                        .setValue2(c1)).Calculate();

        System.out.println("r1 = " + r1);
        System.out.println("r2 = " + r2);
        System.out.println("r3 = " + r3);
        System.out.println("r4 = " + r4);
    }
    public static void testTokens() throws Exception{
        String s1 = "1+2i+5+(12i-1/(3+2i))*2";
        System.out.println("s1 = " + s1);
    }
}
