package netcode;

import card.ICard;

import java.util.ArrayList;

public class CardListPacket {
    ArrayList<ICard> cards;

    public CardListPacket() {
        this.cards = new ArrayList<>();
    }
}
