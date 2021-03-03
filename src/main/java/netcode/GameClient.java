package netcode;

import card.ICard;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Client;
import game.Game;

import java.util.ArrayList;
import java.util.Scanner;

public class GameClient extends Listener {
    private static final int udpPort = 27960;
    private static final int tcpPort = 27960;
    Client client;
    Game game;

    public GameClient() {
        client = new Client();
        ClassRegister.registerAll(client.getKryo());
        client.start();
        client.addListener(this);

        game = new Game();
    }

    public GameClient(boolean isHost) {
        this();

        if(isHost)
            connectTo("localhost");
    }

    public boolean connectTo(String ip){
        try {
            client.connect(5000, ip, tcpPort, udpPort);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public void connected(Connection c) {
        System.out.println("Connected to host, waiting for packet...");
    }

    public void received(Connection c, Object p) {
        if(p instanceof NetworkPackage){
            ArrayList<ICard> cards = ((NetworkPackage) p).cards;
            System.out.println("Client received cards: " + cards.toString());

            ArrayList<ICard> chosenCards = pick(cards);

            NetworkPackage testPacket = new NetworkPackage();
            testPacket.cards = chosenCards;
            c.sendTCP(testPacket);
        }
    }

    public ArrayList pick(ArrayList<ICard> deck) {
        ArrayList<ICard> list = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        Scanner myScanner = new Scanner(System.in);
        while (list.size() < 5) {
            System.out.print("Program your robot: ");
            Integer index = myScanner.nextInt();
            if (indices.contains(index)) {
                System.out.println("You have already picked this card");

            } else if (index > deck.size() || index < 1) {
                System.out.println("Invalid input");
            } else {
                indices.add(index);
                list.add(deck.get(index - 1));
            }
            System.out.println(list);
        }
        return (list);
    }

    public Game getGame() {
        return game;
    }

}
