import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessengerCallback extends Remote {
    void onMessage(String s) throws RemoteException;
}
