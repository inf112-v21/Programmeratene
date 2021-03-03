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
        client.addListener(new GameClient());
    }

    public GameClient(boolean isHost) {
        this();

        if(isHost)
            connectTo("localhost");
    }

    public boolean connectTo(String ip){
        try {
            client.connect(5000, ip, tcpPort, udpPort);
            System.out.println("Client is waiting for a packet...");
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public void received(Connection c, Object p) {
        System.out.println("Received a packet: " + p.toString());
        if(p instanceof NetworkPackage){
            ArrayList<ICard> cards = ((NetworkPackage) p).cards;
            System.out.println("Received deck: " + cards.toString());
            pick(cards);
        }
    }

    public ArrayList pick(ArrayList<ICard> deck) {
        ArrayList<ICard> list = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        Scanner myScanner = new Scanner(System.in);
        while (list.size() < 5) {
            System.out.println("Program your robot: ");
            Integer index = myScanner.nextInt();
            if (indices.contains(index)) {
                System.out.println("You have already picked this card");

            } else if (index > deck.size() && index < 1) {
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
