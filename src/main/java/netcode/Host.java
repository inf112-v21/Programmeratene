package netcode;

import card.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.util.ArrayList;

public class Host extends Listener {
    static Server server;
    static int udpPort = 27960, tcpPort = 27960;

    public static void main(String[] args) throws Exception {
        System.out.println("Creating the server...");

        server = new Server();

        server.getKryo().register(NetworkPackage.class);
        server.getKryo().register(ArrayList.class);
        server.getKryo().register(ICard.class);
        server.getKryo().register(CardMove.class);
        server.getKryo().register(CardTurn.class);

        server.bind(tcpPort,udpPort);
        server.start();

        server.addListener(new Host());
        System.out.println("Server is operational");
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

    /**
     * if damage == 0 then deal 9 cards else deal 9 - damage tokens
     *

    void deliverCardsToPlayer() {
        for(int i = 0; i < 9; i++) {
            playerCards.add(deck.get(0));
            deck.remove(0);
        }
    }
     */

    //This is run when we receive a packet
    public void received(Connection c, Object p) {

    }

    //This is run when a client has disconnected
    public void disconnected(Connection c) {
        System.out.println("A client disconnected!");
    }
}
