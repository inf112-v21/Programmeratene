package card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public static ArrayList<ICard> cards;

    public Deck(){
        cards = new ArrayList<>();
        generateFullDeck();
    }

    void generateFullDeck(){
        for(int i=0; i<18; i++){
            cards.add(new CardMove(1, 490 + 10*i)); //move 1
            cards.add(new CardTurn(-1, 70 + 20*i)); //rotate left
            cards.add(new CardTurn(1, 80 + 20*i)); //rotate right
        }
        for(int i=0; i<12; i++){
            cards.add(new CardMove(2, 670 + 10*i)); //move 2
        }
        for(int i=0; i<6; i++){
            cards.add(new CardMove(3, 790 + 10*i)); //move 3
            cards.add(new CardMove(-1, 430 + 10*i)); //move backwards 1
            cards.add(new CardTurn(2, 10 + 10*i)); //rotate 180deg
        }
    }

    void shuffle(){
        Collections.shuffle(cards);
    }


}
