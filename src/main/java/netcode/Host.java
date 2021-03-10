package netcode;

import card.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import player.HumanPlayer;
import player.IPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Host extends Listener {
    private static final int udpPort = 27960;
    private static final int tcpPort = 27960;
    Server kryoServer;

    GameClient gameClient;

    HashMap<Connection,IPlayer> playerMap;
    int responsesReceived = 0;

    public Host() throws Exception{
        System.out.println("Creating the server...");
        kryoServer = new Server();
        ClassRegister.registerAll(kryoServer.getKryo());
        kryoServer.start();
        kryoServer.addListener(this);
        kryoServer.bind(tcpPort,udpPort);

        playerMap = new HashMap<>();
        gameClient = new GameClient(true);
    }

    //This is run when a connection is received
    public void connected(Connection c) {
        System.out.println("Received a connection from "+c.getRemoteAddressTCP().getHostString());
        c.setTimeout(60000);

        IPlayer newPlayer = new HumanPlayer(c.getRemoteAddressTCP().getHostString());
        playerMap.put(c, newPlayer);
        gameClient.getGame().getBoard().addPlayer(newPlayer);
        gameClient.getGame().getBoard().drawPlayers();

        dealCards();
    }

    //This is run when we receive a packet
    public void received(Connection c, Object p) {
        responsesReceived++;
        if(p instanceof CardListPacket){
            ArrayList<ICard> cards = ((CardListPacket) p).cards;
            System.out.println("Host received cards: " + cards.toString());

            playerMap.get(c).setRegisters(cards);

            if(responsesReceived >= playerMap.size())
                processRound();
        }
    }

    //This is run when a client has disconnected
    public void disconnected(Connection c) {
    }

    public void processRound() {
        responsesReceived = 0;
        for(int i=0; i<5; i++) { //5 faser :)
            // <sort players by priority of card in registry[i]>
            ArrayList<IPlayer> players = new ArrayList<>(playerMap.values());
            int finalI = i;
            Collections.sort(players, (o1, o2) -> o1.getRegisters().get(finalI).compareTo(o2.getRegisters().get(finalI)));
            for (IPlayer player : players) {
                gameClient.getGame().getBoard().clearPos(player.getPos());

                ICard currentCard = player.getRegisters().get(i);
                if (currentCard instanceof CardMove)
                    player.moveRobot(((CardMove) currentCard).getSteps());
                else if (currentCard instanceof CardTurn)
                    player.rotateRobot(((CardTurn) currentCard).getTurnSteps());

                gameClient.getGame().getBoard().drawPlayers();

                try { //Delay for syns skyld
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            try { //Delay for syns skyld
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void dealCards(){
        ArrayList<ICard> gameDeck = new Deck().cards;
        CardListPacket playerHandPacket = new CardListPacket();
        for(int i = 0; i < 9; i++) {
            playerHandPacket.cards.add(gameDeck.get(0));
            gameDeck.remove(0);
        }

        kryoServer.getConnections()[0].sendTCP(playerHandPacket);
    }

    public GameClient getGameClient() {
        return gameClient;
    }
}
