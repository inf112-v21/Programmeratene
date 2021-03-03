package netcode;

import card.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.util.ArrayList;

public class Host extends Listener {
    private static final int udpPort = 27960;
    private static final int tcpPort = 27960;
    Server server;
    GameClient gameClient;

    public Host() throws Exception{
        System.out.println("Creating the server...");
        server = new Server();
        ClassRegister.registerAll(server.getKryo());
        server.start();
        server.addListener(this);
        server.bind(tcpPort,udpPort);
        
        gameClient = new GameClient(true);
    }

    //This is run when a connection is received
    public void connected(Connection c) {
        System.out.println("Received a connection from "+c.getRemoteAddressTCP().getHostString());

        NetworkPackage testPacket = new NetworkPackage();
        ArrayList<ICard> deck = new Deck().cards;
        for(int i = 0; i < 9; i++) {
            testPacket.cards.add(deck.get(0));
            deck.remove(0);
        }

        c.sendTCP(testPacket);
    }

    //This is run when we receive a packet
    public void received(Connection c, Object p) {

    }

    //This is run when a client has disconnected
    public void disconnected(Connection c) {
        System.out.println("A client disconnected!");
    }
}
