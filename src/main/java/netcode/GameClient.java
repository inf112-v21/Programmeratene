package netcode;

import card.CardMove;
import card.CardTurn;
import card.ICard;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Client;

import java.util.ArrayList;

public class GameClient extends Listener {
    static Client client;
    static String ip = "localhost";
    static int udpPort = 27960, tcpPort = 27960;
    static boolean messageReceived = false;

    public static void main(String[] args) throws Exception {
        client = new Client();

        client.getKryo().register(NetworkPackage.class);
        client.getKryo().register(ArrayList.class);
        client.getKryo().register(ICard.class);
        client.getKryo().register(CardMove.class);
        client.getKryo().register(CardTurn.class);

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
}
