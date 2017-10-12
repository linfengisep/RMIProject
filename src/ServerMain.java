import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

    public static void main(String[] args) throws RemoteException, NotBoundException {
         //and &&, or || in java
        if(args.length > 2 || args.length==0) {//reminding us to configure one or two localhost each time;
            System.out.println("USAGE: ServerMain local:port [master:port]");
            System.exit(0);
        }

        String local, master;//localhost
        int localPort, masterPort;//port number

        String [] parsed = args[0].split(":");
        local = parsed[0];  //will get the localhost
        localPort = Integer.parseInt(parsed[1]);  //will get the localhost port number;
        boolean isMaster = true; //this port will be served as the master port and master server;

        if(args.length > 1) {//if we configure two localhost in the terminal
            isMaster = false;
            parsed = args[1].split(":");
            master = parsed[0];
            masterPort = Integer.parseInt(parsed[1]);


        }  else {
            master = local;
            masterPort = localPort;
        }

        Registry localRegistry = null,
                 remoteRegistry = null;

        try {
            localRegistry = LocateRegistry.createRegistry(localPort);
            if(!isMaster)
                remoteRegistry = LocateRegistry.getRegistry(master,masterPort);

        } catch (RemoteException e) {
            e.printStackTrace();
        }


        if(localRegistry !=null && isMaster) {
            // Create master instance
            FTBillboardServer server = new FTBillboardServer(local+":"+localPort,
                                                            master+":"+masterPort,
                                                            null);
            localRegistry.rebind(FTBillboard.LOOKUP_NAME, server);
            System.out.println("STARTED as master on " + args[0]);
            server.startPing();

        } else if(localRegistry != null && remoteRegistry !=null && !isMaster){
            FTBillboard masterServer = (FTBillboard) remoteRegistry.lookup(FTBillboard.LOOKUP_NAME);
            FTBillboardServer localServer = new FTBillboardServer(local+":"+localPort,master+":"+masterPort,masterServer);
            localRegistry.rebind(FTBillboard.LOOKUP_NAME, localServer);
            masterServer.registerReplica(args[0], localServer);
            System.out.println("STARTED as replica of " + args[1] + " on " + args[0]);
            localServer.startPing();
        }
    }
}
