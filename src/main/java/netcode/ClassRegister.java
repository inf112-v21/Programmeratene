package netcode;

import card.CardMove;
import card.CardTurn;
import card.ICard;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import game.Direction;
import netcode.packets.CardListPacket;
import netcode.packets.PlayerDataPacket;
import netcode.packets.PlayerWonPacket;
import player.HumanPlayer;

import java.util.ArrayList;

public class ClassRegister {

    static void registerAll(Kryo k){
        k.register(CardListPacket.class);
        k.register(PlayerDataPacket.class);
        k.register(PlayerWonPacket.class);

        k.register(ArrayList.class);
        k.register(ICard.class);
        k.register(CardMove.class);
        k.register(CardTurn.class);
        k.register(HumanPlayer.class);
        k.register(Direction.class);
        k.register(Vector2.class);
    }
}
