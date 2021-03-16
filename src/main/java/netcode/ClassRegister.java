package netcode;

import card.CardMove;
import card.CardTurn;
import card.ICard;
import com.esotericsoftware.kryo.Kryo;
import netcode.packets.AddPlayerPacket;
import netcode.packets.CardListPacket;

import java.util.ArrayList;

public class ClassRegister {

    static void registerAll(Kryo k){
        k.register(CardListPacket.class);
        k.register(AddPlayerPacket.class);
        k.register(ArrayList.class);
        k.register(ICard.class);
        k.register(CardMove.class);
        k.register(CardTurn.class);
    }
}
