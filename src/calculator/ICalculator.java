package calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculator extends Remote {
    public Result add(Complex c1, Complex c2) throws RemoteException;
    public Result subtract(Complex c1, Complex c2) throws RemoteException;
    public Result multiply(Complex c1, Complex c2) throws RemoteException;
    public Result divide(Complex c1, Complex c2) throws RemoteException;
}
