package calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculator extends Remote {
    public Result add(Result c1, Result c2) throws RemoteException;
    public Result subtract(Result c1, Result c2) throws RemoteException;
    public Result multiply(Result c1, Result c2) throws RemoteException;
    public Result divide(Result c1, Result c2) throws RemoteException;
    public Result pow(Result c1, Result c2) throws RemoteException;
    public Result exp(Result c) throws RemoteException;
    public Result ln(Result c) throws RemoteException;
}
