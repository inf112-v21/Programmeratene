package netcode.packets;

import player.IPlayer;

import java.util.ArrayList;

public class PlayerDataPacket {
    public ArrayList<IPlayer> players;

    public PlayerDataPacket(){
        players = new ArrayList<>();
    }
}
