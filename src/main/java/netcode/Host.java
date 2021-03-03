package netcode;

import card.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import player.ComputerPlayer;
import player.IPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class Host extends Listener {
    private static final int udpPort = 27960;
    private static final int tcpPort = 27960;
    Server server;

    GameClient gameClient;

    HashMap<Connection,IPlayer> playerMap;

    public Host() throws Exception{
        System.out.println("Creating the server...");
        server = new Server();
        ClassRegister.registerAll(server.getKryo());
        server.start();
        server.addListener(this);
        server.bind(tcpPort,udpPort);

        playerMap = new HashMap<>();
        gameClient = new GameClient(true);
    }

    //This is run when a connection is received
    public void connected(Connection c) {
        System.out.println("Received a connection from "+c.getRemoteAddressTCP().getHostString());

        playerMap.put(c, new ComputerPlayer(c.getRemoteAddressTCP().getHostString()));

        dealCards();
    }

    //This is run when we receive a packet
    public void received(Connection c, Object p) {
        if(p instanceof NetworkPackage){
            ArrayList<ICard> cards = ((NetworkPackage) p).cards;
            System.out.println("Host received cards: " + cards.toString());

            playerMap.get(c).setRegisters(cards);
        }
    }

    public void processRound() {

        for(int i=0; i<5; i++) { //5 faser :)
            for (IPlayer player : playerMap.values()) {
                ICard currentCard = player.getRegisters().get(i);
                if (currentCard instanceof CardMove)
                    player.moveRobot(((CardMove) currentCard).getSteps());
                else if (currentCard instanceof CardTurn)
                    player.rotateRobot(((CardTurn) currentCard).getTurnSteps());
            }
        }
    }

    //This is run when a client has disconnected
    public void disconnected(Connection c) {
        System.out.println("A client disconnected!");
    }

    public void dealCards(){
        NetworkPackage testPacket = new NetworkPackage();
        ArrayList<ICard> deck = new Deck().cards;
        for(int i = 0; i < 9; i++) {
            testPacket.cards.add(deck.get(0));
            deck.remove(0);
        }

        server.getConnections()[0].sendTCP(testPacket);
    }
}
