import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.SortedSet;
import java.util.TreeSet;

public class ClientMain {


    static private Registry reg = null;
    static private FTBillboard currentServer = null;
    static private String serverID = null;

    /**
     * CORRECTION
     *
     */
    static SortedSet<String> repList = null;
    static boolean masterAvailable = true;
    /**
     * FIN CORRECTION
     */

    static public void main(String [] args) throws NotBoundException, RemoteException {

        if(args.length !=2) {
            System.out.println("USAGE: ServerMain master port");
            System.exit(0);
        }

        String address = args[0];
        int port = Integer.parseInt(args[1]);
        serverID = address+":"+port;

        try {
            reg = LocateRegistry.getRegistry(address,port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            currentServer = (FTBillboard) reg.lookup(FTBillboard.LOOKUP_NAME);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        /**
         * FIN CORRECTION
         */

        System.out.println("Starting Stupid client");

        for(int i = 0; i<1000 ; i++) {
            String message = "Hello guys " + i, received="";

            System.out.println("Test with message " + message);


            try {

                /**
                 * Correction
                 */
                repList = new TreeSet<>(currentServer.getNeighbors());

                /** fin correction */

                currentServer.setMessage(message);
                Thread.sleep(2500);
                received = currentServer.getMessage();
            } catch (RemoteException e) {
                /** Correction */
                masterAvailable = failover(repList);
                /** Fin Correction */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(received.equals(message)) {
                System.out.println("no problem");
            }
            else {
                System.out.println("Problem: " + received + " instead of " + message );
            }
        }
    }

    /**
     * Reconnects this client to a working master.
     * @param repList
     * @return
     */
    private static boolean failover(SortedSet<String> repList) {

        // Nothing available in list
        if(repList==null || repList.size() <= 1) {
            System.out.println("FAILOVER IMPOSSIBLE");
            return false;
        }
        // Remove old server
        repList.remove(serverID);
        serverID = repList.first();
        String address= serverID.split(":")[0];
        int port = Integer.parseInt(serverID.split(":")[1]);

        try {
            reg = LocateRegistry.getRegistry(address,port);
            currentServer = (FTBillboard) reg.lookup(FTBillboard.LOOKUP_NAME);
        } catch (Exception e) {
            reg =null;
        }

        if(reg !=null && currentServer!=null)
            return true;
        else
            return false;
    }


}
