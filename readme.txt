Object
Using the leader election with java RMI to ensure a fault tolerant service for client.

Compiling:
javac Billboard.java BillboardServer.java FTBillboard.java FTBillboardServer.java ClientMain.java ServerMain.java -d "your-destination-repo-path"

Launching
1.open a terminal, entering to the repo while contain the compiled classes.then, execute command->java ServerMain localhost:1099
2.repeat the first step except the command, enter command->ServerMain localhost:1199 localhost:1099 instead.
3.repeat the first step except the command, enter command->ClientMain localhost 1099

Test
1.shot down the master process launching at first step.
if(client still get the message){
      congratulations, your system have the ability of fault tolerance.
   }else{
      Failed, check your code again.
   }

2.shot down the replica process launching at second step.
your client won't receive the message anymore.
