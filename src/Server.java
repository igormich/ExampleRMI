import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Server {

    static class MessengerImpl implements Messenger {

        private Map<User, MessengerCallback> callbacks = new HashMap<>();

        private boolean trySend(Map.Entry<User, MessengerCallback> it, String message) {
            try {
                it.getValue().onMessage(message);
            } catch (RemoteException e) {
                return true;
            }
            return false;
        }

        @Override
        public void sendMessage(String message, User user) throws RemoteException {
            callbacks.entrySet().removeIf(it -> trySend(it, user.name() +" : "+ message));
        }

        @Override
        public void setCallback(MessengerCallback callback, User user) throws RemoteException {
            callbacks.put(user, callback);
            System.out.println(callbacks.keySet());
        }
    }

    public static void main(String[] args) {
        try {
            System.setProperty(Common.RMI_HOSTNAME, Common.localhost);
            Registry registry = LocateRegistry.createRegistry(1099);
            Messenger messenger = (Messenger) UnicastRemoteObject.exportObject( new MessengerImpl(), 0);
            registry.rebind(Common.SERVICE_NAME, messenger);
        } catch (RemoteException e) {
            System.err.println("RemoteException : " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
            System.exit(2);
        }
        while(true) {

        }
    }
}
