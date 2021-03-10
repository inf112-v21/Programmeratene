package netcode;

import card.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import player.HumanPlayer;
import player.IPlayer;

import java.util.*;

public class Host extends Listener {
    private static final int udpPort = 27960;
    private static final int tcpPort = 27960;
    Server kryoServer;

    GameClient gameClient;

    HashMap<Connection,IPlayer> playerMap;
    int registersReceived = 0;

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
        c.setTimeout(0);

        IPlayer newPlayer = new HumanPlayer("Player "+(playerMap.size()+1));
        playerMap.put(c, newPlayer);
        gameClient.getGame().getBoard().addPlayer(newPlayer);
        gameClient.getGame().getBoard().drawPlayers();

        Scanner sc = new Scanner(System.in);
        System.out.print("Wait for more players? (y/n): ");
        if (sc.nextLine().equals("n"))
            dealCards();
    }

    //This is run when we receive a packet
    public void received(Connection c, Object p) {
        if(p instanceof CardListPacket){
            registersReceived++;

            ArrayList<ICard> cards = ((CardListPacket) p).cards;
            System.out.println("Host received cards: " + cards.toString());

            playerMap.get(c).setRegisters(cards);

            if(registersReceived >= playerMap.size())
                processRound();
        }
    }

    //This is run when a client has disconnected
    public void disconnected(Connection c) {
    }

    public void processRound() {
        registersReceived = 0;
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
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        dealCards();
    }

    public void dealCards(){
        ArrayList<ICard> gameDeck = new Deck().cards;
        for(Map.Entry<Connection, IPlayer> entry : playerMap.entrySet()) {
            CardListPacket playerHandPacket = new CardListPacket();
            for (int i = 0; i < 9; i++) {
                playerHandPacket.cards.add(gameDeck.get(0));
                gameDeck.remove(0);
            }
            entry.getKey().sendTCP(playerHandPacket);
        }
    }

    public void sendPlayerData(ArrayList<IPlayer> playerData){

    }

    public GameClient getGameClient() {
        return gameClient;
    }
}
