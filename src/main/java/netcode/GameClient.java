package netcode;

import card.CardMove;
import card.CardTurn;
import card.ICard;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Client;

import java.util.ArrayList;
import java.util.Scanner;

public class GameClient extends Listener {
    static Client client;
    static String ip = "localhost";
    static int udpPort = 27960, tcpPort = 27960;
    static boolean messageReceived = false;

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

    public void received(Connection c, Object p) {
        System.out.println("Received a packet: " + p.toString());
        if(p instanceof NetworkPackage){
            ArrayList<ICard> cards = ((NetworkPackage) p).cards;
            System.out.println("Received deck: " + cards.toString());

            messageReceived = true;
        }
    }
    public ArrayList pick(ArrayList<ICard> hand) {
        ArrayList<ICard> list = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        Scanner myScanner = new Scanner(System.in);
        while (list.size() < 5) {
            System.out.println("Program your robot: ");
            Integer index = myScanner.nextInt();
            if (indices.contains(index)) {
                System.out.println("You have already picked this card");

            }
            else if (index>9 && index<1){
                System.out.println("Invalid input");
            }
            else {
                indices.add(index);
                list.add(hand.get(index - 1));
            }
            System.out.println(list);
        }
        return (list);
    }
}
