package netcode;

import card.ICard;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Client;
import game.Game;
import netcode.packets.AddPlayerPacket;
import netcode.packets.CardListPacket;
import netcode.packets.PlayerDataPacket;
import netcode.packets.PlayerWonPacket;
import player.IPlayer;

import java.util.ArrayList;
import java.util.Scanner;

public class GameClient extends Listener {
    private static final int udpPort = 27960;
    private static final int tcpPort = 27960;
    final Client kryoClient;
    final Game game;

    public GameClient(boolean isHost) {
        kryoClient = new Client();
        ClassRegister.registerAll(kryoClient.getKryo());
        kryoClient.start();
        kryoClient.addListener(this);

        game = new Game();
        if(isHost)
            connectTo("localhost");
    }

    public GameClient() {
        this(false);
    }

    public boolean connectTo(String ip){
        try {
            kryoClient.connect(5000, ip, tcpPort, udpPort);
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
        if(p instanceof CardListPacket){
            ArrayList<ICard> playerHand = ((CardListPacket) p).cards;
            System.out.println("══════════════════\n═ CARDS RECEIVED ═\n══════════════════");

            ArrayList<ICard> chosenCards = pick(playerHand);

            CardListPacket chosenCardsPacket = new CardListPacket();
            chosenCardsPacket.cards = chosenCards;
            kryoClient.sendTCP(chosenCardsPacket);
        }
        else if(p instanceof AddPlayerPacket){
            game.getBoard().addPlayer(((AddPlayerPacket) p).player);
        }
        else if(p instanceof PlayerDataPacket){
            ArrayList<IPlayer> players = ((PlayerDataPacket) p).players;
            game.getBoard().setPlayers(players);
            game.getBoard().drawPlayers();
            if(players.stream().noneMatch(IPlayer::getAlive)){
                System.out.println("All players died!");
                kryoClient.stop();
            }
        }
        else if(p instanceof PlayerWonPacket){
            IPlayer player = ((PlayerWonPacket) p).player;
            System.out.println(player.getPlayerName() + " won!");
            kryoClient.stop();
        }
    }

    public ArrayList<ICard> pick(ArrayList<ICard> deck) {
        ArrayList<ICard> list = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        for(int i=1; i<=9; i++)
            System.out.println(i+": "+deck.get(i-1));

        while (list.size() < 5) {
            System.out.print("Pick a card (1-9): ");
            int index = sc.nextInt();
            if (indices.contains(index)) {
                System.out.println("You have already picked this card");

            } else if (index > deck.size() || index < 1) {
                System.out.println("Invalid input");
            } else {
                indices.add(index);
                list.add(deck.get(index - 1));
            }
        }
        System.out.println("Cards chosen: " + list);
        return list;
    }

    public Game getGame() {
        return game;
    }

}
