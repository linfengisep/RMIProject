import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FTClient {
    static private Registry reg = null;
    static private FTBillboard currentServer = null;
    static private String serverID = null;

    static public void main(String [] args) throws NotBoundException {
        //if user enter less than or more than two paramter in the terminal for the FTClient,exit it;
        if(args.length !=2) {
            System.out.println("USAGE:master portNumber");
            System.exit(0);
        }

        String address = args[0];
        int port = Integer.parseInt(args[1]); //get the port number;
        serverID = address+":"+port;

        try {
         /*Returns a stub/reference to the remote object Registry (FTBillboard) on the specified host and port(enter at terminal).
         *If host is null, the local host is used
         */
            reg = LocateRegistry.getRegistry(address,port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            //look for the remote stub by its name(FTBillboard.LOOKUP_NAME) in the registry
            currentServer = (FTBillboard)reg.lookup(FTBillboard.LOOKUP_NAME);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("Starting fault-tolerant Client");

        for(int i = 0; i<1000 ; i++) {
            String message = "Hello guys " + i, received="";
            System.out.println("Test with message " + message);
            try {
                currentServer.setMessage(message);
                Thread.sleep(2500);//suspending this process for 1.5 seconds;
                received = currentServer.getMessage();
            } catch (RemoteException e) {
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(received.equals(message)) {
                System.out.println("no problem");
            }
            else {
                FTBillboardServer ftb =new FTBillboardServer(local+":"+localPort, address+":"+portNumber,null);
                List replicaSet=ftb.getNeighbors()
                String newmaster =replicaSet.firstKey();
            }
        }
    }
}
