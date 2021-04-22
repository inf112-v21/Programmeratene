package netcode.packets;

import player.IPlayer;

public class PlayerWonPacket {
    public IPlayer player;

    public PlayerWonPacket(){}
    public PlayerWonPacket(IPlayer p){
        this.player = p;
    }
}
