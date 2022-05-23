package server;

import calculator.Calculator;
import calculator.ICalculator;
import constants.Constants;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.rmi.server.hostname", getIpAddress());
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

    private static String getIpAddress() throws SocketException {
        Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                String address = i.getHostAddress();
                Matcher matcher = pattern.matcher(address);

                if (matcher.find())
                    return address;
            }
        }

        return "127.0.0.1";
    }
}
