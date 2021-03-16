package netcode.packets;

import player.IPlayer;

public class AddPlayerPacket {
    IPlayer player;

    public AddPlayerPacket(IPlayer player) {
        this.player = player;
    }
}
