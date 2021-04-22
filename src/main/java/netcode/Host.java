package netcode;

import card.*;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import game.Direction;
import netcode.packets.CardListPacket;
import netcode.packets.PlayerDataPacket;
import netcode.packets.PlayerWonPacket;
import player.HumanPlayer;
import player.IPlayer;

import java.util.*;
import java.util.stream.Collectors;

public class Host extends Listener {
    private static final int udpPort = 27960;
    private static final int tcpPort = 27960;
    final Server kryoServer;

    GameClient gameClient;

    HashMap<Connection,IPlayer> playerMap;
    ArrayList<Vector2> spawns;
    ArrayList<Integer> flags;
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
        spawns = gameClient.getGame().getBoard().getSpawns();
        flags = gameClient.getGame().getBoard().getFlags();
    }

    //This is run when a connection is received
    public void connected(Connection c) {
        System.out.println("Received a connection from "+c.getRemoteAddressTCP().getHostString());
        c.setTimeout(0);

        IPlayer newPlayer = new HumanPlayer("Player "+(playerMap.size()+1), spawns.get(playerMap.size()));
        playerMap.put(c, newPlayer);

        sendPlayerData(); //Make alle clients add the new player

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
            playerMap.get(c).setRegisters(cards);
            //System.out.println("Host received cards: " + cards.toString()); //for debugging

            if(registersReceived >= playerMap.entrySet().stream().filter(x -> x.getValue().getAlive()).count())
                processRound();
        }
    }

    //This is run when a client has disconnected
    public void disconnected(Connection c) {
        playerMap.get(c).kill();
    }


    public void processRound() {
        registersReceived = 0;
        for(int i=0; i<5; i++) { //5 faser :)
            // <sort players by priority of card in registry[i]>
            ArrayList<IPlayer> players = new ArrayList<>(playerMap.values());
            int finalI = i;
            players.sort((o1, o2) -> o1.getRegisters().get(finalI).compareTo(o2.getRegisters().get(finalI)));
            for (IPlayer player : players.stream().filter(IPlayer::getAlive).collect(Collectors.toList())) {
                ICard currentCard = player.getRegisters().get(i);
                if (currentCard instanceof CardMove) {
                    if (((CardMove) currentCard).getSteps() == -1) {
                        if(gameClient.game.getBoard().canMove(player.getPos(), player.getOrientation().getReverse())) {
                            //Using getReverse in canMove function since we are interested in the opposite direction here
                            player.moveRobot(-1);
                            positionCheck(player); //Check for hole/flag
                            sendPlayerData();
                            try { //Delay for syns skyld
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    for (int j = 0; j < ((CardMove) currentCard).getSteps(); j++) {
                        if (gameClient.game.getBoard().canMove(player.getPos(), player.getOrientation())) {
                            player.moveRobot(1);
                            positionCheck(player); //Check for hole/flag
                            sendPlayerData();
                            try { //Delay for syns skyld
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                else if (currentCard instanceof CardTurn) {
                    player.rotateRobot(((CardTurn) currentCard).getTurnSteps());
                    sendPlayerData();
                    try { //Delay for syns skyld
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                sendPlayerData(); //Tell all clients to update board with new positions

                try { //Delay for syns skyld
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if(playerMap.values().stream().anyMatch(IPlayer::getAlive))
            dealCards();
        else
            kryoServer.stop();
    }

    public void positionCheck(IPlayer player){
        String standingOn = gameClient.getGame().getBoard().getElementInPos(player.getPos());
        switch (standingOn){
            case "flag":
                int flag = gameClient.getGame().getBoard().getLayers().get("Flag").getCell((int) player.getPos().x, (int) player.getPos().y).getTile().getId();
                if(flag == flags.get(player.getVisited().size()))
                    player.addVisitedFlag(flag);
                if(player.getVisited().size() >= flags.size()) {
                    sendPlayerWonMessage(player);
                    kryoServer.stop();
                }
                break;
            case "hole":
                player.applyDamage(9);
                player.setOrientation(Direction.NORTH);
            case "player":
                Vector2 newPosition = player.getPos().cpy().add(new Vector2(-1,0));
                Collection<IPlayer> otherPlayers = playerMap.values();
                IPlayer collidingPlayer = otherPlayers.stream().filter(x -> x.getPos() == player.getPos()).collect(Collectors.toList()).get(0);
                collidingPlayer.setPos(newPosition);
        }
    }

    public void dealCards(){
        ArrayList<ICard> gameDeck = new Deck().cards;
        for(Map.Entry<Connection, IPlayer> entry : playerMap.entrySet()) {
            if( !entry.getValue().getAlive()) {
                continue;
            }
            CardListPacket playerHandPacket = new CardListPacket();
            for (int i = 0; i < 9; i++) {
                playerHandPacket.cards.add(gameDeck.get(0));
                gameDeck.remove(0);
            }
            entry.getKey().sendTCP(playerHandPacket);
        }
    }

    public void sendPlayerWonMessage(IPlayer player){
        PlayerWonPacket p = new PlayerWonPacket(player);
        for(Connection c : playerMap.keySet())
            c.sendTCP(p);
    }

    public void sendPlayerData(){
        PlayerDataPacket p = new PlayerDataPacket();
        p.players.addAll(playerMap.values());
        for(Connection c : playerMap.keySet())
            c.sendTCP(p);
    }

    public GameClient getGameClient() {
        return gameClient;
    }
}
