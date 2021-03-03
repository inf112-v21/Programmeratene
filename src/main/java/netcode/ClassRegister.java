package netcode;

import card.CardMove;
import card.CardTurn;
import card.ICard;
import com.esotericsoftware.kryo.Kryo;

import java.util.ArrayList;

public class ClassRegister {

    static void registerAll(Kryo k){
        k.register(NetworkPackage.class);
        k.register(ArrayList.class);
        k.register(ICard.class);
        k.register(CardMove.class);
        k.register(CardTurn.class);
    }
}
