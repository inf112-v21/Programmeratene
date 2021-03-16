package netcode.packets;

import player.IPlayer;

public class AddPlayerPacket {
    public IPlayer player;

    public AddPlayerPacket(){

    }

    public AddPlayerPacket(IPlayer player) {
        this.player = player;
    }
}
