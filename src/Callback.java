import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class Callback extends UnicastRemoteObject implements MessengerCallback {

        protected Callback() throws RemoteException {
        }

        @Override
        public void onMessage(String s) throws RemoteException {
            System.out.println(s);
        }
    }