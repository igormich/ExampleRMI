import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

public class Client2 {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        System.setProperty(Common.RMI_HOSTNAME, Common.localhost);
        // URL удаленного объекта
        String objectName = Common.SERVICE_PATH;
        Messenger messenger = (Messenger) Naming.lookup(objectName);
        Callback callback = new Callback();
        User user = new User("Petr", new Random().nextLong());
        messenger.setCallback(callback, user);
        messenger.sendMessage("Hello to Ivan", user);
    }
}
