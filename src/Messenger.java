import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Messenger extends Remote {
    void sendMessage(String s, User user) throws RemoteException;
    void setCallback(MessengerCallback callback, User user) throws RemoteException;
}
