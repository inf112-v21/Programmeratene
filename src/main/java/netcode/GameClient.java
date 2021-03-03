package netcode;

import card.ICard;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Client;
import game.Game;

import java.util.ArrayList;
import java.util.Scanner;

public class GameClient extends Listener {
    static Client client;
    static String ip = "localhost";
    static int udpPort = 27960, tcpPort = 27960;
    static boolean messageReceived = false;
    Game game;

    public static void main(String[] args) throws Exception {
        client = new Client();

        ClassRegister.registerAll(client.getKryo());

        client.start();
        client.connect(5000, ip, tcpPort, udpPort);

        client.addListener(new GameClient());

        System.out.println("Client is waiting for a packet...");

        while(!messageReceived) {
            Thread.sleep(1000);
        }

        System.out.println("Client will now exit.");
        System.exit(0);
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

    public void received(Connection c, Object p) {
        System.out.println("Received a packet: " + p.toString());
        if(p instanceof NetworkPackage){
            ArrayList<ICard> cards = ((NetworkPackage) p).cards;
            System.out.println("Received deck: " + cards.toString());
            pick(cards);

            messageReceived = true;
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
