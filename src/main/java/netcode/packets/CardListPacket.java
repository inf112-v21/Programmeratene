package netcode.packets;

import card.ICard;

import java.util.ArrayList;

public class CardListPacket {
    public ArrayList<ICard> cards;

    public CardListPacket() {
        this.cards = new ArrayList<>();
    }
}
