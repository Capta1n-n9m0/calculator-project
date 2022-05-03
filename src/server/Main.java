package server;

import calculator.Calculator;
import calculator.ICalculator;
import constants.Constants;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        System.out.println("Launching the server.");
        Registry registry = LocateRegistry.createRegistry(Constants.PORT);
        ICalculator stub = (ICalculator) UnicastRemoteObject.exportObject(new Calculator(), Constants.PORT);
        if(Arrays.asList(registry.list()).contains(Constants.TAG)){
            System.out.println("Rebinding remote.");
            registry.rebind(Constants.TAG, stub);
        } else{
            System.out.println("Binding remote.");
            registry.bind(Constants.TAG, stub);
        }
        System.out.println("Ready");
    }
}
