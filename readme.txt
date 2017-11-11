Suject: Practical Leader Election with JAVA RMI

Goal: make a simple fault tolerant service with Java RMI.

Service:
1.Making a network billboard fault tolerant.
2.Client can connect to the service and change the message on the billboard.

Design pattern: delegate pattern


Launch
1.start a terminal

2.compile all the java file to a directory javac Billboard.java BillboardServer.java FTBillboard.java FTBillboardServer.java ClientMain.java ServerMain.java -d "your-customed-path"
Note: because of the dependencies between, the compilation order of the java class is father class before the children class.

3.launch a master server
java ServerMain localhost:1099

4.open another terminal to launch the same class as the replica server
java ServerMain localhost:1199 localhost:1099

5.open another terminal to launch class ClientMain at port 1099
java ClientMain localhost 1099


Test
shot down the master terminal, see whether the service is available at client side?
if(yes){
Congratulations, your client have the same service even one fault happens to one of your server.
Your system implement the property-> fault tolerance.
}else{
Failed, check your code again.
}
