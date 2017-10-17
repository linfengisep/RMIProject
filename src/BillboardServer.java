import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BillboardServer extends UnicastRemoteObject implements Billboard {

    private final Object msgLock = new Object();
    private String message;

    protected BillboardServer() throws RemoteException {
        super();
    }

    @Override
    public void setMessage(String message) throws RemoteException {

        synchronized (msgLock) {//the object 'msgLock' that provides the intrinsic lock
            this.message = message;
        }

    }

    @Override
    public String getMessage() throws RemoteException {
        String out;
        synchronized (msgLock) {
            out = message;
        }
        return out;
    }
}
